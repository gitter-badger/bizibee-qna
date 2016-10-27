package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.CompanyEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyCompanyDataModel extends LazyAbstractDataModel<CompanyEntity> {

    public LazyCompanyDataModel(CrudService crudService) {
        super(crudService);
    }

}
