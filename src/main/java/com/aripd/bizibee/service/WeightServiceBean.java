package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.entity.WeightEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.WeightEntity_;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class WeightServiceBean extends CrudServiceBean<WeightEntity, Long> implements WeightService {

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

    public WeightServiceBean() {
        super(WeightEntity.class);
    }

    @Override
    public WeightEntity findOneByDecisionAndSku(DecisionEntity decision, SkuEntity sku) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<WeightEntity> cq = cb.createQuery(WeightEntity.class);
            Root<WeightEntity> root = cq.from(WeightEntity.class);

            Predicate predicate1 = cb.equal(root.get(WeightEntity_.decision), decision);
            Predicate predicate2 = cb.equal(root.get(WeightEntity_.sku), sku);
            cq.where(cb.and(predicate1, predicate2));

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
