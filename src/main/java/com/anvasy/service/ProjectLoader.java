package com.anvasy.service;

import com.anvasy.model.Locale;
import com.anvasy.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectLoader {

    public static ObjectMapper mapper = new ObjectMapper();

    public static Project createProject(String projectName, String folder, Locale locale) throws Exception {
        createFolderStructure(folder, projectName);
        Project project = createProjectSettings(projectName, folder, locale);
        createNewLocaleJson(locale, project.getDirectory());

        return project;
    }

    public static Project openProject(File file) throws Exception {
        try {
            Project project = mapper.readValue(file, Project.class);
            createFolderStructure(file.getParent());
            return project;
        } catch (IOException e) {
            throw new Exception("Failed to read settings file: ", e);
        }
    }

    public static Project openProject(String directory) throws Exception {
        Path path = Paths.get(directory + "project-settings.json");
        try {
            Project project = mapper.readValue(Files.readString(path), Project.class);
            createFolderStructure(directory);
            return project;
        } catch (IOException e) {
            throw new Exception("Failed to read settings file: ", e);
        }
    }

    private static void createFolderStructure(String folder) throws Exception {
        Path path = Paths.get(folder + "\\languages");
        createFolderStructure(path);
    }

    private static void createFolderStructure(String folder, String projectName) throws Exception {
        Path path = Paths.get(folder + "\\" + projectName + "\\languages");
        createFolderStructure(path);
    }

    private static void createFolderStructure(Path path) throws Exception {
        try {
            Files.createDirectories(path);
            //log.info("Directories created successfully.");
        } catch (IOException e) {
            //log.error("Failed to create directory: ", e);
            throw new Exception("Failed to create folder structure.");
        }
    }

    private static Project createProjectSettings(String projectName, String folder, Locale locale) throws Exception {
        Project settings = new Project(projectName, locale, folder);
        try {
            File outputFile = new File(settings.getDirectory() + "project-settings.json");
            mapper.writeValue(outputFile, settings);

            //log.info("Project settings for {} created successfully.", projectName);
        } catch (IOException e) {
            //log.error("Failed to create project settings: ", e);
            throw new Exception("Failed to create project settings.");
        }

        return settings;
    }

    private static void createNewLocaleJson(Locale locale, String path) throws Exception {
        try {
            Files.createFile(Paths.get(path + "languages\\" + locale.code() + ".json"));
        } catch (IOException e) {
            //log.error("Failed to create locale file for {}: ", locale.code(), e);
            throw new Exception("Failed to create locale file.");
        }
    }

}
