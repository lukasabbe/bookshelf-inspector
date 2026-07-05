package com.lukasabbe.entry.neoforge;
//? if neoforge {
/*import com.lukasabbe.BookshelfInspector;
import com.lukasabbe.Constants;
import com.lukasabbe.network.packets.*;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {
    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar(Constants.MOD_ID).optional();
        registrar.playToClient(
                BookShelfInventoryPayload.ID,
                BookShelfInventoryPayload.CODEC,
                ((payload, context) -> BookshelfInspector.networkHandlers.bookShelfInventoryHandlerServer.receive(payload, (LocalPlayer) context.player()))
        );
        registrar.playToClient(
                ModCheckPayload.ID,
                ModCheckPayload.CODEC,
                ((payload, context) -> BookshelfInspector.networkHandlers.modServerPayloadHandler.receive(payload, (LocalPlayer) context.player()))
        );
        registrar.playToServer(
                BookShelfInventoryRequestPayload.ID,
                BookShelfInventoryRequestPayload.CODEC,
                ((payload, context) -> BookshelfInspector.networkHandlers.bookShelfInventoryRequestServerPayloadHandler.receive(payload, (ServerPlayer) context.player()))
        );
        registrar.playToServer(
                LecternInventoryRequestPayload.ID,
                LecternInventoryRequestPayload.CODEC,
                ((payload, context) -> BookshelfInspector.networkHandlers.lecternInventoryRequestServerPayloadHandler.receive(payload, (ServerPlayer) context.player()))
        );
        //?if >= 1.21.9 {
        
        registrar.playToServer(
                ShelfInventoryRequestPayload.ID,
                ShelfInventoryRequestPayload.CODEC,
                ((payload, context) -> BookshelfInspector.networkHandlers.shelfInventoryRequestServerPayloadHandler.receive(payload, (ServerPlayer) context.player()))
        );
        //?}
    }
}
*///?}
