package com.aripd.util.locale;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;

/**
 * <pre>
 * locale.getCountry()//US
 * locale.getDisplayCountry()//United States
 * locale.getDisplayLanguage()//English
 * locale.getDisplayName()//English (United States)
 * locale.getDisplayScript()
 * locale.getDisplayVariant()
 * locale.getLanguage()//en
 * locale.getScript()
 * locale.getVariant()
 * locale.toLanguageTag()//en-US
 * locale.toString()//en_US
 * </pre>
 *
 * @author cem
 */
public final class LocaleProvider {

    private static final LocaleProvider INSTANCE = new LocaleProvider();

    private final FacesContext facesContext;

    private Locale locale;
    private final List<Locale> locales;

    private LocaleProvider() {
        facesContext = FacesContext.getCurrentInstance();

        locale = (locale == null) ? facesContext.getApplication().getDefaultLocale() : facesContext.getViewRoot().getLocale();

        locales = new ArrayList<>();

        Iterator<Locale> ls = facesContext.getApplication().getSupportedLocales();
        for (Iterator it = ls; it.hasNext();) {
            Locale l = (Locale) it.next();
            locales.add(l);
        }

    }

    public static Locale getLocale() {
        return INSTANCE.locale;
    }

    public static List<Locale> getLocales() {
        return INSTANCE.locales;
    }

}
