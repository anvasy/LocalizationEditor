package com.anvasy.service;

import com.anvasy.model.Locale;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class ProjectLoader {

    public void createProject(String projectName, String folder, Locale defaultLocale) throws IOException {
        System.out.println(projectName + " " + folder + " " + defaultLocale.code());
    }

    private void createFolderStructure(String path) {

    }

}
