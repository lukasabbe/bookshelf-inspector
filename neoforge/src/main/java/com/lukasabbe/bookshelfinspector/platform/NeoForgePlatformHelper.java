package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.platform.services.IPlatformHelper;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public Path getConfigPath(String file) {
        return FMLPaths.CONFIGDIR.get().resolve(file);
    }

    @Override
    public Path getFileInModContainer(String mod, String fileName) {
        if(ModList.get().getModContainerById(mod).isEmpty()) return null;
        ModContainer container = ModList.get().getModContainerById(mod).get();
        return Paths.get(container.getModInfo().getOwningFile().getFile().getContents().findFile(fileName).get());
    }
}