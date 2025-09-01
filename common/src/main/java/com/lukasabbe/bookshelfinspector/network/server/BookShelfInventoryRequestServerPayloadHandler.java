package com.lukasabbe.bookshelfinspector.network.server;

import com.lukasabbe.bookshelfinspector.BookshelfInspector;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.platform.Services;
import com.lukasabbe.bookshelfinspector.platform.handlers.ServerPayloadHandler;
import com.lukasabbe.bookshelfinspector.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BookShelfInventoryRequestServerPayloadHandler implements ServerPayloadHandler<BookShelfInventoryRequestPayload> {
    @Override
    public void receive(BookShelfInventoryRequestPayload bookShelfInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getBookInChiseledBookShelf(bookShelfInventoryRequestPayload.pos(),bookShelfInventoryRequestPayload.slotNum(), player.level());

        if(stack == null){
            Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), bookShelfInventoryRequestPayload.pos(), bookShelfInventoryRequestPayload.slotNum()));
            return;
        }

        Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, bookShelfInventoryRequestPayload.pos(), bookShelfInventoryRequestPayload.slotNum()));
    }
}
