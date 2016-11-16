package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import java.util.ArrayList;
import java.util.List;

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
        return ResponseConverter.convert(this).toString();
    }

}
