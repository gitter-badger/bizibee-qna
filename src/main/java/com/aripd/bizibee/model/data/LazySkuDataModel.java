package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.service.CrudService;

public class LazySkuDataModel extends LazyAbstractDataModel<SkuEntity> {

    public LazySkuDataModel(CrudService crudService) {
        super(crudService);
    }

}
