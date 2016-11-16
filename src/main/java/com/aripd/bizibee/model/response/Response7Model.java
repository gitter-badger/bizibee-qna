package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Response7Model {

    private SkuEntity sku;
    private List<DecisionchoiceEntity> decisionchoices = new ArrayList<>();

    public Response7Model(SkuEntity sku) {
        this.sku = sku;
    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
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
                .add("sku", sku.getId())
                .add("decisionchoices", arrayBuilder)
                .build();
        return model.toString();
//        return "Response7Model{" + "sku=" + sku + ", decisionchoices=" + decisionchoices + '}';
    }

}
