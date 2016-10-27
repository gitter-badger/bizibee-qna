package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.ProductEntity;
import javax.ejb.Local;

@Local
public interface ProductService extends CrudService<ProductEntity, Long> {

}
