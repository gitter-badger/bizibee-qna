package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;

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
        return ResponseConverter.convert(this).toString();
    }

}
