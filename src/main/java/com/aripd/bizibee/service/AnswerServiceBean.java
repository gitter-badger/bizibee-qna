package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.AnswerEntity_;
import com.aripd.bizibee.entity.UserEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class AnswerServiceBean extends CrudServiceBean<AnswerEntity, Long> implements AnswerService {

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

    public AnswerServiceBean() {
        super(AnswerEntity.class);
    }

    @Override
    public List<AnswerEntity> findByQuestion(QuestionEntity question) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AnswerEntity> cq = cb.createQuery(AnswerEntity.class);
        Root<AnswerEntity> root = cq.from(AnswerEntity.class);

        Predicate predicate = cb.equal(root.get(AnswerEntity_.question), question);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

}
