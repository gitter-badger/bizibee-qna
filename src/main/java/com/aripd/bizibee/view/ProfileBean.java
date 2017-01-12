package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.UserService;
import com.aripd.util.RequestUtil;
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
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedRecord;

    private UploadedFile file;

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

    public void doUploadImage(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedRecord.setBytes(file.getContents());
        }
        userService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/player/profile?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doResetImage(ActionEvent actionEvent) {
        selectedRecord.setBytes(null);
        userService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/player/profile?faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
