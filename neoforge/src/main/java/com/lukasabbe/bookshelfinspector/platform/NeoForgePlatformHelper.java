package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.platform.services.IPlatformHelper;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
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
    public Path getFileOrCopyInModContainer(String mod, String fileName) {
        if(ModList.get().getModContainerById(mod).isEmpty()) return null;
        ModContainer container = ModList.get().getModContainerById(mod).get();
        try {
            InputStream inputStream = container.getModInfo().getOwningFile().getFile().getContents().get(fileName).open();
            File targetFile = new File(getConfigPath("bookshelfinspector-config.yml").toUri());
            try(OutputStream outputStream = new FileOutputStream(targetFile)){
                byte[] buffer = new byte[8192];
                int bytesRead;
                while((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, bytesRead);
                }
            }catch (IOException ignore){}
        }catch (IOException ignore){}
        return null;
    }
}