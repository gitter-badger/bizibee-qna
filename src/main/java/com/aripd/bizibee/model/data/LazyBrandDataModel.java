package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.BrandEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyBrandDataModel extends LazyAbstractDataModel<BrandEntity> {

    public LazyBrandDataModel(CrudService crudService) {
        super(crudService);
    }

}
