package com.lukasabbe.entry.neoforge;
//? if neoforge {

/*import com.lukasabbe.renderer.HudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public class HudSubscriber {
    @SubscribeEvent
    public static void initHudLayers(RegisterGuiLayersEvent event){
        event.registerAbove(
                VanillaGuiLayers.CROSSHAIR,
                Identifier.fromNamespaceAndPath("bookshelfinspector", "inspect"),
                (ctx, dt) -> HudRenderer.hudRender(ctx, Minecraft.getInstance())
        );
    }
}

*///?}
