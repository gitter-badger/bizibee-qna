package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;

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
        return ResponseConverter.convert(this).toString();
    }

}
