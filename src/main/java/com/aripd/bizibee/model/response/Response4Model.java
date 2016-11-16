package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Response4Model {

    private List<SkuEntity> skus = new ArrayList<>();

    public Response4Model() {
    }

    public List<SkuEntity> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuEntity> skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        skus.forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("sku", c.getId())
            );
        });

        JsonObject model = Json.createObjectBuilder()
                .add("skus", arrayBuilder)
                .build();
        return model.toString();
//        return "Response4Model{" + "skus=" + skus + '}';
    }

}
