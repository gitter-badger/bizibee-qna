package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.service.CrudService;

public class LazySimulationDataModel extends LazyAbstractDataModel<SimulationEntity> {

    public LazySimulationDataModel(CrudService crudService) {
        super(crudService);
    }

}
