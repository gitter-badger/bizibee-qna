package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;

public class Response1Model {

    private DecisionchoiceEntity decisionchoice;

    public Response1Model() {
    }

    public DecisionchoiceEntity getDecisionchoice() {
        return decisionchoice;
    }

    public void setDecisionchoice(DecisionchoiceEntity decisionchoice) {
        this.decisionchoice = decisionchoice;
    }

    @Override
    public String toString() {
        return ResponseConverter.convert(this).toString();
    }

}
