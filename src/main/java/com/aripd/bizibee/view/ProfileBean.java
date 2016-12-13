package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedRecord;

    @Inject
    MessageUtil messageUtil;

    public ProfileBean() {
    }

    @PostConstruct
    private void init() {
        selectedRecord = userService.getCurrentUser();
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        String navigation = "/index.xhtml?faces-redirect=true";
        UserEntity user = userService.getCurrentUser();
        if (selectedRecord.getUsername().equalsIgnoreCase(user.getUsername())) {
            userService.update(selectedRecord);
            messageUtil.addGlobalInfoFlashMessage("Updated");
        } else if (userService.isExistByUsernameExceptUsername(selectedRecord.getUsername(), user.getUsername())) {
            messageUtil.addGlobalErrorFlashMessage("Username {0} is available. Please try another one.", new Object[]{selectedRecord.getUsername()});
        } else {
            userService.update(selectedRecord);
            messageUtil.addGlobalInfoFlashMessage("Updated");

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            try {
                request.logout();

                navigationHandler.handleNavigation(context, null, navigation);
            } catch (ServletException ex) {
                throw new FacesException(ex);
            }
        }
    }

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

}
