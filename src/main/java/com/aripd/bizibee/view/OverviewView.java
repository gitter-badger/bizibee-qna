package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.GroupEntity;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.service.GroupService;
import com.aripd.bizibee.service.ResponseService;

@Named
@ViewScoped
public class OverviewView implements Serializable {

    @Inject
    private GroupService groupService;
    private List<GroupEntity> groups;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    private ResponseService responseService;

    @Inject
    MessageUtil messageUtil;

    public OverviewView() {
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        groups = groupService.findAll();
    }

    public boolean checkCompletion(GroupEntity group) {
        boolean responded = false;
        List<QuestionEntity> questions = group.getQuestions();
        for (QuestionEntity question : questions) {
            ResponseEntity response = responseService.findOneByUserAndQuestion(user, question);
            if (response != null) {
                responded = true;
            } else {
                responded = false;
                break;
            }
        }

        return responded;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
