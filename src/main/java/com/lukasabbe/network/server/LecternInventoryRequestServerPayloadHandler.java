package com.lukasabbe.network.server;

import com.lukasabbe.BookshelfInspector;
import com.lukasabbe.ModLoaderAccess;
import com.lukasabbe.handlers.ServerPayloadHandler;
import com.lukasabbe.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.network.packets.LecternInventoryRequestPayload;
import com.lukasabbe.util.BlockTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LecternInventoryRequestServerPayloadHandler implements ServerPayloadHandler<LecternInventoryRequestPayload> {
    @Override
    public void receive(LecternInventoryRequestPayload lecternInventoryRequestPayload, ServerPlayer player) {
        if(BookshelfInspector.serverInstance == null) return;

        ItemStack stack = BlockTools.getBookInLectern(lecternInventoryRequestPayload.pos(), player.level());

        if(stack == null){
            ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(Items.AIR.getDefaultInstance(), lecternInventoryRequestPayload.pos(), 0));
            return;
        }

        ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new BookShelfInventoryPayload(stack, lecternInventoryRequestPayload.pos(), 0));
    }
}
