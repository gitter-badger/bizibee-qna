package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.model.response.ResponseConverter;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.AnswerService;

@Named
@ViewScoped
public class ReportView implements Serializable {

    @Inject
    private UserService userService;
    private LazyDataModel<UserEntity> lazyModelPlayer;
    private UserEntity user;
    private SimulationEntity simulation;

    private Long id;
    private UserEntity selectedPlayer;

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;

    @Inject
    private AnswerService answerService;

    double sales = 0;
    double budget = 0;
    double budgetChange = 0;
    double gm = 0;
    double gmChange = 0;
    double ms = 0;
    double msChange = 0;
    double usg = 0;
    double usgChange = 0;

    @Inject
    MessageUtil messageUtil;

    public ReportView() {
        selectedRecord = new ResponseEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        simulation = user.getSimulation();
        lazyModelPlayer = new LazyUserDataModelBySimulation(userService, simulation);

        sales = simulation.getSalesStart();
        budget = simulation.getBudgetStart();
        budgetChange = 0;
        gm = simulation.getGmStart();
        gmChange = 0;
        ms = simulation.getMsStart();
        msChange = 0;
        usg = 0;
        usgChange = 0;
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedPlayer = userService.find(id);

        if (selectedPlayer == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public String calculateScore(ResponseEntity response) {
        String outcome = response.getOutcome();
        QuestionEntity question = response.getQuestion();

        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double budgetLocal = 0;
        double gmLocal = 0;
        double msLocal = 0;
        double usgLocal = 0;

        switch (question.getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    budget += answer.getCoefBudget();
                    budgetLocal = answer.getCoefBudget();
                    gm += answer.getCoefGm();
                    gmLocal = answer.getCoefGm();
                    ms += answer.getCoefMs();
                    msLocal = answer.getCoefMs();
                    usg += answer.getCoefUsg();
                    usgLocal = answer.getCoefUsg();
                } catch (NullPointerException ex) {
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("answers");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    answerId = jsonObject2.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    budget += answer.getCoefBudget();
                    budgetLocal += answer.getCoefBudget();
                    gm += answer.getCoefGm();
                    gmLocal += answer.getCoefGm();
                    ms += answer.getCoefMs();
                    msLocal += answer.getCoefMs();
                    usg += answer.getCoefUsg();
                    usgLocal += answer.getCoefUsg();
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    answerId = jsonObject2.getJsonNumber("answer").longValue();
                    answer = answerService.find(answerId);

                    try {
                        value = jsonObject2.getJsonNumber("value").intValue();
                    } catch (NullPointerException | ClassCastException ex) {
                        // TODO bunun yerine default olarak answer.getIndexMin() girilebilir
                        value = answer.getCoefIndexMin();
                    }

                    budget += answer.getCoefBudget();
                    budgetLocal += answer.getCoefBudget();
                    if (value >= answer.getCoefIndexMin() && value < answer.getCoefMsBreakpointIndexMin()) {
                        ms += 0;
                        msLocal += 0;
                    } else if (value >= answer.getCoefMsBreakpointIndexMin() && value < answer.getCoefMsBreakpointIndexMax()) {
                        ms += answer.getCoefMsGainMax() + ((value - answer.getCoefMsBreakpointIndexMin()) * (answer.getCoefMsGainMin() - answer.getCoefMsGainMax())) / (answer.getCoefMsBreakpointIndexMax() - answer.getCoefMsBreakpointIndexMin());
                    } else {
                        ms += answer.getCoefMsGainMin();
                        msLocal += answer.getCoefMsGainMin();
                    }
                    gm += answer.getCoefGmGainMin() + ((value - answer.getCoefIndexMin()) * (answer.getCoefGmGainMax() - answer.getCoefGmGainMin()) / ((answer.getCoefIndexMax() + answer.getCoefIndexMin()) / 2 - (answer.getCoefIndexMin())));
                    gmLocal += answer.getCoefGmGainMin() + ((value - answer.getCoefIndexMin()) * (answer.getCoefGmGainMax() - answer.getCoefGmGainMin()) / ((answer.getCoefIndexMax() + answer.getCoefIndexMin()) / 2 - (answer.getCoefIndexMin())));
                    usg += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                    usgLocal += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
        }
        return outcome;
    }

    public List<ResponseEntity> getResponses(UserEntity u) {
        if (u != null) {
            return responseService.findByUser(u);
        } else {
            return responseService.findByUser(user);
        }
    }

    public void doGenerateReport() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();

            // get the JRXML template as a stream
            InputStream inputStream = externalContext.getResourceAsStream("/resources/reports/Report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            InputStream is_subreport = externalContext.getResourceAsStream("/resources/reports/Report_Item.jrxml");
            JasperReport jaspersubreport = JasperCompileManager.compileReport(is_subreport);

            InputStream logo = externalContext.getResourceAsStream("/resources/reports/logo.png");

            Map<String, Object> jasperParameters = new HashMap();
//            jasperParameters.put(JRParameter.REPORT_LOCALE, LocaleProvider.getLocale());
            jasperParameters.put("SUBREPORT1", jaspersubreport);
            jasperParameters.put("player", selectedPlayer);
            jasperParameters.put("responses", responseService.findByUser(selectedPlayer));
            jasperParameters.put("logo", logo);

            // filling report with data from data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameters);
            // exporting process
            File file = File.createTempFile("Report." + selectedPlayer.getUsername() + ".", ".pdf");
            externalContext.setResponseHeader("Content-Type", "application/force-download");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            OutputStream out = externalContext.getResponseOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            facesContext.responseComplete();
        } catch (JRException | IOException ex) {
            throw new FacesException(ex);
        }
    }

    public ResponseEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ResponseEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getGm() {
        return gm;
    }

    public void setGm(double gm) {
        this.gm = gm;
    }

    public double getMs() {
        return ms;
    }

    public void setMs(double ms) {
        this.ms = ms;
    }

    public double getUsg() {
        return usg;
    }

    public void setUsg(double usg) {
        this.usg = usg;
    }

    public double getGmChange() {
        return gmChange;
    }

    public void setGmChange(double gmChange) {
        this.gmChange = gmChange;
    }

    public double getMsChange() {
        return msChange;
    }

    public void setMsChange(double msChange) {
        this.msChange = msChange;
    }

    public double getUsgChange() {
        return usgChange;
    }

    public void setUsgChange(double usgChange) {
        this.usgChange = usgChange;
    }

    public double getBudgetChange() {
        return budgetChange;
    }

    public void setBudgetChange(double budgetChange) {
        this.budgetChange = budgetChange;
    }

    public LazyDataModel<UserEntity> getLazyModelPlayer() {
        return lazyModelPlayer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(UserEntity selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

}
