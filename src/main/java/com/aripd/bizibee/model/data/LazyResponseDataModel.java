package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyResponseDataModel extends LazyAbstractDataModel<ResponseEntity> {

    public LazyResponseDataModel(CrudService crudService) {
        super(crudService);
    }

}
