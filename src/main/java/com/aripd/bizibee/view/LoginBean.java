package com.aripd.bizibee.view;

import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserStatus;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import com.aripd.util.locale.LocaleBean;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.LocaleUtils;
import org.hibernate.validator.constraints.Email;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    static final Logger LOG = Logger.getLogger(LoginBean.class.getName());

    @Inject
    private UserService userService;
    private UserEntity user;

    private String token;

    @Email
    private String username;
    private String password;

    @Inject
    private LocaleBean localeBean;

    @Inject
    MessageUtil messageUtil;

    public LoginBean() {
        LOG.info(String.format("%s is created...", this.getClass().getSimpleName()));
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (token != null) {
            user = userService.findOneByToken(token);

            if (user != null) {
                username = user.getEmail();
                password = user.getPassword();
                login();
            }
        }
    }

    /**
     * TODO 403 substatus integration REF: http://en.wikipedia.org/wiki/HTTP_403
     *
     * @param actionEvent ActionEvent
     */
    public void doLogin(ActionEvent actionEvent) {
        user = userService.findOneByEmail(username);
        if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else if (user.getUserStatus().equals(UserStatus.Unconfirmed)) {
            messageUtil.addGlobalErrorFlashMessage("Your account has not been confirmed yet");
        } else if (user.getUserStatus().equals(UserStatus.Confirmed)) {
            login();
        }
    }

    private void login() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(username, password);
            LOG.info(String.format("User (%s) has logged in %s", request.getUserPrincipal().getName(), new Date()));
            Locale locale = LocaleUtils.toLocale(user.getLocale());
            localeBean.doChange(locale);
            LOG.info(String.format("Locale is set to %s for %s", locale, user.getEmail()));

            String navigation = "/member/index?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } catch (ServletException ex) {
            LOG.error(ex.getMessage());
            messageUtil.addGlobalErrorFlashMessage("The username or password you provided does not match our records");
        }
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            LOG.info(String.format("User (%s) has logged out %s", request.getUserPrincipal().getName(), new Date()));
            request.logout();
        } catch (ServletException ex) {
            LOG.error(ex.getMessage());
            messageUtil.addGlobalErrorFlashMessage("Logout failed");
        }
        return "/index?faces-redirect=true";
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
