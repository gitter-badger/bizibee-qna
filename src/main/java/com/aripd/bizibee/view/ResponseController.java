package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyResponseDataModel;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class ResponseController implements Serializable {

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;
    private List<ResponseEntity> selectedRecords;
    private LazyDataModel<ResponseEntity> lazyModel;

    @Inject
    private UserService userService;
    private UserEntity selectedPlayer;
    private LazyDataModel<UserEntity> lazyModelPlayer;

    @Inject
    MessageUtil messageUtil;

    public ResponseController() {
        selectedRecord = new ResponseEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyResponseDataModel(responseService);

        UserEntity user = userService.getCurrentUser();
        SimulationEntity simulation = user.getSimulation();
        lazyModelPlayer = new LazyUserDataModelBySimulation(userService, simulation);
    }

    public void doGenerateReport() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();

            // get the JRXML template as a stream
            InputStream inputStream = externalContext.getResourceAsStream("/resources/reports/Report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//            InputStream is_subreport = externalContext.getResourceAsStream("/resources/reports/Report_Item.jrxml");
//            JasperReport jaspersubreport = JasperCompileManager.compileReport(is_subreport);

            Map<String, Object> jasperParameters = new HashMap();
//            jasperParameters.put(JRParameter.REPORT_LOCALE, LocaleProvider.getLocale());
//            jasperParameters.put("SUBREPORT1", jaspersubreport);
            jasperParameters.put("user", selectedPlayer);

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

    public List<ResponseEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<ResponseEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public LazyDataModel<ResponseEntity> getLazyModel() {
        return lazyModel;
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

}
