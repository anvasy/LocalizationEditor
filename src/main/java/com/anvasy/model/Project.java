package com.anvasy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectName;
    private String directory;
    private Locale primaryLocale;
    private List<Locale> projectLocales;

    public Project(String projectName, Locale primaryLocale, String path) {
        this.projectName = projectName;
        this.primaryLocale = primaryLocale;

        directory = path + "\\" + projectName + "\\";

        projectLocales = new ArrayList<>();
        projectLocales.add(primaryLocale);
    }
}
