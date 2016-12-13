package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.UserEntity;
import javax.ejb.Local;

@Local
public interface ResponseService extends CrudService<ResponseEntity, Long> {

    public ResponseEntity findOneByUserAndDecision(UserEntity user, DecisionEntity decision);

    public void updateOrCreate(UserEntity user, DecisionEntity decision, String outcome);

}
