package com.lukasabbe.network.server;
//?if >= 1.21.9 {

import com.lukasabbe.BookshelfInspector;
import com.lukasabbe.ModLoaderAccess;
import com.lukasabbe.handlers.ServerPayloadHandler;
import com.lukasabbe.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.network.packets.ShelfInventoryRequestPayload;
import com.lukasabbe.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ShelfInventoryRequestServerPayloadHandler implements ServerPayloadHandler<ShelfInventoryRequestPayload> {
    @Override
    public void receive(ShelfInventoryRequestPayload shelfInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getItemInShelf(shelfInventoryRequestPayload.pos(),shelfInventoryRequestPayload.slotNum(), player.level());

        if(stack == null){
            ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), shelfInventoryRequestPayload.pos(), shelfInventoryRequestPayload.slotNum()));
            return;
        }

        ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, shelfInventoryRequestPayload.pos(), shelfInventoryRequestPayload.slotNum()));
    }
}
//?}
