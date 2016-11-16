package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import javax.json.Json;
import javax.json.JsonObject;

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
        JsonObject model = Json.createObjectBuilder()
                .add("decisionchoice", decisionchoice.getId())
                .build();
        return model.toString();
//        return "Response1Model{" + "decisionchoice=" + decisionchoice + '}';
    }

}
