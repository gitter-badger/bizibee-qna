package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SimulationServiceBean extends CrudServiceBean<SimulationEntity, Long> implements SimulationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SimulationServiceBean() {
        super(SimulationEntity.class);
    }

}
