package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Local
public interface UserService extends CrudService<UserEntity, Long> {

    public UserEntity getCurrentUser();

    public boolean isExistByUsernameExceptUsername(String usernameNew, String username);

    public UserEntity findOneByUsername(String username);

    public UserEntity findOneByUuid(String uuid);

    public List<UserEntity> findAllByUserGroup(UserGroup userGroup);

    public List<UserEntity> findAllBySimulation(SimulationEntity simulation);

    public List<UserEntity> findAllBySimulationAndNoTeamAssigned(SimulationEntity simulation);

    public List<UserEntity> getResultList(SimulationEntity simulation, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public List<UserEntity> getResultList(SimulationEntity simulation, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

    public int count(SimulationEntity simulation, Map<String, Object> filters);

    public Long calculateNumberOfPlayers(SimulationEntity simulation);

}
