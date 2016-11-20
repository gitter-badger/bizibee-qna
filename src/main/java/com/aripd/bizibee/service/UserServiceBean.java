package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserEntity_;
import com.aripd.bizibee.entity.UserGroup;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Stateless
public class UserServiceBean extends CrudServiceBean<UserEntity, Long> implements UserService {

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
    public UserEntity findOneByUuid(String uuid) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate = cb.equal(root.get(UserEntity_.uuid), uuid);
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
    public List<UserEntity> findAllBySimulation(SimulationEntity simulation) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate = cb.equal(root.get(UserEntity_.simulation), simulation);
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

    @Override
    public List<UserEntity> getResultList(SimulationEntity simulation, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.userGroup), UserGroup.Players);
        Predicate predicate3 = this.getFilterCondition(cb, root, filters);

        Predicate predicate = cb.and(predicate1, predicate2, predicate3);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.orderBy(cb.desc(root.get(UserEntity_.id)));

        Query q = em.createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public List<UserEntity> getResultList(SimulationEntity simulation, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.userGroup), UserGroup.Players);
        Predicate predicate3 = this.getFilterCondition(cb, root, filters);

        Predicate predicate = cb.and(predicate1, predicate2, predicate3);
        cq.where(predicate);

        if (multiSortMeta != null) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta sortMeta : multiSortMeta) {
                String sortField = sortMeta.getSortField();
                SortOrder sortOrder = sortMeta.getSortOrder();
                if (sortField != null) {
                    Order order = null;
                    if (sortOrder == SortOrder.ASCENDING) {
                        order = cb.asc(root.get(sortField));
                    } else if (sortOrder == SortOrder.DESCENDING) {
                        order = cb.desc(root.get(sortField));
                    }
                    orders.add(order);
                }
            }
            cq.orderBy(orders);
        }

        Query q = em.createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(SimulationEntity simulation, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.userGroup), UserGroup.Players);
        Predicate predicate3 = this.getFilterCondition(cb, root, filters);

        Predicate predicate = cb.and(predicate1, predicate2, predicate3);
        cq.where(predicate);

        cq.select(cb.count(root));

        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public int calculateNumberOfTeams(SimulationEntity simulation) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<UserEntity> root = cq.from(UserEntity.class);

        Expression<String> eTeam = root.get(UserEntity_.teamName);
        Expression<Long> eCount = cb.count(eTeam);
        cq.multiselect(eTeam.alias("TEAM"), eCount.alias("COUNT"));
        cq.groupBy(eTeam);

        Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.userGroup), UserGroup.Players);
        Predicate predicate3 = cb.isNotNull(root.get(UserEntity_.teamName));
        cq.where(cb.and(predicate1, predicate2, predicate3));

        TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(cq);
        List<Tuple> list = typedQuery.getResultList();
        return list.size();
    }

    @Override
    public int calculateNumberOfPlayers(SimulationEntity simulation) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<UserEntity> root = cq.from(UserEntity.class);

            cq.select(cb.count(root));

            Predicate predicate1 = cb.equal(root.get(UserEntity_.simulation), simulation);
            Predicate predicate2 = cb.equal(root.get(UserEntity_.userGroup), UserGroup.Players);
            cq.where(cb.and(predicate1, predicate2));

            return em.createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

}
