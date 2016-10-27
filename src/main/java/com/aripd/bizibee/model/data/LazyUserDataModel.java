package com.aripd.bizibee.model.data;

import com.aripd.bizibee.service.CrudService;
import com.aripd.bizibee.entity.UserEntity;

public class LazyUserDataModel extends LazyAbstractDataModel<UserEntity> {

    public LazyUserDataModel(CrudService crudService) {
        super(crudService);
    }

}
