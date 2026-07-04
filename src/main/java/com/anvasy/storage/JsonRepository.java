package com.anvasy.storage;

import com.anvasy.model.LocalizationEntry;
import com.anvasy.utils.ProjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonRepository implements ProjectRepository {
    //TODO: save order
    @Override
    public List<LocalizationEntry> load(String dir) throws IOException {
        Map<String, LocalizationEntry> locEntries = new HashMap<>();

        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file) && ProjectUtils.validateFileName(file)) {
                    readFileContents(Files.readString(file), locEntries, ProjectUtils.getLocaleFromFileName(file));
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return locEntries.values().stream().toList();
    }

    @Override
    public void save() {

    }

    private void readFileContents(String contents, Map<String, LocalizationEntry> locEntries, String locale) throws JsonProcessingException {
        Map<String, String> raw = new ObjectMapper().readValue(contents, HashMap.class);

        for (Map.Entry<String, String> e : raw.entrySet()) {
            if (locEntries.containsKey(e.getKey())) {
                locEntries.get(e.getKey()).getLocales().put(locale, e.getValue());
            } else {
                locEntries.put(e.getKey(), new LocalizationEntry(e.getKey(), locale, e.getValue()));
            }
        }
    }
}
