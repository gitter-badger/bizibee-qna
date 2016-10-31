package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.PlayerEntity;
import javax.ejb.Local;

@Local
public interface PlayerService extends CrudService<PlayerEntity, Long> {

}
