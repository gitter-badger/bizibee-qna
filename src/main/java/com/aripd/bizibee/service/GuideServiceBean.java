package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.GuideEntity;
import com.aripd.bizibee.entity.UserEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GuideServiceBean extends CrudServiceBean<GuideEntity, Long> implements GuideService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserService userService;

    @Override
    protected EntityManager getEntityManager() {
        UserEntity user = userService.getCurrentUser();
        SimulationEntity simulation = user.getSimulation();
        em.setProperty("eclipselink.tenant-id", simulation.getId());
        return em;
    }

    public GuideServiceBean() {
        super(GuideEntity.class);
    }

}
