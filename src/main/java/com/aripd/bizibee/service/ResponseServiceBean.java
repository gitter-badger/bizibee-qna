package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.ResponseEntity_;
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
public class ResponseServiceBean extends CrudServiceBean<ResponseEntity, Long> implements ResponseService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserService userService;

    @Override
    protected EntityManager getEntityManager() {
        UserEntity user = userService.getCurrentUser();
        SimulationEntity simulation = user.getSimulation();
        em.setProperty("eclipselink.tenant-id", simulation.getId());
        em.setProperty("eclipselink.tenant-user-id", user.getId());
        return em;
    }

    public ResponseServiceBean() {
        super(ResponseEntity.class);
    }

    @Override
    public ResponseEntity findOneByDecision(DecisionEntity decision) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResponseEntity> cq = cb.createQuery(ResponseEntity.class);
        Root<ResponseEntity> root = cq.from(ResponseEntity.class);

        Predicate predicate = cb.equal(root.get(ResponseEntity_.decision), decision);
        cq.where(predicate);

        Query query = getEntityManager().createQuery(cq);
        List<ResponseEntity> results = query.getResultList();
        ResponseEntity entity = null;
        if (!results.isEmpty()) {
            entity = results.get(0);
        }
        return entity;
    }

    @Override
    public void updateOrCreate(DecisionEntity decision, String outcome) {
        ResponseEntity entity = this.findOneByDecision(decision);
        if (entity != null) {
            entity.setOutcome(outcome);
            this.update(entity);
        } else {
            this.create(new ResponseEntity(decision, outcome));
        }
    }

}
