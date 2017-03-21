package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.GroupEntity;
import com.aripd.bizibee.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.service.GroupService;

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
    MessageUtil messageUtil;

    public OverviewView() {
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();

        groups = groupService.findAll();
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
