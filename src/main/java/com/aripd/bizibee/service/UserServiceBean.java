package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserEntity_;
import com.aripd.bizibee.entity.UserGroup;
import java.security.Principal;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class UserServiceBean extends CrudServiceBean<UserEntity, Long> implements UserService {

    static final Logger LOG = Logger.getLogger(UserServiceBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserServiceBean() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity getCurrentUser() {

        if (this.sessionContext == null) {
            throw new RuntimeException("initialization error, sessionContext must not be null!");
        }

        Principal callerPrincipal = this.sessionContext.getCallerPrincipal();
        if (callerPrincipal == null) {
            throw new RuntimeException("callerPrincipal must not be null, but it is");
        }

        String name = callerPrincipal.getName();
        if (name == null) {
            throw new RuntimeException("could not determine the current user id, because no prinicial in session context");
        }

        return this.findOneByUsername(name);
    }

    /**
     * @param usernameNew String
     * @param username String
     * @return boolean
     */
    @Override
    public boolean isExistByUsernameExceptUsername(String usernameNew, String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.notEqual(root.get(UserEntity_.username), username);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.username), usernameNew);
        Predicate predicate = cb.and(predicate1, predicate2);

        cq.where(predicate);

        TypedQuery<UserEntity> typedQuery = em.createQuery(cq);
        List<UserEntity> resultList = typedQuery.getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public UserEntity findOneByToken(String token) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Expression<String> eConcat = cb.concat(root.get(UserEntity_.password), root.get(UserEntity_.username));
        Expression<String> eToken = cb.function("SHA2", String.class, eConcat, cb.literal("512"));

        Predicate predicate = cb.equal(eToken, token);
        cq.where(predicate);

        Query q = getEntityManager().createQuery(cq);
        List<UserEntity> results = q.getResultList();
        UserEntity entity = null;
        if (!results.isEmpty()) {
            entity = results.get(0);
        }
        return entity;
    }

    @Override
    public UserEntity findOneByUsername(String username) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate = cb.equal(root.get(UserEntity_.username), username);
        cq.where(predicate);

        Query query = getEntityManager().createQuery(cq);
        List<UserEntity> results = query.getResultList();
        UserEntity entity = null;
        if (!results.isEmpty()) {
            entity = results.get(0);
        }
        return entity;
    }

    @Override
    public List<UserEntity> findAllByUserGroup(UserGroup userGroup) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate = cb.equal(root.get(UserEntity_.userGroup), userGroup);
        cq.where(predicate);

        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<UserEntity> findAllBySimulationAndNoTeamAssigned(SimulationEntity simulation) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
        Predicate predicate2 = cb.isNull(root.get(UserEntity_.team));
        cq.where(cb.and(predicate1, predicate2));

        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

}
