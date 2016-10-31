package com.aripd.bizibee.model.data;

import com.aripd.bizibee.service.CrudService;
import com.aripd.bizibee.entity.PlayerEntity;

public class LazyPlayerDataModel extends LazyAbstractDataModel<PlayerEntity> {

    public LazyPlayerDataModel(CrudService crudService) {
        super(crudService);
    }

}
