package com.aripd.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class MessageUtil implements Serializable {

    public MessageUtil() {
    }

    public void addGlobalCustomFlashMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;
        String summary = message;
        String detail = message;
        context.addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void addGlobalErrorFlashMessages(List<String> messages) {
        for (String message : messages) {
            addGlobalErrorFlashMessage(message);
        }
    }

    public void addGlobalErrorFlashMessages(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addGlobalErrorFlashMessage(msg);
        } else {
            addGlobalErrorFlashMessage(defaultMsg);
        }
    }

    public void addGlobalErrorFlashMessage(String message) {
        addGlobalErrorFlashMessage(message, null);
    }

    public void addGlobalErrorFlashMessage(String message, Object[] obj) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Resources", locale);

        FacesMessage.Severity severity = FacesMessage.SEVERITY_ERROR;
        String summary = MessageFormat.format(rb.getString("Error"), obj);
        String detail = MessageFormat.format(rb.getString(message), obj);
        context.addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void addGlobalInfoFlashMessage(String message) {
        addGlobalInfoFlashMessage(message, null);
    }

    public void addGlobalInfoFlashMessage(String message, Object[] obj) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Resources", locale);

        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        String summary = MessageFormat.format(rb.getString("Info"), obj);
        String detail = MessageFormat.format(rb.getString(message), obj);
        context.addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String getMailMessage(Locale locale, String message) {
        return getMailMessage(locale, message, null);
    }

    public String getMailMessage(Locale locale, String message, Object[] obj) {
        ResourceBundle rb = ResourceBundle.getBundle("Mail", locale);
        return MessageFormat.format(rb.getString(message), obj);
    }

    public String getI18nResource(String message) {
        return getI18nResource(message, null);
    }

    public String getI18nResource(String message, Object[] obj) {
        FacesContext context = FacesContext.getCurrentInstance();

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Resources", locale);

        return MessageFormat.format(rb.getString(message), obj);
    }

    public String getRequestParameter(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getRequestParameterMap().get(key);
    }

    public Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

}
