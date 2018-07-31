/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.birbit.jsonapi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class JsonApiResponse<T> {
    private final T data;
    private final Map<String, Map<String, ?>> included;
    private final Map<Class, String> typeMapping;
    private final JsonApiLinks links;
    private final JsonApiMeta meta;
    @Nullable private final List<JsonApiError> errors;

    public JsonApiResponse(@Nullable T data, @Nonnull Map<String, Map<String, ?>> included,
                           @Nonnull Map<Class, String> typeMapping, @Nonnull JsonApiLinks links,
                           @Nonnull JsonApiMeta meta) {
        this.data = data;
        this.errors = null;
        this.included = included;
        this.typeMapping = typeMapping;
        this.links = links;
        this.meta = meta;
    }

    public JsonApiResponse(List<JsonApiError> errors, @Nonnull Map<Class, String> typeMapping,
                           @Nonnull JsonApiLinks links, @Nonnull JsonApiMeta meta) {
        this.data = null;
        this.included = Collections.emptyMap();
        this.errors = errors;
        this.typeMapping = typeMapping;
        this.links = links;
        this.meta = meta;
    }

    @Nullable public T getData() {
        return data;
    }

    @Nonnull public <K> Map<String, K> getIncluded(Class<K> type) {
        String mapping = typeMapping.get(type);
        if (mapping == null) {
            return Collections.emptyMap();
        }
        //noinspection unchecked
        Map<String, K> includedMapping = (Map<String, K>) included.get(mapping);
        if (includedMapping == null) {
            return Collections.emptyMap();
        } else {
            return includedMapping;
        }
    }

    public List<JsonApiError> getErrors() {
        return errors;
    }

    @Nullable public <K> K getIncluded(Class<K> type, String id) {
        return getIncluded(type).get(id);
    }

    @Nonnull public Map<String, Map<String, ?>> getIncluded() {
        return included;
    }

    @Nonnull public JsonApiLinks getLinks() {
        return links;
    }

    @Nonnull public JsonApiMeta getMeta() {
        return meta;
    }
}