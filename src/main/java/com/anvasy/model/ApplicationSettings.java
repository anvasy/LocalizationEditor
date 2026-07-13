package com.anvasy.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationSettings {
    private Theme theme = Theme.DARK;
    private String lastOpened;
    private List<String> recentProjects = new ArrayList<>();
    private int autosaveInterval;
}
