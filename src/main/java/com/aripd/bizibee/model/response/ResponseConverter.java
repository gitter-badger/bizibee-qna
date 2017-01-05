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
        try {
            return Json.createObjectBuilder()
                    .add("id", model.getDecisionchoice().getId())
                    .add("name", model.getDecisionchoice().getName())
                    .add("budget", model.getDecisionchoice().getBudget())
                    .add("gm", model.getDecisionchoice().getGm())
                    .add("ms", model.getDecisionchoice().getMs())
                    .add("usg", model.getDecisionchoice().getUsg())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder().build();
        }
    }

    static JsonObject convert(Response2Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getDecisionchoices().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
                            .add("budget", c.getBudget())
                            .add("gm", c.getGm())
                            .add("ms", c.getMs())
                            .add("usg", c.getUsg())
            );
        });

        return Json.createObjectBuilder()
                .add("decisionchoices", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response3Model model) {
        try {
            return Json.createObjectBuilder()
                    .add("id", model.getSku().getId())
                    .add("name", model.getSku().getName())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder().build();
        }
    }

    static JsonObject convert(Response4Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getSkus().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
            );
        });

        return Json.createObjectBuilder()
                .add("skus", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response5Model model) {
        try {
            return Json.createObjectBuilder()
                    .add("sku", model.getSku().getId())
                    .add("value", model.getValue())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder()
                    .add("sku", model.getSku().getId())
                    .add("value", "")
                    .build();
        }
    }

    static JsonObject convert(Response6Model model) {
        try {
            return Json.createObjectBuilder()
                    .add("sku", model.getSku().getId())
                    .add("decisionchoice", model.getDecisionchoice().getId())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder()
                    .add("sku", model.getSku().getId())
                    .add("decisionchoice", "")
                    .build();
        }
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
