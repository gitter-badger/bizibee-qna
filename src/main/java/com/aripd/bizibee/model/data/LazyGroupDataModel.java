package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.GroupEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyGroupDataModel extends LazyAbstractDataModel<GroupEntity> {

    public LazyGroupDataModel(CrudService crudService) {
        super(crudService);
    }

}
