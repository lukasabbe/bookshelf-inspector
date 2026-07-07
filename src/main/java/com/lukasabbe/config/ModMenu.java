package com.lukasabbe.config;

//? if fabric && <= 26.2 {

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return ClothConfig::getClothConfigScreen;
    }
}
 
//?}
