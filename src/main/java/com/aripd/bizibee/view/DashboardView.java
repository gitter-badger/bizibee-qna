package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.Kind;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.GroupService;
import com.aripd.bizibee.service.SimulationService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import com.aripd.bizibee.service.QuestionService;
import com.aripd.util.RequestUtil;
import java.util.Arrays;

@Named
@ViewScoped
public class DashboardView implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedUser;

    @Inject
    private SimulationService simulationService;
    private SimulationEntity selectedSimulation;

    private UploadedFile file;

    @Inject
    private QuestionService questionService;

    @Inject
    private GroupService groupService;

    @Inject
    MessageUtil messageUtil;

    public DashboardView() {
    }

    @PostConstruct
    private void init() {
        try {
            selectedUser = userService.getCurrentUser();
            selectedSimulation = selectedUser.getSimulation();
        } catch (NullPointerException ex) {
            throw new FacesException(ex);
        }
    }

    public int getNumberOfTeams() {
        return userService.calculateNumberOfTeams(selectedSimulation);
    }

    public int getNumberOfPlayers() {
        return userService.calculateNumberOfPlayers(selectedSimulation);
    }

    public int getNumberOfDecisions() {
        return groupService.count();
    }

    public int getNumberOfQuestions() {
        return questionService.calculateNumberOfQuestionsByKind(Arrays.asList(Kind.SIMULATION));
    }

    public void doUploadImage(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedSimulation.setBytes(file.getContents());
        }
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/ruler/simulation/logo?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doResetImage(ActionEvent actionEvent) {
        selectedSimulation.setBytes(null);
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/logo?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doUpdateSimulation(ActionEvent actionEvent) {
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public UserEntity getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserEntity selectedUser) {
        this.selectedUser = selectedUser;
    }

    public SimulationEntity getSelectedSimulation() {
        return selectedSimulation;
    }

    public void setSelectedSimulation(SimulationEntity selectedSimulation) {
        this.selectedSimulation = selectedSimulation;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
