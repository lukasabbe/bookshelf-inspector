package com.lukasabbe.bookshelfinspector.config;

import com.lukasabbe.bookshelfinspector.platform.Services;
import org.yaml.snakeyaml.Yaml;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Config class, defines config values.
 */
public class Config {
    public boolean lecternToggle = true;
    public int scale = 10;
    public boolean useRoman = false;

    /**
     * Loads the config file in to mod
     */
    public void loadConfig(){
        Path configPath = Services.PLATFORM.getConfigPath("bookshelfinspector-config.yml");
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
            if(configMap.containsKey("roman")){
                useRoman = (boolean) configMap.get("roman");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void createConfig(Path configPath){
        Path defaultConfigPath = Services.PLATFORM.getFileInModContainer("bookshelfinspector", "bookshelfinspector-config.yml");
        try {
            Files.copy(defaultConfigPath, configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves config settings to config file
     */
    public void saveConfig(){
        Path configPath = Services.PLATFORM.getConfigPath("bookshelfinspector-config.yml");
        if(!Files.exists(configPath)) createConfig(configPath);
        Yaml yaml = new Yaml();
        try{
            Map<String, Object> configMap = yaml.load(new FileReader(configPath.toFile()));
            configMap.put("lectern-toggle",lecternToggle);
            configMap.put("scale",scale);
            configMap.put("roman", useRoman);
            FileWriter writer = new FileWriter(configPath.toString());
            yaml.dump(configMap,writer);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
