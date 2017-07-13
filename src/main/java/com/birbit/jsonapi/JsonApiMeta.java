package com.birbit.jsonapi;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ana on 13/07/17.
 * <p>
 * A meta object defined as in http://jsonapi.org/format/#document-meta
 */
public class JsonApiMeta {
    public static final JsonApiMeta EMPTY = new JsonApiMeta(Collections.<String, Object>emptyMap());

    private Map<String, Object> meta;

    public JsonApiMeta(@NonNull Map<String, Object> meta) {
        this.meta = meta;
    }

    @NonNull public Map<String, Object> getMeta() {
        return meta;
    }

    @Nullable public Object get(String name) {
        return meta.get(name);
    }
}