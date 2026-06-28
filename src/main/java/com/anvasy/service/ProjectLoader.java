package com.anvasy.service;

import com.anvasy.storage.ProjectRepository;

import java.io.IOException;

public class ProjectLoader {

    //backend placeholder
    public static void loadProject(ProjectRepository repository) throws IOException {
            repository.load("./testdata/languages/");
    }

    private void createFolderStructure(String path) {

    }

}
