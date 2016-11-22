package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
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
public class ResetBean implements Serializable {

    @Inject
    private SimulationService simulationService;

    @Inject
    private UserService userService;

    private String simulationCode;
    @EmailAddress
    private String email;
    private String password;

    @Inject
    MessageUtil messageUtil;

    public ResetBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void doReset(ActionEvent actionEvent) {
        SimulationEntity simulation = simulationService.findOneByCode(simulationCode);
        UserEntity user = userService.findOneBySimulationAndEmail(simulation, email);
        if (simulation == null) {
            messageUtil.addGlobalErrorFlashMessage("Simulation is not exist");
        } else if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else {
//            String password = RandomStringUtils.random(6, 0, 0, true, true, null, new SecureRandom());
            user.setPassword(password);
            userService.update(user);

            messageUtil.addGlobalInfoFlashMessage("Changed");

            simulationCode = "";
            email = "";
            password = "";
        }
    }

    public String getSimulationCode() {
        return simulationCode;
    }

    public void setSimulationCode(String simulationCode) {
        this.simulationCode = simulationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
