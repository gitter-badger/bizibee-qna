package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.GuideEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyGuideDataModel extends LazyAbstractDataModel<GuideEntity> {

    public LazyGuideDataModel(CrudService crudService) {
        super(crudService);
    }

}
