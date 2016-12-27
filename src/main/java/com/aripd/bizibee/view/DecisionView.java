package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionType;
import com.aripd.bizibee.entity.SkuEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.SkuService;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class DecisionView implements Serializable {

    @Inject
    private DecisionService decisionService;
    private DecisionEntity newRecord;
    private DecisionEntity selectedRecord;
    private List<DecisionEntity> selectedRecords;
    private LazyDataModel<DecisionEntity> lazyModel;

    private UploadedFile file;

    @Inject
    private SkuService skuService;

    @Inject
    MessageUtil messageUtil;

    public DecisionView() {
        newRecord = new DecisionEntity();
        selectedRecord = new DecisionEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDecisionDataModel(decisionService);
    }

    public List<DecisionType> getDecisionTypes() {
        return Arrays.asList(DecisionType.values());
    }

    public List<DecisionEntity> getDecisions() {
        return decisionService.findAll();
    }

    public List<SkuEntity> getSkus() {
        return skuService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            newRecord.setBytes(file.getContents());
        }
        decisionService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedRecord.setBytes(file.getContents());
        }
        decisionService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        decisionService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        decisionService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public DecisionEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(DecisionEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<DecisionEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<DecisionEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public DecisionEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(DecisionEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<DecisionEntity> getLazyModel() {
        return lazyModel;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
