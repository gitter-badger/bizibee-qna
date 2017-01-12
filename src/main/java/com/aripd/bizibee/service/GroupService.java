package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.GroupEntity;
import javax.ejb.Local;

@Local
public interface GroupService extends CrudService<GroupEntity, Long> {

}
