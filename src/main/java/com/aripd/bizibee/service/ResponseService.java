package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.UserEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ResponseService extends CrudService<ResponseEntity, Long> {

    public List<ResponseEntity> findByUser(UserEntity user);

    public ResponseEntity findOneByUserAndDecision(UserEntity user, DecisionEntity decision);

}
