package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        decisionchoices.forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("decisionchoice", c.getId())
            );
        });

        JsonObject model = Json.createObjectBuilder()
                .add("decisionchoices", arrayBuilder)
                .build();
        return model.toString();
//        return "Response2Model{" + "decisionchoices=" + decisionchoices + '}';
    }

}
