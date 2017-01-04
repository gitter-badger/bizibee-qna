package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.model.response.ResponseConverter;
import com.aripd.bizibee.service.DecisionchoiceService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.SkuService;
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
    private DecisionchoiceService decisionchoiceService;

    @Inject
    private SkuService skuService;

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
        DecisionEntity decision = response.getDecision();

        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long skuId;
        SkuEntity sku;

        Long decisionchoiceId;
        DecisionchoiceEntity decisionchoice;

        int value;

        double budgetLocal = 0;
        double gmLocal = 0;
        double msLocal = 0;
        double usgLocal = 0;

        switch (decision.getDecisionType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                try {
                    decisionchoiceId = jsonObject1.getJsonNumber("id").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    budget += decisionchoice.getBudget();
                    budgetLocal = decisionchoice.getBudget();
                    gm += decisionchoice.getGm();
                    gmLocal = decisionchoice.getGm();
                    ms += decisionchoice.getMs();
                    msLocal = decisionchoice.getMs();
                    usg += decisionchoice.getUsg();
                    usgLocal = decisionchoice.getUsg();
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
                jsonArray1 = jsonObject1.getJsonArray("decisionchoices");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    decisionchoiceId = jsonObject2.getJsonNumber("id").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    budget += decisionchoice.getBudget();
                    budgetLocal += decisionchoice.getBudget();
                    gm += decisionchoice.getGm();
                    gmLocal += decisionchoice.getGm();
                    ms += decisionchoice.getMs();
                    msLocal += decisionchoice.getMs();
                    usg += decisionchoice.getUsg();
                    usgLocal += decisionchoice.getUsg();
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case SINGLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                try {
                    skuId = jsonObject1.getJsonNumber("id").longValue();
                    sku = skuService.find(skuId);
                    budget += sku.getBudget();
                    budgetLocal = sku.getBudget();
                    gm += sku.getGm();
                    gmLocal = sku.getGm();
                    ms += sku.getMs();
                    msLocal = sku.getMs();
                    usg += sku.getUsg();
                    usgLocal = sku.getUsg();
                } catch (NullPointerException ex) {
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case MULTIPLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("skus");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("id").longValue();
                    sku = skuService.find(skuId);
                    budget += sku.getBudget();
                    budgetLocal += sku.getBudget();
                    gm += sku.getGm();
                    gmLocal += sku.getGm();
                    ms += sku.getMs();
                    msLocal += sku.getMs();
                    usg += sku.getUsg();
                    usgLocal += sku.getUsg();
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case RANGE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);

                    try {
                        value = jsonObject2.getJsonNumber("value").intValue();
                    } catch (NullPointerException | ClassCastException ex) {
                        // TODO bunun yerine default olarak sku.getIndexMin() girilebilir
                        value = sku.getIndexMin();
                    }

                    budget += sku.getBudget();
                    budgetLocal += sku.getBudget();
                    if (value >= sku.getIndexMin() && value < sku.getMsBreakpointIndexMin()) {
                        ms += 0;
                        msLocal += 0;
                    } else if (value >= sku.getMsBreakpointIndexMin() && value < sku.getMsBreakpointIndexMax()) {
                        ms += sku.getMsGainMax() + ((value - sku.getMsBreakpointIndexMin()) * (sku.getMsGainMin() - sku.getMsGainMax())) / (sku.getMsBreakpointIndexMax() - sku.getMsBreakpointIndexMin());
                    } else {
                        ms += sku.getMsGainMin();
                        msLocal += sku.getMsGainMin();
                    }
                    gm += sku.getGmGainMin() + ((value - sku.getIndexMin()) * (sku.getGmGainMax() - sku.getGmGainMin()) / ((sku.getIndexMax() + sku.getIndexMin()) / 2 - (sku.getIndexMin())));
                    gmLocal += sku.getGmGainMin() + ((value - sku.getIndexMin()) * (sku.getGmGainMax() - sku.getGmGainMin()) / ((sku.getIndexMax() + sku.getIndexMin()) / 2 - (sku.getIndexMin())));
                    usg += ((sku.getIndexMax() - value) * (sku.getUsgGainMax() - sku.getUsgGainMin()) / (sku.getIndexMax() - sku.getIndexMin())) + sku.getUsgGainMin();
                    usgLocal += ((sku.getIndexMax() - value) * (sku.getUsgGainMax() - sku.getUsgGainMin()) / (sku.getIndexMax() - sku.getIndexMin())) + sku.getUsgGainMin();
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    budget += sku.getBudget();
                    budgetLocal += sku.getBudget();
                    gm += sku.getGm();
                    gmLocal += sku.getGm();
                    ms += sku.getMs();
                    msLocal += sku.getMs();
                    usg += sku.getUsg();
                    usgLocal += sku.getUsg();

                    try {
                        decisionchoiceId = jsonObject2.getJsonNumber("decisionchoice").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        budget += decisionchoice.getBudget();
                        budgetLocal += decisionchoice.getBudget();
                        gm += decisionchoice.getGm();
                        gmLocal += decisionchoice.getGm();
                        ms += decisionchoice.getMs();
                        msLocal += decisionchoice.getMs();
                        usg += decisionchoice.getUsg();
                        usgLocal += decisionchoice.getUsg();
                    } catch (NullPointerException | ClassCastException ex) {
                    }
                }

                budgetChange = budgetLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    budget += sku.getBudget();
                    budgetLocal += sku.getBudget();
                    gm += sku.getGm();
                    gmLocal += sku.getGm();
                    ms += sku.getMs();
                    msLocal += sku.getMs();
                    usg += sku.getUsg();
                    usgLocal += sku.getUsg();

                    JsonArray jsonArray2 = jsonObject2.getJsonArray("decisionchoices");
                    for (JsonValue jsonValue2 : jsonArray2) {
                        JsonObject jsonObject3 = ResponseConverter.jsonObjectFromString(jsonValue2.toString());
                        decisionchoiceId = jsonObject3.getJsonNumber("decisionchoice").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        budget += decisionchoice.getBudget();
                        budgetLocal += decisionchoice.getBudget();
                        gm += decisionchoice.getGm();
                        gmLocal += decisionchoice.getGm();
                        ms += decisionchoice.getMs();
                        msLocal += decisionchoice.getMs();
                        usg += decisionchoice.getUsg();
                        usgLocal += decisionchoice.getUsg();
                    }
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
