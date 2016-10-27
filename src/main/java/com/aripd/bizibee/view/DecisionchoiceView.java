package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionchoiceDataModel;
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
import com.aripd.bizibee.service.DecisionchoiceService;

@Named
@ViewScoped
public class DecisionchoiceView implements Serializable {

    static final Logger LOG = Logger.getLogger(DecisionchoiceView.class.getName());

    @Inject
    private DecisionchoiceService decisionchoiceService;
    private DecisionchoiceEntity newRecord;
    private DecisionchoiceEntity selectedRecord;
    private List<DecisionchoiceEntity> selectedRecords;
    private LazyDataModel<DecisionchoiceEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public DecisionchoiceView() {
        newRecord = new DecisionchoiceEntity();
        selectedRecord = new DecisionchoiceEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDecisionchoiceDataModel(decisionchoiceService);
    }

    public List<DecisionchoiceEntity> fetchAllRecords() {
        return decisionchoiceService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        decisionchoiceService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        decisionchoiceService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        decisionchoiceService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        decisionchoiceService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public DecisionchoiceEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(DecisionchoiceEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<DecisionchoiceEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<DecisionchoiceEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public DecisionchoiceEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(DecisionchoiceEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<DecisionchoiceEntity> getLazyModel() {
        return lazyModel;
    }

}
