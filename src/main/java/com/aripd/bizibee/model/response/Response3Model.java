package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;

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
        return ResponseConverter.convert(this).toString();
    }

}
