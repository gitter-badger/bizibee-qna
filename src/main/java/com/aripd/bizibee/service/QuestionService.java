package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.QuestionEntity;
import javax.ejb.Local;

@Local
public interface QuestionService extends CrudService<QuestionEntity, Long> {

    public QuestionEntity findOneByUuid(String uuid);

}
