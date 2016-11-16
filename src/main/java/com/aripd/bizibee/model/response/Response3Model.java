package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;
import javax.json.Json;
import javax.json.JsonObject;

public class Response3Model {

    private SkuEntity sku;

    public Response3Model() {
    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        JsonObject model = Json.createObjectBuilder()
                .add("sku", sku.getId())
                .build();
        return model.toString();
//        return "Response3Model{" + "sku=" + sku + '}';
    }

}
