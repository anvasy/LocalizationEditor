package com.anvasy.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectSettings {
    Locale primaryLocale;
    List<Locale> projectLocales;
}
