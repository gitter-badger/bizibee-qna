package com.aripd.bizibee.view;

import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import com.aripd.util.helper.CookieHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    public static final String COOKIE_NAME = "remember";
    public static final int COOKIE_AGE = 2592000;// 30 days

    @Inject
    private UserService userService;
    private UserEntity user;

    private String username;
    private String password;
    private boolean remember;

    @Inject
    MessageUtil messageUtil;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
    }

    /**
     * @param actionEvent ActionEvent
     */
    public void doLogin(ActionEvent actionEvent) {
        user = userService.findOneByUsername(username);
        Date now = new Date();
        if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else {
            switch (user.getUserGroup()) {
                case Administrators:
                    login();
                    break;
                case Rulers:
                case Players:
                    if (now.before(user.getSimulation().getDateStart())) {
                        messageUtil.addGlobalErrorFlashMessage("Simulation is not started yet");
                    } else if (now.after(user.getSimulation().getDateEnd())) {
                        messageUtil.addGlobalErrorFlashMessage("Simulation is expired");
                    } else if (now.after(user.getSimulation().getDateStart()) && now.before(user.getSimulation().getDateEnd())) {
                        login();
                    }
                    break;
            }
        }
    }

    private void login() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            request.login(username, password);

            if (remember) {
                String uuid = UUID.randomUUID().toString();
                user.setUuid(uuid);
                userService.update(user);
                CookieHelper.addCookie(response, COOKIE_NAME, uuid, COOKIE_AGE);
            } else {
                user.setUuid(null);
                userService.update(user);
                CookieHelper.removeCookie(response, COOKIE_NAME);
            }

            String navigation = "/login?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } catch (ServletException ex) {
            messageUtil.addGlobalErrorFlashMessage("The username or password you provided does not match our records");
//            throw new FacesException(ex);
        }
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            user = userService.findOneByUsername(request.getUserPrincipal().getName());
            user.setUuid(null);
            userService.update(user);
            CookieHelper.removeCookie(response, COOKIE_NAME);

            request.logout();
        } catch (ServletException ex) {
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
