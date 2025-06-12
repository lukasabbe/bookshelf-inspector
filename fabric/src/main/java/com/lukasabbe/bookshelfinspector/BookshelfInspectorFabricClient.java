package com.lukasabbe.bookshelfinspector;

import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class BookshelfInspectorFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(
                BookShelfInventoryPayload.ID,
                ((payload, context) -> BookshelfInspector.networkHandlers.bookShelfInventoryHandlerServer.receive(payload, context.player())));
        ClientPlayNetworking.registerGlobalReceiver(
                ModCheckPayload.ID,
                ((payload, context) -> BookshelfInspector.networkHandlers.modServerPayloadHandler.receive(payload, context.player())));
        BookshelfInspectorClient.clientInit();
    }
}
