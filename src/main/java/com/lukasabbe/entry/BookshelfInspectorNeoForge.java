package com.lukasabbe.entry;

//? if neoforge {
/*import com.lukasabbe.BookshelfInspector;
import com.lukasabbe.entry.neoforge.EventHandler;
import com.lukasabbe.entry.neoforge.NetworkHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod("template")
public class BookshelfInspectorNeoForge {
    public BookshelfInspectorNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        EventHandler.initServer();
        modEventBus.addListener(NetworkHandler::registerPayloads);
        BookshelfInspector.init();
    }
}
*///?}