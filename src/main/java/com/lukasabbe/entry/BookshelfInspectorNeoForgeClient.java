package com.lukasabbe.entry;
//? if neoforge {
/*import com.lukasabbe.BookshelfInspectorClient;
import com.lukasabbe.Constants;
import com.lukasabbe.config.ClothConfig;
import com.lukasabbe.entry.neoforge.EventHandler;
import com.lukasabbe.entry.neoforge.HudSubscriber;
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
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> ClothConfig.getClothConfigScreen(parent));
        bus.addListener(HudSubscriber::initHudLayers);
    }
}
*///?}

