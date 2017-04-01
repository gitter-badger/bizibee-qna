package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.Kind;
import com.aripd.bizibee.entity.QuestionEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface QuestionService extends CrudService<QuestionEntity, Long> {

    public QuestionEntity findOneByUuid(String uuid);

    public List<QuestionEntity> findByKinds(List<Kind> kinds);

    public int calculateNumberOfQuestionsByKinds(List<Kind> kinds);

}
