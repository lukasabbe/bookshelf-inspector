package me.lukasabbe.bookshelfinspector.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class Config {
    public boolean lecternToggle = true;
    public int scale = 10;

    public void loadConfig(){
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("bookshelfinspector-config.yml");
        if(!Files.exists(configPath))createConfig(configPath);
        Yaml yaml = new Yaml();
        try{
            Map<String, Object> configMap = yaml.load(new FileReader(configPath.toFile()));
            if(configMap.containsKey("lectern-toggle")){
                lecternToggle = (boolean) configMap.get("lectern-toggle");
            }
            if(configMap.containsKey("scale")){
                scale = (int) configMap.get("scale");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void createConfig(Path configPath){
        FabricLoader.getInstance().getModContainer("bookshelfinspector").ifPresent(modContainer -> {
            Path path = modContainer.findPath("bookshelfinspector-config.yml").orElseThrow();
            try {
                Files.copy(path,configPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void saveConfig(){
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("bookshelfinspector-config.yml");
        if(!Files.exists(configPath))createConfig(configPath);
        Yaml yaml = new Yaml();
        try{
            Map<String, Object> configMap = yaml.load(new FileReader(configPath.toFile()));
            configMap.put("lectern-toggle",lecternToggle);
            configMap.put("scale",scale);
            FileWriter writer = new FileWriter(configPath.toString());
            yaml.dump(configMap,writer);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
