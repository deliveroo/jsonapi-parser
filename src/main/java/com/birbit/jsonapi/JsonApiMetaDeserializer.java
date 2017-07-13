package com.birbit.jsonapi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ana on 13/07/17.
 */
@SuppressWarnings("WeakerAccess")
public class JsonApiMetaDeserializer implements JsonDeserializer<JsonApiMeta> {
    public static final JsonApiMetaDeserializer INSTANCE = new JsonApiMetaDeserializer();

    private JsonApiMetaDeserializer() {
    }

    @Override
    public JsonApiMeta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("JsonApiMeta json element must be an object");
        }
        JsonObject asJsonObject = json.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = asJsonObject.entrySet();
        if (entries.isEmpty()) {
            return JsonApiMeta.EMPTY;
        }
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive()) {
                result.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return new JsonApiMeta(result);
    }
}