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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
        return em;
    }

    public ResponseServiceBean() {
        super(ResponseEntity.class);
    }

    @Override
    public List<ResponseEntity> findByUser(UserEntity user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResponseEntity> cq = cb.createQuery(ResponseEntity.class);
        Root<ResponseEntity> root = cq.from(ResponseEntity.class);

        Predicate predicate = cb.equal(root.get(ResponseEntity_.user), user);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public ResponseEntity findOneByUserAndDecision(UserEntity user, DecisionEntity decision) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ResponseEntity> cq = cb.createQuery(ResponseEntity.class);
            Root<ResponseEntity> root = cq.from(ResponseEntity.class);

            Predicate predicate1 = cb.equal(root.get(ResponseEntity_.user), user);
            Predicate predicate2 = cb.equal(root.get(ResponseEntity_.decision), decision);
            cq.where(cb.and(predicate1, predicate2));

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
