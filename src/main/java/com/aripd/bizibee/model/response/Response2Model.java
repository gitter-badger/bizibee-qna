package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.AnswerEntity;
import java.util.ArrayList;
import java.util.List;

public class Response2Model {

    private List<AnswerEntity> answers = new ArrayList<>();

    public Response2Model() {
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return ResponseConverter.convert(this).toString();
    }

}
