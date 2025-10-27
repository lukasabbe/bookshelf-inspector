package com.lukasabbe.bookshelfinspector.network.server;

import com.lukasabbe.bookshelfinspector.BookshelfInspector;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.network.packets.ShelfInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.platform.Services;
import com.lukasabbe.bookshelfinspector.platform.handlers.ServerPayloadHandler;
import com.lukasabbe.bookshelfinspector.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ShelfInventoryRequestServerPayloadHandler implements ServerPayloadHandler<ShelfInventoryRequestPayload> {
    @Override
    public void receive(ShelfInventoryRequestPayload shelfInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getItemInShelf(shelfInventoryRequestPayload.pos(),shelfInventoryRequestPayload.slotNum(), player.level());

        if(stack == null){
            Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), shelfInventoryRequestPayload.pos(), shelfInventoryRequestPayload.slotNum()));
            return;
        }

        Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, shelfInventoryRequestPayload.pos(), shelfInventoryRequestPayload.slotNum()));
    }
}
