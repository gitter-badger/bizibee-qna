package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.SimulationEntity_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    @Override
    public SimulationEntity findOneByCode(String code) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SimulationEntity> cq = cb.createQuery(SimulationEntity.class);
            Root<SimulationEntity> root = cq.from(SimulationEntity.class);

            Predicate predicate = cb.equal(root.get(SimulationEntity_.code), code);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
