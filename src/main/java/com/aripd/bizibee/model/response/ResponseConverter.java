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
                    .add("id", model.getAnswer().getId())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder().build();
        }
    }

    static JsonObject convert(Response2Model model) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        model.getAnswers().forEach(c -> {
            arrayBuilder.add(
                    Json.createObjectBuilder()
                            .add("id", c.getId())
            );
        });

        return Json.createObjectBuilder()
                .add("answers", arrayBuilder)
                .build();
    }

    static JsonObject convert(Response5Model model) {
        try {
            return Json.createObjectBuilder()
                    .add("answer", model.getAnswer().getId())
                    .add("value", model.getValue())
                    .build();
        } catch (NullPointerException ex) {
            return Json.createObjectBuilder()
                    .add("answer", model.getAnswer().getId())
                    .add("value", "")
                    .build();
        }
    }

}
