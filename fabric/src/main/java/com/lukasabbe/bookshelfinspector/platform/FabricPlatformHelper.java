package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path getConfigPath(String file) {
        return FabricLoader.getInstance().getConfigDir().resolve(file);
    }

    @Override
    public Path getFileInModContainer(String mod, String fileName) {
        if(FabricLoader.getInstance().getModContainer(mod).isEmpty()) return null;
        ModContainer modContainer = FabricLoader.getInstance().getModContainer(mod).get();
        return modContainer.findPath(fileName).orElseThrow();
    }
}
