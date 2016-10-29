package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyUserDataModel;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.SimulationService;

@Named
@ViewScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity newRecord;
    private UserEntity selectedRecord;
    private List<UserEntity> selectedRecords;
    private LazyDataModel<UserEntity> lazyModel;

    @Inject
    private SimulationService simulationService;

    @Inject
    MessageUtil messageUtil;

    public UserController() {
        newRecord = new UserEntity();
        selectedRecord = new UserEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyUserDataModel(userService);
    }

    public List<UserGroup> getUserGroups() {
        return Arrays.asList(UserGroup.values());
    }

    public List<SimulationEntity> getSimulations() {
        return simulationService.findAll();
    }

    public int fullnameSorter(UserEntity e1, UserEntity e2) {
        Collator trCollator = Collator.getInstance(new Locale("tr", "TR"));
        return trCollator.compare(e1.getFullname(), e2.getFullname());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        userService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        userService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        userService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        userService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<UserEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<UserEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public UserEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(UserEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<UserEntity> getLazyModel() {
        return lazyModel;
    }

}
