package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.TeamEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.PlayerEntity;
import com.aripd.bizibee.model.data.LazyPlayerDataModel;
import com.aripd.bizibee.service.TeamService;
import com.aripd.bizibee.service.PlayerService;
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
public class PlayerView implements Serializable {

    static final Logger LOG = Logger.getLogger(PlayerView.class.getName());

    @Inject
    private PlayerService playerService;
    private PlayerEntity newRecord;
    private PlayerEntity selectedRecord;
    private List<PlayerEntity> selectedRecords;
    private LazyDataModel<PlayerEntity> lazyModel;

    @Inject
    private TeamService teamService;

    @Inject
    MessageUtil messageUtil;

    public PlayerView() {
        newRecord = new PlayerEntity();
        selectedRecord = new PlayerEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyPlayerDataModel(playerService);
    }

    public List<TeamEntity> getTeams() {
        return teamService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        playerService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        playerService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        playerService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        playerService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public PlayerEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(PlayerEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<PlayerEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<PlayerEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public PlayerEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(PlayerEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<PlayerEntity> getLazyModel() {
        return lazyModel;
    }

}
