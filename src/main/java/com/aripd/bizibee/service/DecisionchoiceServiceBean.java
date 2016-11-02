package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity_;
import com.aripd.bizibee.entity.UserEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class DecisionchoiceServiceBean extends CrudServiceBean<DecisionchoiceEntity, Long> implements DecisionchoiceService {

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

    public DecisionchoiceServiceBean() {
        super(DecisionchoiceEntity.class);
    }

    @Override
    public List<DecisionchoiceEntity> findByDecision(DecisionEntity decision) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DecisionchoiceEntity> cq = cb.createQuery(DecisionchoiceEntity.class);
        Root<DecisionchoiceEntity> root = cq.from(DecisionchoiceEntity.class);

        Predicate predicate = cb.equal(root.get(DecisionchoiceEntity_.decision), decision);
        cq.where(predicate);

        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

}
