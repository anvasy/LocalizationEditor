package com.anvasy.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class LocalizationEntry {
    private String key;
    private Map<String, String> locales;
    private Metadata metadata;

    public LocalizationEntry(String key, String locale, String value) {
        this.key = key;
        locales = new LinkedHashMap<>();
        locales.put(locale, value);
    }
}
