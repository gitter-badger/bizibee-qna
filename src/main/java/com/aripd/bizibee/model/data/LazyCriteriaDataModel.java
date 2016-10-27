package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.CriteriaEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyCriteriaDataModel extends LazyAbstractDataModel<CriteriaEntity> {

    public LazyCriteriaDataModel(CrudService crudService) {
        super(crudService);
    }

}
