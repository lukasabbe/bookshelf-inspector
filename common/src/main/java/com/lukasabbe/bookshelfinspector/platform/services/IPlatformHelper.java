package com.lukasabbe.bookshelfinspector.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {
    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    Path getConfigPath(String file);

    Path getFileInModContainer(String mod, String fileName);

    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }
}