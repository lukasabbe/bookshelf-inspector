package com.lukasabbe.bookshelfinspector.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {
    String getPlatformName();

    boolean isModLoaded(String modId);

    Path getConfigPath(String file);

    Path getFileOrCopyInModContainer(String mod, String fileName);
}