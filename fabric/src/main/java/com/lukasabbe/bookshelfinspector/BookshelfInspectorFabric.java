package com.lukasabbe.bookshelfinspector;

import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.network.packets.ShelfInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class BookshelfInspectorFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        PayloadTypeRegistry.serverboundPlay().register(BookShelfInventoryRequestPayload.ID, BookShelfInventoryRequestPayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(LecternInventoryRequestPayload.ID, LecternInventoryRequestPayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ShelfInventoryRequestPayload.ID, ShelfInventoryRequestPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(BookShelfInventoryPayload.ID, BookShelfInventoryPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(ModCheckPayload.ID, ModCheckPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(
                BookShelfInventoryRequestPayload.ID,
                (payload, context) -> BookshelfInspector.networkHandlers.bookShelfInventoryRequestServerPayloadHandler.receive(payload, context.player()));
        ServerPlayNetworking.registerGlobalReceiver(
                LecternInventoryRequestPayload.ID,
                ((payload, context) -> BookshelfInspector.networkHandlers.lecternInventoryRequestServerPayloadHandler.receive(payload, context.player())));
        ServerPlayNetworking.registerGlobalReceiver(
                ShelfInventoryRequestPayload.ID,
                (payload, context) -> BookshelfInspector.networkHandlers.shelfInventoryRequestServerPayloadHandler.receive(payload, context.player()));

        BookshelfInspector.init();
    }
}
