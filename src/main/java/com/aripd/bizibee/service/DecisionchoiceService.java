package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DecisionchoiceService extends CrudService<DecisionchoiceEntity, Long> {

    public List<DecisionchoiceEntity> findByDecision(DecisionEntity decision);

}
