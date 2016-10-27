package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyCompanyDataModel;
import com.aripd.bizibee.entity.CompanyEntity;
import com.aripd.bizibee.service.CompanyService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class CompanyController implements Serializable {

    @Inject
    private CompanyService companyService;
    private CompanyEntity newRecord;
    private CompanyEntity selectedRecord;
    private List<CompanyEntity> selectedRecords;
    private LazyDataModel<CompanyEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public CompanyController() {
        newRecord = new CompanyEntity();
        selectedRecord = new CompanyEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyCompanyDataModel(companyService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        companyService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        companyService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        companyService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        companyService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public CompanyEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(CompanyEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<CompanyEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<CompanyEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public CompanyEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(CompanyEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<CompanyEntity> getLazyModel() {
        return lazyModel;
    }

}
