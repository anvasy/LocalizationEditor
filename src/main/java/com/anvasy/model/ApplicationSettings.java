package com.anvasy.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationSettings {
    private Theme theme;
    private String lastOpened;
    private List<String> recentProjects;
    private int autosaveInterval;
}
