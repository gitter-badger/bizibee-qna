package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import javax.json.Json;
import javax.json.JsonObject;

public class Response6Model {

    private SkuEntity sku;
    private DecisionchoiceEntity decisionchoice;

    public Response6Model(SkuEntity sku) {
        this.sku = sku;
    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
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
                .add("sku", sku.getId())
                .add("decisionchoice", decisionchoice.getId())
                .build();
        return model.toString();
//        return "Response6Model{" + "sku=" + sku + ", decisionchoice=" + decisionchoice + '}';
    }

}
