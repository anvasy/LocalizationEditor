package com.anvasy.storage;

import com.anvasy.model.LocalizationEntry;

import java.io.IOException;
import java.util.List;

public interface ProjectRepository {
    public List<LocalizationEntry> load(String dir) throws IOException;
    public void save();
}
