package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.service.CrudService;

public class LazyQuestionDataModel extends LazyAbstractDataModel<QuestionEntity> {

    public LazyQuestionDataModel(CrudService crudService) {
        super(crudService);
    }

}
