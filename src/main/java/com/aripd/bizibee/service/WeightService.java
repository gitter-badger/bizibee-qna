package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.entity.WeightEntity;
import javax.ejb.Local;

@Local
public interface WeightService extends CrudService<WeightEntity, Long> {

    public WeightEntity findOneByDecisionAndSku(DecisionEntity decision, SkuEntity sku);

}
