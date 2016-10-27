package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.ProductEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyProductDataModel extends LazyAbstractDataModel<ProductEntity> {

    public LazyProductDataModel(CrudService crudService) {
        super(crudService);
    }

}
