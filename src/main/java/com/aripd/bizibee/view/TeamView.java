package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyTeamDataModel;
import com.aripd.bizibee.entity.TeamEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.TeamService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class TeamView implements Serializable {

    static final Logger LOG = Logger.getLogger(TeamView.class.getName());

    @Inject
    private TeamService teamService;
    private TeamEntity newRecord;
    private TeamEntity selectedRecord;
    private List<TeamEntity> selectedRecords;
    private LazyDataModel<TeamEntity> lazyModel;

    @Inject
    private UserService userService;

    private DualListModel<UserEntity> users;

    @Inject
    MessageUtil messageUtil;

    public TeamView() {
        newRecord = new TeamEntity();
        selectedRecord = new TeamEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyTeamDataModel(teamService);

        UserEntity user = userService.getCurrentUser();
        SimulationEntity simulation = user.getSimulation();

        List<UserEntity> usersSource = userService.findAllBySimulationAndNoTeamAssigned(simulation);
        List<UserEntity> usersTarget = new ArrayList<>();
        users = new DualListModel<>(usersSource, usersTarget);
    }

    public List<TeamEntity> fetchAllRecords() {
        return teamService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        newRecord.setUsers(users.getTarget());
        teamService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        selectedRecord.setUsers(users.getTarget());
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

    public DualListModel<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<UserEntity> users) {
        this.users = users;
    }

}
