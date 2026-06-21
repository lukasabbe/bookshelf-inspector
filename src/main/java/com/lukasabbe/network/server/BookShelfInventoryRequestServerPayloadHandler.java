package com.lukasabbe.network.server;

import com.lukasabbe.BookshelfInspector;
import com.lukasabbe.ModLoaderAccess;
import com.lukasabbe.handlers.ServerPayloadHandler;
import com.lukasabbe.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.network.packets.BookShelfInventoryRequestPayload;
import com.lukasabbe.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BookShelfInventoryRequestServerPayloadHandler implements ServerPayloadHandler<BookShelfInventoryRequestPayload> {
    @Override
    public void receive(BookShelfInventoryRequestPayload bookShelfInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getBookInChiseledBookShelf(bookShelfInventoryRequestPayload.pos(),bookShelfInventoryRequestPayload.slotNum(), player.level());

        if(stack == null){
            ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), bookShelfInventoryRequestPayload.pos(), bookShelfInventoryRequestPayload.slotNum()));
            return;
        }

        ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, bookShelfInventoryRequestPayload.pos(), bookShelfInventoryRequestPayload.slotNum()));
    }
}
