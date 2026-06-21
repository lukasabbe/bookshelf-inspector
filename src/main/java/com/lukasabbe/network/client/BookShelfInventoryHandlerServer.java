package com.lukasabbe.network.client;

import com.lukasabbe.BookshelfInspectorClient;
import com.lukasabbe.data.BookData;
import com.lukasabbe.handlers.ClientPayloadHandler;
import com.lukasabbe.network.packets.BookShelfInventoryPayload;

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
