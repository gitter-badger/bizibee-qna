package com.aripd.util.locale;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleBean implements Serializable {

    private Locale locale;
    private List<Locale> locales;

    public LocaleBean() {
        locales = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        locale = LocaleProvider.getLocale();
        locales = LocaleProvider.getLocales();
    }

    public void onChange(AjaxBehaviorEvent abe) throws IOException {
        Locale newLocale = (Locale) ((UIOutput) abe.getSource()).getValue();
        for (Locale l : locales) {
            if (l.equals(newLocale)) {
                this.doChange(new Locale(l.getLanguage(), l.getCountry()));
                break;
            }
        }
    }

    public String onSet() {
        this.doChange(locale);

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?includeViewParams=true&faces-redirect=true";
    }

    public void doChange(Locale newLocale) {
        locale = newLocale;
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

}
