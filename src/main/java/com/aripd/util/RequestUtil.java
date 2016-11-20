package com.aripd.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public RequestUtil() {
    }

    public static URL getFullAddress() {
        return getFullAddress(null);
    }

    public static URL getFullAddress(String file) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        URL url = null;
        try {
            url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath() + file);
        } catch (MalformedURLException ex) {
            throw new FacesException(ex);
        }
        return url;
    }

    public static String getServerName() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getServerName();
    }

    public static String getViewId() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        return viewId + "?includeViewParams=true&faces-redirect=true";
    }

    public static String getRewrittenURL() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = (String) request.getAttribute("javax.servlet.forward.request_uri");
        return url;
    }

    public static URL getCurrentPageURL() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        URL url = null;
        try {
            url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getAttribute("javax.servlet.forward.request_uri").toString());
        } catch (MalformedURLException ex) {
            throw new FacesException(ex);
        }
        return url;
    }

    public static void doNavigate(String navigation) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(context, null, navigation);
    }

    public static void doRedirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            externalContext.redirect(url);
        } catch (IOException ex) {
            throw new FacesException(ex);
        }
    }

}
