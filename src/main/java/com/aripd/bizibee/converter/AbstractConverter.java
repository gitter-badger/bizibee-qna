package com.aripd.bizibee.converter;

import com.aripd.bizibee.entity.AbstractEntity;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.log4j.Logger;

/**
 *
 * @author cem
 * @param <T> Entity
 */
public abstract class AbstractConverter<T extends AbstractEntity> implements Converter {
    
    static final Logger LOG = Logger.getLogger(AbstractConverter.class.getName());
    
    private final HashMap<String, T> map = new HashMap<>();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        T e = map.get(value);
        LOG.info(e);
        return e;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.equals("")) {
            return "";
        }
        T e = (T) value;
        map.put(e.getId().toString(), e);
        return e.getId().toString();
    }
}
