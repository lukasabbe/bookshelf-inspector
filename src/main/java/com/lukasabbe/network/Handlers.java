package com.lukasabbe.network;

import com.lukasabbe.network.client.BookShelfInventoryHandlerServer;
import com.lukasabbe.network.client.ModServerPayloadHandler;
import com.lukasabbe.network.server.BookShelfInventoryRequestServerPayloadHandler;
import com.lukasabbe.network.server.LecternInventoryRequestServerPayloadHandler;
import com.lukasabbe.network.server.ShelfInventoryRequestServerPayloadHandler;

public class Handlers {
    public BookShelfInventoryHandlerServer bookShelfInventoryHandlerServer;
    public ModServerPayloadHandler modServerPayloadHandler;
    public BookShelfInventoryRequestServerPayloadHandler bookShelfInventoryRequestServerPayloadHandler;
    public LecternInventoryRequestServerPayloadHandler lecternInventoryRequestServerPayloadHandler;
    public ShelfInventoryRequestServerPayloadHandler shelfInventoryRequestServerPayloadHandler;

    public Handlers(){
        bookShelfInventoryHandlerServer = new BookShelfInventoryHandlerServer();
        modServerPayloadHandler = new ModServerPayloadHandler();
        bookShelfInventoryRequestServerPayloadHandler = new BookShelfInventoryRequestServerPayloadHandler();
        lecternInventoryRequestServerPayloadHandler = new LecternInventoryRequestServerPayloadHandler();
        shelfInventoryRequestServerPayloadHandler = new ShelfInventoryRequestServerPayloadHandler();
    }

}
