package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import com.aripd.bizibee.service.SimulationService;
import com.aripd.util.MessageUtil;
import com.aripd.util.validator.EmailAddress;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SignupBean implements Serializable {

    @Inject
    private SimulationService simulationService;

    @Inject
    private UserService userService;

    private String simulationCode;
    private String username;
    private String password;
    @EmailAddress
    private String email;
    private String name;
    private boolean agree;

    @Inject
    MessageUtil messageUtil;

    public SignupBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void doSignup(ActionEvent actionEvent) {
        SimulationEntity simulation = simulationService.findOneByCode(simulationCode);
        UserEntity user = userService.findOneByUsername(username);
        if (simulation == null) {
            messageUtil.addGlobalErrorFlashMessage("Simulation is not exist");
        } else if (user != null) {
            messageUtil.addGlobalErrorFlashMessage("User is exist");
        } else if (!agree) {
            messageUtil.addGlobalErrorFlashMessage("Please read terms of use");
        } else {
            user = new UserEntity();
            user.setSimulation(simulation);
            user.setUserGroup(UserGroup.Players);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);

            userService.create(user);
            messageUtil.addGlobalInfoFlashMessage("Created");
        }
    }

    public String getSimulationCode() {
        return simulationCode;
    }

    public void setSimulationCode(String simulationCode) {
        this.simulationCode = simulationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

}
