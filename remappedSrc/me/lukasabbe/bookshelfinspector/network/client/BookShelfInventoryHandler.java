package me.lukasabbe.bookshelfinspector.network.client;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.Items;

@Environment(EnvType.CLIENT)
public class BookShelfInventoryHandler implements ClientPlayNetworking.PlayPayloadHandler<BookShelfInventoryPayload> {
    @Override
    public void receive(BookShelfInventoryPayload bookShelfInventoryPayload, ClientPlayNetworking.Context context) {
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
