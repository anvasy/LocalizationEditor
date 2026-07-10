package com.anvasy.service;

import com.anvasy.model.ApplicationSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class ApplicationSettingsService {

    private ApplicationSettings appSettings;
    private final ObjectMapper mapper = new ObjectMapper();

    private static final Path APP_DIRECTORY = Path.of(System.getProperty("user.home"), ".localization-editor");
    private static final Path SETTINGS_FILE = APP_DIRECTORY.resolve("settings.json");

    public List<String> getRecentProjects() {
       return appSettings.getRecentProjects();
    }

    public void addRecentProject(String path) {
        appSettings.getRecentProjects().remove(path);
        appSettings.getRecentProjects().addFirst(path);

        if (appSettings.getRecentProjects().size() > 5) {
            appSettings.getRecentProjects().removeLast();
        }

        save();
    }

    private void load() throws Exception {
        if (Files.exists(SETTINGS_FILE)) {
            appSettings = mapper.readValue(SETTINGS_FILE.toFile(), ApplicationSettings.class);
        } else {
            appSettings = new ApplicationSettings();
            save();
        }
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(SETTINGS_FILE.toFile(), appSettings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
