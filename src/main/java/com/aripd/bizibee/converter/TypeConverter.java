package com.aripd.bizibee.converter;

import com.aripd.bizibee.entity.Type;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TypeConverter extends EnumConverter {

    public TypeConverter() {
        super(Type.class);
    }

}
