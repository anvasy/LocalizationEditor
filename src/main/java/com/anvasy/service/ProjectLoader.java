package com.anvasy.service;

import com.anvasy.model.Locale;
import com.anvasy.model.ProjectSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ProjectLoader {

    public void createProject(String projectName, String folder, Locale locale) throws Exception {
        createFolderStructure(folder, projectName);
        ProjectSettings settings = createProjectSettings(projectName, folder, locale);
        createNewLocaleJson(locale, settings.getDirectory());
    }

    private void createFolderStructure(String folder, String projectName) throws Exception {
        Path path = Paths.get(folder + "\\" + projectName + "\\languages");

        try {
            Files.createDirectories(path);
            log.info("Directories for {} created successfully.", projectName);
        } catch (IOException e) {
            log.error("Failed to create directory: ", e);
            throw new Exception("Failed to create folder structure.");
        }
    }

    private ProjectSettings createProjectSettings(String projectName, String folder, Locale locale) throws Exception {
        ProjectSettings settings = new ProjectSettings(projectName, locale, folder);

        ObjectMapper mapper = new ObjectMapper();

        try {
            File outputFile = new File(settings.getDirectory() + "project-settings.json");
            mapper.writeValue(outputFile, settings);

            log.info("Project settings for {} created successfully.", projectName);
        } catch (IOException e) {
            log.error("Failed to create project settings: ", e);
            throw new Exception("Failed to create project settings.");
        }

        return settings;
    }

    private void createNewLocaleJson(Locale locale, String path) throws Exception {
        try {
            Files.createFile(Paths.get(path + "languages\\" + locale.code() +".json"));
        } catch (IOException e) {
            log.error("Failed to create locale file for {}: ", locale.code(), e);
            throw new Exception("Failed to create locale file.");
        }
    }

}
