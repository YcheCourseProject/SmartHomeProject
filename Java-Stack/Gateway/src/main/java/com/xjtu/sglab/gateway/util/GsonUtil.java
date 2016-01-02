package com.xjtu.sglab.gateway.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Jingkai Tang
 * @version 1.0
 * @email jingkaitang@gmail.com
 * @date 2015.04.15 10:24
 */
public class GsonUtil {
    public static Gson create() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).serializeNulls();

        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static JsonObject combineJson(JsonObject j1, JsonObject j2) {
        JsonObject json = new JsonObject();
        for(Map.Entry<String, JsonElement> e : j1.entrySet()) {
            json.add(e.getKey(), e.getValue());
        }
        for (Map.Entry<String, JsonElement> e : j2.entrySet()) {
            json.add(e.getKey(), e.getValue());
        }
        return json;
    }
}

class DateDeserializer implements JsonDeserializer<Date> {

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
    }
}

class DateSerializer implements JsonSerializer<Date> {
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }
}