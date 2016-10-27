package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.CriteriaoptionEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyCriteriaoptionDataModel extends LazyAbstractDataModel<CriteriaoptionEntity> {

    public LazyCriteriaoptionDataModel(CrudService crudService) {
        super(crudService);
    }

}
