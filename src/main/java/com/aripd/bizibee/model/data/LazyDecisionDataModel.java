package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyDecisionDataModel extends LazyAbstractDataModel<DecisionEntity> {

    public LazyDecisionDataModel(CrudService crudService) {
        super(crudService);
    }

}
