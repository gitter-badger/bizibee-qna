package com.aripd.bizibee.converter;

import com.aripd.bizibee.entity.DecisionType;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DecisionTypeConverter extends EnumConverter {

    public DecisionTypeConverter() {
        super(DecisionType.class);
    }

}
