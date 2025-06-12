package com.lukasabbe.bookshelfinspector.network.client;

import com.lukasabbe.bookshelfinspector.BookshelfInspectorClient;
import com.lukasabbe.bookshelfinspector.data.BookData;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import com.lukasabbe.bookshelfinspector.platform.handlers.ClientPayloadHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Items;

public class BookShelfInventoryHandlerServer implements ClientPayloadHandler<BookShelfInventoryPayload> {
    @Override
    public void receive(BookShelfInventoryPayload bookShelfInventoryPayload, LocalPlayer player) {
        BookshelfInspectorClient.bookShelfData.requestSent = false;
        if(bookShelfInventoryPayload.itemStack().is(Items.AIR)){
            BookshelfInspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            BookshelfInspectorClient.currentBookData = BookData.empty();
            BookshelfInspectorClient.currentBookData.slotId = -2;
        }
        else{
            BookshelfInspectorClient.bookShelfData.isCurrentBookDataToggled = true;
            BookshelfInspectorClient.currentBookData = new BookData(bookShelfInventoryPayload.itemStack(),bookShelfInventoryPayload.pos(),bookShelfInventoryPayload.slotNum());
        }
    }
}
