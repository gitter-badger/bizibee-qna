package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.TeamEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyTeamDataModel extends LazyAbstractDataModel<TeamEntity> {

    public LazyTeamDataModel(CrudService crudService) {
        super(crudService);
    }

}
