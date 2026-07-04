package com.anvasy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.anvasy.model.Locale;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j  //TODO: configure log file
public class ProjectUtils {

    public static boolean validateFileName(Path file) {
        return true;
    }

    public static String getLocaleFromFileName(Path file) {
        String fileName = file.getFileName().toString();
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    public static List<Locale> getAvailableLocales() {
        try (InputStream is = ProjectUtils.class.getClassLoader().getResourceAsStream("locale/locale.json")) {
            if (is == null) {
                throw new IllegalArgumentException("File locale/locale.json not found in resources.");
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Exception when reading from locale.json", e);
        }

        return new ArrayList<>();
    }
}
