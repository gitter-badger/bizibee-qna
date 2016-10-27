package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.TeamEntity;
import javax.ejb.Local;

@Local
public interface TeamService extends CrudService<TeamEntity, Long> {

}
