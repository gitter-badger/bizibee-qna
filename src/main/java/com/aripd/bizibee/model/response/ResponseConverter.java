package com.aripd.bizibee.model.response;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ResponseConverter {

    public static JsonObject jsonObjectFromString(String jsonObjectStr) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }

    public static JsonArray jsonArrayFromString(String jsonArrayStr) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonArrayStr));
        JsonArray array = jsonReader.readArray();
        jsonReader.close();
        return array;
    }

    static JsonObject convert(Response1Model model) {
        return Json.createObjectBuilder()
                .add("id", model.getDecisionchoice().getId())
                .add("name", model.getDecisionchoice().getName())
                .add("gm", model.getDecisionchoice().getGm())
                .build();
    }

    static JsonObject convert(Response2Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getDecisionchoices().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
                            .add("gm", c.getGm())
            );
        });

        return Json.createObjectBuilder()
                .add("decisionchoices", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response3Model model) {
        return Json.createObjectBuilder()
                .add("id", model.getSku().getId())
                .add("name", model.getSku().getName())
                .add("gm", model.getSku().getGm())
                .build();
    }

    static JsonObject convert(Response4Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getSkus().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
                            .add("gm", c.getGm())
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
