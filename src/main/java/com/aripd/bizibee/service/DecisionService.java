package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import javax.ejb.Local;

@Local
public interface DecisionService extends CrudService<DecisionEntity, Long> {

    public DecisionEntity findOneByUuid(String uuid);

}
