package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.TeamEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeamServiceBean extends CrudServiceBean<TeamEntity, Long> implements TeamService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamServiceBean() {
        super(TeamEntity.class);
    }

}
