package com.anvasy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectLoader {

    public void createProject(Path folder) throws IOException {
        Files.createDirectory(folder);
    }

    private void createFolderStructure(String path) {

    }

}
