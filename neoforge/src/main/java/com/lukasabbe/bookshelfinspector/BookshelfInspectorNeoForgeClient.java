package com.lukasabbe.bookshelfinspector;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class BookshelfInspectorNeoForgeClient {
    public BookshelfInspectorNeoForgeClient(IEventBus bus) {
        EventHandler.initClient();
        BookshelfInspectorClient.clientInit();
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> ClothConfigGenerator.createConfig(parent));
    }
}
