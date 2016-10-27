package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyCriteriaDataModel;
import com.aripd.bizibee.entity.CriteriaEntity;
import com.aripd.bizibee.service.CriteriaService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;

@Named
@ViewScoped
public class CriteriaView implements Serializable {

    static final Logger LOG = Logger.getLogger(CriteriaView.class.getName());

    @Inject
    private CriteriaService criteriaService;
    private CriteriaEntity newRecord;
    private CriteriaEntity selectedRecord;
    private List<CriteriaEntity> selectedRecords;
    private LazyDataModel<CriteriaEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public CriteriaView() {
        newRecord = new CriteriaEntity();
        selectedRecord = new CriteriaEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyCriteriaDataModel(criteriaService);
    }

    public List<CriteriaEntity> fetchAllRecords() {
        return criteriaService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        criteriaService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        criteriaService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        criteriaService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        criteriaService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public CriteriaEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(CriteriaEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<CriteriaEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<CriteriaEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public CriteriaEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(CriteriaEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<CriteriaEntity> getLazyModel() {
        return lazyModel;
    }

}
