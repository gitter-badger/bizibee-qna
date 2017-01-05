package com.aripd.bizibee.service;

import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AnswerService extends CrudService<AnswerEntity, Long> {

    public List<AnswerEntity> findByQuestion(QuestionEntity question);

}
