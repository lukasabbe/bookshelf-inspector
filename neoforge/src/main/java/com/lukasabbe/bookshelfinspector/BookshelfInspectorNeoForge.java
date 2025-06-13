package com.lukasabbe.bookshelfinspector;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Constants.MOD_ID)
public class BookshelfInspectorNeoForge {

    public BookshelfInspectorNeoForge(IEventBus eventBus) {
        EventHandler.initServer();
        eventBus.addListener(NetworkHandler::registerPayloads);
        BookshelfInspector.init();
    }
}