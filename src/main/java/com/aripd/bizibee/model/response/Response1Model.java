package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.AnswerEntity;

public class Response1Model {

    private AnswerEntity answer;

    public Response1Model() {
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return ResponseConverter.convert(this).toString();
    }

}
