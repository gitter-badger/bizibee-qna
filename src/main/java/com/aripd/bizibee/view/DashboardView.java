package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.BrandService;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.SimulationService;
import com.aripd.bizibee.service.SkuService;
import com.aripd.bizibee.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

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
    private BrandService brandService;

    @Inject
    private SkuService skuService;

    @Inject
    private DecisionService decisionService;

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

    public int getNumberOfBrands() {
        return brandService.count();
    }

    public int getNumberOfSkus() {
        return skuService.count();
    }

    public int getNumberOfDecisions() {
        return decisionService.count();
    }

    public void doUpdateSimulation(ActionEvent actionEvent) {
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doUploadLogo(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedSimulation.setBytes(file.getContents());
        }
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/ruler/simulation/logo.xhtml?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(context, null, navigation);
    }

    public void doResetLogo(ActionEvent actionEvent) {
        selectedSimulation.setBytes(null);
        simulationService.update(selectedSimulation);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/logo.xhtml?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(context, null, navigation);
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
