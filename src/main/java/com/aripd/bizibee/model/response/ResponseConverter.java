package com.aripd.bizibee.model.response;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ResponseConverter {

    static JsonObject convert(Response1Model model) {
        return Json.createObjectBuilder()
                .add("decisionchoice", model.getDecisionchoice().getId())
                .build();
    }

    static JsonObject convert(Response2Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getDecisionchoices().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("decisionchoice", c.getId())
            );
        });

        return Json.createObjectBuilder()
                .add("decisionchoices", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response3Model model) {
        return Json.createObjectBuilder()
                .add("sku", model.getSku().getId())
                .build();
    }

    static JsonObject convert(Response4Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getSkus().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("sku", c.getId())
            );
        });

        return Json.createObjectBuilder()
                .add("skus", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response5Model model) {
        return Json.createObjectBuilder()
                .add("sku", model.getSku().getId())
                .add("value", model.getValue())
                .build();
    }

    static JsonObject convert(Response6Model model) {
        return Json.createObjectBuilder()
                .add("sku", model.getSku().getId())
                .add("decisionchoice", model.getDecisionchoice().getId())
                .build();
    }

    static JsonObject convert(Response7Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getDecisionchoices().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("decisionchoice", c.getId())
            );
        });

        return Json.createObjectBuilder()
                .add("sku", model.getSku().getId())
                .add("decisionchoices", arrayBuilder)
                .build();
    }

}
