package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.QuestionEntity;
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
import com.aripd.bizibee.service.ResponseService;
import com.aripd.util.RequestUtil;
import java.util.List;
import java.util.stream.Collectors;

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
    private UploadedFile fileSimulation;

    @Inject
    private QuestionService questionService;
    private List<QuestionEntity> questions;
    List<QuestionEntity> responded;

    @Inject
    private GroupService groupService;

    @Inject
    private ResponseService responseService;

    @Inject
    MessageUtil messageUtil;

    public DashboardView() {
    }

    @PostConstruct
    private void init() {
        try {
            selectedUser = userService.getCurrentUser();
            selectedSimulation = selectedUser.getSimulation();

            questions = questionService.findAll();
            questions.sort((p1, p2) -> p1.getSortOrder() - p2.getSortOrder());

            responded = responseService.findByUser(selectedUser)
                    .stream()
                    .map(m -> m.getQuestion())
                    .collect(Collectors.toList());

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

    public void continueTheSimulation(ActionEvent actionEvent) {
        if (!questions.isEmpty()) {
            questions.removeAll(responded);
            QuestionEntity nextQuestion = questions.get(0);

            String navigation = "/player/simulation?uuid=" + nextQuestion.getUuid() + "&amp;faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } else {
            String navigation = "/player/report/list?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        }
    }

    public void doUploadImage(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedSimulation.setBytesCompany(file.getContents());
        }
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/ruler/simulation/logoCompany?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doResetImage(ActionEvent actionEvent) {
        selectedSimulation.setBytesCompany(null);
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/logoCompany?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doUploadImageSimulation(ActionEvent actionEvent) {
        if (fileSimulation != null && fileSimulation.getSize() > 0) {
            selectedSimulation.setBytesSimulation(fileSimulation.getContents());
        }
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/ruler/simulation/logoSimulation?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doResetImageSimulation(ActionEvent actionEvent) {
        selectedSimulation.setBytesSimulation(null);
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/logoSimulation?faces-redirect=true";
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

    public UploadedFile getFileSimulation() {
        return fileSimulation;
    }

    public void setFileSimulation(UploadedFile fileSimulation) {
        this.fileSimulation = fileSimulation;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<QuestionEntity> getResponded() {
        return responded;
    }

    public void setResponded(List<QuestionEntity> responded) {
        this.responded = responded;
    }

}
