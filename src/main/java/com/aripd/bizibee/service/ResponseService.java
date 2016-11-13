package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import javax.ejb.Local;

@Local
public interface ResponseService extends CrudService<ResponseEntity, Long> {

    public ResponseEntity findOneByDecision(DecisionEntity decision);

    public void updateOrCreate(DecisionEntity selectedRecord, String toString);

}
