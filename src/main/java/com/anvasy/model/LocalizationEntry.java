package com.anvasy.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LocalizationEntry {
    String key;
    Map<String, String> locales; //TODO: fixed list of locales
    Metadata metadata;

    public LocalizationEntry(String key, String locale, String value) {
        this.key = key;
        locales = new HashMap<>();
        locales.put(locale, value);
    }
}
