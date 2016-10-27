package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.CompanyEntity;
import com.aripd.bizibee.entity.CriteriaoptionEntity;
import com.aripd.bizibee.entity.UserEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CriteriaoptionServiceBean extends CrudServiceBean<CriteriaoptionEntity, Long> implements CriteriaoptionService {

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

    public CriteriaoptionServiceBean() {
        super(CriteriaoptionEntity.class);
    }

}
