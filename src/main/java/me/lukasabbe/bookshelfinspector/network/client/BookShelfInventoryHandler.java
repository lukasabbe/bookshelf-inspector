package me.lukasabbe.bookshelfinspector.network.client;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;

@Environment(EnvType.CLIENT)
public class BookShelfInventoryHandler implements ClientPlayNetworking.PlayPacketHandler<BookShelfInventoryHandler>{
    @Override
    public void receive(BookShelfInventoryHandler bookShelfInventoryHandler, ClientPlayerEntity clientPlayerEntity, PacketSender packetSender) {
        context.client().execute(() ->{
            BookshelfinspectorClient.bookShelfData.requestSent = false;
            if(bookShelfInventoryPayload.itemStack().isOf(Items.AIR)){
                BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
                BookshelfinspectorClient.currentBookData = BookData.empty();
                BookshelfinspectorClient.currentBookData.slotId = -2;
            }
            else{
                BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = true;
                BookshelfinspectorClient.currentBookData = new BookData(bookShelfInventoryPayload.itemStack(),bookShelfInventoryPayload.pos(),bookShelfInventoryPayload.slotNum());
            }
        });
    }
}
