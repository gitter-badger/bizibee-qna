package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserService extends CrudService<UserEntity, Long> {

    public UserEntity getCurrentUser();

    public boolean isExistByUsernameExceptUsername(String usernameNew, String username);

    public UserEntity findOneByToken(String token);

    public UserEntity findOneByUsername(String username);

    public List<UserEntity> findAllByUserGroup(UserGroup userGroup);

}
