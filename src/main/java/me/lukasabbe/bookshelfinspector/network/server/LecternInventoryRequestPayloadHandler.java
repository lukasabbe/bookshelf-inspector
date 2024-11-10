package me.lukasabbe.bookshelfinspector.network.server;

import me.lukasabbe.bookshelfinspector.Bookshelfinspector;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import me.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import me.lukasabbe.bookshelfinspector.util.LecternTools;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class LecternInventoryRequestPayloadHandler implements ServerPlayNetworking.PlayPayloadHandler<LecternInventoryRequestPayload>{
    @Override
    public void receive(LecternInventoryRequestPayload lecternInventoryRequestPayload, ServerPlayNetworking.Context context) {
        context.server().execute(() ->{
            if(Bookshelfinspector.serverInstance == null) return;

            ItemStack stack = LecternTools.getItemStack(lecternInventoryRequestPayload.pos(), context.player());

            if(stack == null){
                ServerPlayNetworking.send(context.player(), new BookShelfInventoryPayload(Items.AIR.getDefaultStack(), lecternInventoryRequestPayload.pos(), 0));
                return;
            }
            ServerPlayNetworking.send(context.player(), new BookShelfInventoryPayload(stack, lecternInventoryRequestPayload.pos(), 0));
        });
    }
}
