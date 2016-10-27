package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyCriteriaoptionDataModel;
import com.aripd.bizibee.entity.CriteriaoptionEntity;
import com.aripd.bizibee.service.CriteriaoptionService;
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
public class CriteriaoptionView implements Serializable {

    static final Logger LOG = Logger.getLogger(CriteriaoptionView.class.getName());

    @Inject
    private CriteriaoptionService criteriaoptionService;
    private CriteriaoptionEntity newRecord;
    private CriteriaoptionEntity selectedRecord;
    private List<CriteriaoptionEntity> selectedRecords;
    private LazyDataModel<CriteriaoptionEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public CriteriaoptionView() {
        newRecord = new CriteriaoptionEntity();
        selectedRecord = new CriteriaoptionEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyCriteriaoptionDataModel(criteriaoptionService);
    }

    public List<CriteriaoptionEntity> fetchAllRecords() {
        return criteriaoptionService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        criteriaoptionService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        criteriaoptionService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        criteriaoptionService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        criteriaoptionService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public CriteriaoptionEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(CriteriaoptionEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<CriteriaoptionEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<CriteriaoptionEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public CriteriaoptionEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(CriteriaoptionEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<CriteriaoptionEntity> getLazyModel() {
        return lazyModel;
    }

}
