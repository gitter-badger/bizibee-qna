package com.aripd.bizibee.view;

import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    static final Logger LOG = Logger.getLogger(LoginBean.class.getName());

    @Inject
    private UserService userService;
    private UserEntity user;

    private String username;
    private String password;

    @Inject
    MessageUtil messageUtil;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
    }

    /**
     * TODO 403 substatus integration REF: http://en.wikipedia.org/wiki/HTTP_403
     *
     * @param actionEvent ActionEvent
     */
    public void doLogin(ActionEvent actionEvent) {
        user = userService.findOneByUsername(username);
        Date now = new Date();
        if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else if (now.before(user.getSimulation().getDateStart())) {
            messageUtil.addGlobalErrorFlashMessage("Simulation is not started yet");
        } else if (now.after(user.getSimulation().getDateEnd())) {
            messageUtil.addGlobalErrorFlashMessage("Simulation is expired");
        } else if (now.after(user.getSimulation().getDateStart()) && now.before(user.getSimulation().getDateEnd())) {
            login();
        }
    }

    private void login() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(username, password);
            LOG.info(String.format("User (%s) has logged in %s", request.getUserPrincipal().getName(), new Date()));

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
