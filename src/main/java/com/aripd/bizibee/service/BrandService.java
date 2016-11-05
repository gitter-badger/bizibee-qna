package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.BrandEntity;
import javax.ejb.Local;

@Local
public interface BrandService extends CrudService<BrandEntity, Long> {

}
