package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyResponseDataModel;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class ResponseController implements Serializable {

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;
    private List<ResponseEntity> selectedRecords;
    private LazyDataModel<ResponseEntity> lazyModel;

    @Inject
    private UserService userService;
    private UserEntity selectedPlayer;
    private LazyDataModel<UserEntity> lazyModelPlayer;

    @Inject
    MessageUtil messageUtil;

    public ResponseController() {
        selectedRecord = new ResponseEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyResponseDataModel(responseService);

        UserEntity user = userService.getCurrentUser();
        SimulationEntity simulation = user.getSimulation();
        lazyModelPlayer = new LazyUserDataModelBySimulation(userService, simulation);
    }

    public ResponseEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ResponseEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<ResponseEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<ResponseEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public LazyDataModel<ResponseEntity> getLazyModel() {
        return lazyModel;
    }

    public LazyDataModel<UserEntity> getLazyModelPlayer() {
        return lazyModelPlayer;
    }

    public UserEntity getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(UserEntity selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

}
