package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.Kind;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.QuestionEntity_;
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
public class QuestionServiceBean extends CrudServiceBean<QuestionEntity, Long> implements QuestionService {

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

    public QuestionServiceBean() {
        super(QuestionEntity.class);
    }

    @Override
    public QuestionEntity findOneByUuid(String uuid) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<QuestionEntity> cq = cb.createQuery(QuestionEntity.class);
            Root<QuestionEntity> root = cq.from(QuestionEntity.class);

            Predicate predicate = cb.equal(root.get(QuestionEntity_.uuid), uuid);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<QuestionEntity> findByKinds(List<Kind> kinds) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<QuestionEntity> cq = cb.createQuery(QuestionEntity.class);
        Root<QuestionEntity> root = cq.from(QuestionEntity.class);

        Predicate predicate = root.get(QuestionEntity_.kind).in(kinds);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public int calculateNumberOfQuestionsByKinds(List<Kind> kinds) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<QuestionEntity> root = cq.from(QuestionEntity.class);

            cq.select(cb.count(root));

            Predicate predicate = root.get(QuestionEntity_.kind).in(kinds);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }

    }

}
