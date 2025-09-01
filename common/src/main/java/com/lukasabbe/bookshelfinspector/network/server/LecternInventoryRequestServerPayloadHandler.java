package com.lukasabbe.bookshelfinspector.network.server;

import com.lukasabbe.bookshelfinspector.BookshelfInspector;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.platform.Services;
import com.lukasabbe.bookshelfinspector.platform.handlers.ServerPayloadHandler;
import com.lukasabbe.bookshelfinspector.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LecternInventoryRequestServerPayloadHandler implements ServerPayloadHandler<LecternInventoryRequestPayload> {
    @Override
    public void receive(LecternInventoryRequestPayload lecternInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getBookInLectern(lecternInventoryRequestPayload.pos(), player.level());

        if(stack == null){
            Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), lecternInventoryRequestPayload.pos(), 0));
            return;
        }

        Services.NETWORK_HELPER.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, lecternInventoryRequestPayload.pos(), 0));
    }
}
