package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.SimulationService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SimulationView implements Serializable {

    static final Logger LOG = Logger.getLogger(SimulationView.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private SimulationService simulationService;
    private SimulationEntity selectedRecord;

    @Inject
    MessageUtil messageUtil;

    public SimulationView() {
    }

    @PostConstruct
    private void init() {
        UserEntity user = userService.getCurrentUser();
        selectedRecord = user.getSimulation();
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        simulationService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public SimulationEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SimulationEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

}
