package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.Kind;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.ResponseEntity;
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
import java.util.Arrays;
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

    @Inject
    private QuestionService questionService;

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

    public void continueTheSimulation(ActionEvent actionEvent) {
        List<QuestionEntity> questions = questionService.findAll();
        questions.sort((p1, p2) -> p1.getSortOrder() - p2.getSortOrder());

        List<ResponseEntity> responses = responseService.findByUser(selectedUser);
        List<QuestionEntity> responded = responses
                .stream()
                .map(m -> m.getQuestion())
                .collect(Collectors.toList());

        questions.removeAll(responded);
        if (!questions.isEmpty()) {
            QuestionEntity nextQuestion = questions.get(0);

            String navigation = "/player/simulation?uuid=" + nextQuestion.getUuid() + "&amp;faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } else {
            String navigation = "/player/report?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        }
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
