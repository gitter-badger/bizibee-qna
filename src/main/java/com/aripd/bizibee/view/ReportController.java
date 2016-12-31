package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class ReportController implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedPlayer;
    private LazyDataModel<UserEntity> lazyModelPlayer;

    private Long id;

    @Inject
    private ResponseService responseService;

    @Inject
    MessageUtil messageUtil;

    public ReportController() {
    }

    @PostConstruct
    public void init() {
        UserEntity ruler = userService.getCurrentUser();
        SimulationEntity simulation = ruler.getSimulation();
        lazyModelPlayer = new LazyUserDataModelBySimulation(userService, simulation);
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

    public LazyDataModel<UserEntity> getLazyModelPlayer() {
        return lazyModelPlayer;
    }

    public UserEntity getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(UserEntity selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
