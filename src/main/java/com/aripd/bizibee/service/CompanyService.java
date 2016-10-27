package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.CompanyEntity;
import javax.ejb.Local;

@Local
public interface CompanyService extends CrudService<CompanyEntity, Long> {

}
