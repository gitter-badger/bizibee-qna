package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyTeamDataModel;
import com.aripd.bizibee.entity.TeamEntity;
import com.aripd.bizibee.service.TeamService;
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
public class TeamController implements Serializable {

    static final Logger LOG = Logger.getLogger(TeamController.class.getName());

    @Inject
    private TeamService teamService;
    private TeamEntity newRecord;
    private TeamEntity selectedRecord;
    private List<TeamEntity> selectedRecords;
    private LazyDataModel<TeamEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public TeamController() {
        newRecord = new TeamEntity();
        selectedRecord = new TeamEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyTeamDataModel(teamService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        teamService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        teamService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        teamService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        teamService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public TeamEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(TeamEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<TeamEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<TeamEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public TeamEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(TeamEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<TeamEntity> getLazyModel() {
        return lazyModel;
    }

}
