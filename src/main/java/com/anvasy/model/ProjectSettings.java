package com.anvasy.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectSettings {
    private String projectName;
    private Locale primaryLocale;
    private List<Locale> projectLocales;

    public ProjectSettings(String projectName, Locale primaryLocale) {
        this.projectName = projectName;
        this.primaryLocale = primaryLocale;
    }
}
