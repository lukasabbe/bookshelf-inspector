package com.lukasabbe.bookshelfinspector;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class BookshelfInspectorNeoForgeClient {
    public BookshelfInspectorNeoForgeClient(IEventBus bus) {
        BookshelfInspectorClient.clientInit();
    }
}
