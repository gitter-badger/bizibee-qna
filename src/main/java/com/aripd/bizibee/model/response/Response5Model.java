package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;
import javax.json.Json;
import javax.json.JsonObject;

public class Response5Model {

    private SkuEntity sku;
    private Integer value;

    public Response5Model(SkuEntity sku) {
        this.sku = sku;
    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        JsonObject model = Json.createObjectBuilder()
                .add("sku", sku.getId())
                .add("value", value)
                .build();
        return model.toString();
//        return "Response5Model{" + "sku=" + sku + ", value=" + value + '}';
    }

}
