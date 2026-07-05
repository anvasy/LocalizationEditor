package com.anvasy.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectSettings {
    private String projectName;
    private String directory;
    private Locale primaryLocale;
    private List<Locale> projectLocales;

    public ProjectSettings(String projectName, Locale primaryLocale, String path) {
        this.projectName = projectName;
        this.primaryLocale = primaryLocale;

        directory = path + "\\" + projectName + "\\";

        projectLocales = new ArrayList<>();
        projectLocales.add(primaryLocale);
    }
}
