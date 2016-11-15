package com.aripd.bizibee.model.response;

import com.aripd.bizibee.entity.SkuEntity;
import java.util.ArrayList;
import java.util.List;

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
        return "Response4Model{" + "skus=" + skus + '}';
    }

}
