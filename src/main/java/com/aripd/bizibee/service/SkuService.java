package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SkuEntity;
import javax.ejb.Local;

@Local
public interface SkuService extends CrudService<SkuEntity, Long> {

}
