package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.CompanyEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CompanyServiceBean extends CrudServiceBean<CompanyEntity, Long> implements CompanyService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyServiceBean() {
        super(CompanyEntity.class);
    }

}
