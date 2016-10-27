package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.CompanyEntity;
import com.aripd.bizibee.entity.CriteriaEntity;
import com.aripd.bizibee.entity.UserEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CriteriaServiceBean extends CrudServiceBean<CriteriaEntity, Long> implements CriteriaService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserService userService;

    @Override
    protected EntityManager getEntityManager() {
        UserEntity user = userService.getCurrentUser();
        CompanyEntity company = user.getCompany();
        em.setProperty("eclipselink.tenant-id", company.getId());
        return em;
    }

    public CriteriaServiceBean() {
        super(CriteriaEntity.class);
    }

}
