package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.service.UserService;
import com.aripd.util.locale.LocaleBean;
import java.io.Serializable;
import java.util.Locale;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.LocaleUtils;

@Named
@ViewScoped
public class ProfileBean implements Serializable {

    static final Logger LOG = Logger.getLogger(ProfileBean.class.getName());

    @Inject
    private UserService userService;
    private UserEntity selectedRecord;

    @Inject
    private LocaleBean localeBean;

    @Inject
    MessageUtil messageUtil;

    public ProfileBean() {
    }

    @PostConstruct
    private void init() {
        selectedRecord = userService.getCurrentUser();
    }

    public void doUpdateLocale(AjaxBehaviorEvent abe) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();

        userService.update(selectedRecord);

        Locale locale = LocaleUtils.toLocale(selectedRecord.getLocale());
        localeBean.doChange(locale);
        LOG.info(String.format("Locale has been set to {0} for {1}", locale, selectedRecord.getEmail()));

        String navigation = "/member/profile.xhtml?faces-redirect=true";
        navigationHandler.handleNavigation(context, null, navigation);
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        String navigation = "/index.xhtml?faces-redirect=true";
        UserEntity user = userService.getCurrentUser();
        if (selectedRecord.getEmail().equalsIgnoreCase(user.getEmail())) {
            userService.update(selectedRecord);

            if (!selectedRecord.getLocale().equalsIgnoreCase(user.getLocale())) {
                Locale locale = LocaleUtils.toLocale(selectedRecord.getLocale());
                localeBean.doChange(locale);
                LOG.info(String.format("Locale has been set to {0} for {1}", locale, selectedRecord.getEmail()));

                navigation = "/member/profile.xhtml?faces-redirect=true";
                navigationHandler.handleNavigation(context, null, navigation);
            }

            messageUtil.addGlobalInfoFlashMessage("Updated");
        } else if (userService.isExistByEmailExceptEmail(selectedRecord.getEmail(), user.getEmail())) {
            messageUtil.addGlobalErrorFlashMessage("E-mail address {0} is available. Please try another one.", new Object[]{selectedRecord.getEmail()});
        } else {
            userService.update(selectedRecord);

            Locale locale = LocaleUtils.toLocale(selectedRecord.getLocale());
            localeBean.doChange(locale);
            LOG.info(String.format("Locale has been set to {0} for {1}", locale, selectedRecord.getEmail()));

            messageUtil.addGlobalInfoFlashMessage("Updated");

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            try {
                request.logout();

                navigationHandler.handleNavigation(context, null, navigation);
            } catch (ServletException ex) {
                LOG.error(ex.getMessage());
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
