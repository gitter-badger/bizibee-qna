package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.DecisionchoiceService;
import java.util.ArrayList;

@Named
@ViewScoped
public class ResponseView implements Serializable {

    static final Logger LOG = Logger.getLogger(ResponseView.class.getName());

    @Inject
    private DecisionService decisionService;
    private DecisionEntity newRecord;
    private DecisionEntity selectedRecord;
    private List<DecisionEntity> selectedRecords;
    private LazyDataModel<DecisionEntity> lazyModel;

    private Long id;

    @Inject
    private DecisionchoiceService decisionchoiceService;
    private DecisionchoiceEntity decisionchoice;
    private List<DecisionchoiceEntity> decisionchoices = new ArrayList<>();

    @Inject
    MessageUtil messageUtil;

    public ResponseView() {
        newRecord = new DecisionEntity();
        selectedRecord = new DecisionEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDecisionDataModel(decisionService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = decisionService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public void doUpdate(ActionEvent actionEvent) {
        LOG.info("decisionchoice: " + decisionchoice);
        LOG.info("decisionchoices: " + decisionchoices);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public List<DecisionchoiceEntity> fetchDecisionchoices(DecisionEntity decision) {
        return decisionchoiceService.findByDecision(decision);
    }

    public List<DecisionEntity> fetchAllRecords() {
        return decisionService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        decisionService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DecisionchoiceEntity getDecisionchoice() {
        return decisionchoice;
    }

    public void setDecisionchoice(DecisionchoiceEntity decisionchoice) {
        this.decisionchoice = decisionchoice;
    }

    public List<DecisionchoiceEntity> getDecisionchoices() {
        return decisionchoices;
    }

    public void setDecisionchoices(List<DecisionchoiceEntity> decisionchoices) {
        this.decisionchoices = decisionchoices;
    }

}
