package com.anvasy.utils;

import java.nio.file.Path;

public class FileUtils {

    public static boolean validateFileName(Path file) {
        return true;
    }

    public static String getLocaleFromFileName(Path file) {
        String fileName = file.getFileName().toString();
        return fileName.replaceFirst("[.][^.]+$", "");
    }
}
