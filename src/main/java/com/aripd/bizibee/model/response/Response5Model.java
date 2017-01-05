package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.AnswerEntity;

public class Response5Model {

    private AnswerEntity answer;
    private Integer value;

    public Response5Model(AnswerEntity answer) {
        this.answer = answer;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ResponseConverter.convert(this).toString();
    }

}
