package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import java.util.ArrayList;
import java.util.List;

public class Response2Model {

    private List<DecisionchoiceEntity> decisionchoices = new ArrayList<>();

    public Response2Model() {
    }

    public List<DecisionchoiceEntity> getDecisionchoices() {
        return decisionchoices;
    }

    public void setDecisionchoices(List<DecisionchoiceEntity> decisionchoices) {
        this.decisionchoices = decisionchoices;
    }

    @Override
    public String toString() {
        return "Response2Model{" + "decisionchoices=" + decisionchoices + '}';
    }

}
