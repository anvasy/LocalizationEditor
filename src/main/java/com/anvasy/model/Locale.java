package com.anvasy.model;

import lombok.NonNull;

public record Locale(String code, String language, String region, String displayName) {
    @Override
    public @NonNull String toString() {
        return displayName + " — " + code;
    }
}