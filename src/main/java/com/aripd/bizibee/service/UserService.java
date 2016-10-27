package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import com.aripd.bizibee.flow.register.RegisterModel;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserService extends CrudService<UserEntity, Long> {

    public UserEntity register(RegisterModel registerModel);

    public UserEntity getCurrentUser();

    public boolean isExistByEmailExceptEmail(String emailNew, String email);

    public UserEntity findOneByToken(String token);

    public UserEntity findOneByEmail(String email);

    public List<UserEntity> findAllByUserGroup(UserGroup userGroup);

}
