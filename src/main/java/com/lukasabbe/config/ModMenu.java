package com.lukasabbe.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

//? if fabric {
public class ModMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return ClothConfig::getClothConfigScreen;
    }
}
//?}
