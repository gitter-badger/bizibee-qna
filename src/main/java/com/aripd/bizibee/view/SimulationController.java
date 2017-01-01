package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazySimulationDataModel;
import com.aripd.bizibee.entity.SimulationEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.SimulationService;

@Named
@ViewScoped
public class SimulationController implements Serializable {

    @Inject
    private SimulationService simulationService;
    private SimulationEntity newRecord;
    private SimulationEntity selectedRecord;
    private List<SimulationEntity> selectedRecords;
    private LazyDataModel<SimulationEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public SimulationController() {
        newRecord = new SimulationEntity();
        selectedRecord = new SimulationEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySimulationDataModel(simulationService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        simulationService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        simulationService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        simulationService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            simulationService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public SimulationEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SimulationEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<SimulationEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<SimulationEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public SimulationEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(SimulationEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<SimulationEntity> getLazyModel() {
        return lazyModel;
    }

}
