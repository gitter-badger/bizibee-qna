package com.aripd.bizibee.converter;

import com.aripd.bizibee.entity.Kind;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class KindConverter extends EnumConverter {

    public KindConverter() {
        super(Kind.class);
    }

}
