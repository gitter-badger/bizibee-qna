package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.service.SimulationService;
import com.aripd.bizibee.service.TeamService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class DashboardController implements Serializable {

    @Inject
    private SimulationService simulationService;

    @Inject
    private UserService userService;

    @Inject
    private TeamService teamService;

    @Inject
    MessageUtil messageUtil;

    public DashboardController() {
    }

    @PostConstruct
    private void init() {
    }

    public int getNumberOfSimulations() {
        return simulationService.count();
    }

    public int getNumberOfUsers() {
        return userService.count();
    }

    public int getNumberOfTeams() {
        return teamService.count();
    }

}