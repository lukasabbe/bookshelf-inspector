package com.lukasabbe.bookshelfinspector;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Constants.MOD_ID)
public class BookshelfInspectorNeoForge {

    public BookshelfInspectorNeoForge(IEventBus eventBus) {
        EventHandler.init();
        eventBus.addListener(NetworkHandler::registerPayloads);
        BookshelfInspector.init();
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> ClothConfigGenerator.createConfig(parent));
    }
}