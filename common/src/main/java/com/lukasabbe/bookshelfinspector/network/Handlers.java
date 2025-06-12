package com.lukasabbe.bookshelfinspector.network;

import com.lukasabbe.bookshelfinspector.network.client.BookShelfInventoryHandlerServer;
import com.lukasabbe.bookshelfinspector.network.client.ModServerPayloadHandler;
import com.lukasabbe.bookshelfinspector.network.server.BookShelfInventoryRequestServerPayloadHandler;
import com.lukasabbe.bookshelfinspector.network.server.LecternInventoryRequestServerPayloadHandler;

public class Handlers {
    public BookShelfInventoryHandlerServer bookShelfInventoryHandlerServer;
    public ModServerPayloadHandler modServerPayloadHandler;
    public BookShelfInventoryRequestServerPayloadHandler bookShelfInventoryRequestServerPayloadHandler;
    public LecternInventoryRequestServerPayloadHandler lecternInventoryRequestServerPayloadHandler;

    public Handlers(){
        bookShelfInventoryHandlerServer = new BookShelfInventoryHandlerServer();
        modServerPayloadHandler = new ModServerPayloadHandler();
        bookShelfInventoryRequestServerPayloadHandler = new BookShelfInventoryRequestServerPayloadHandler();
        lecternInventoryRequestServerPayloadHandler = new LecternInventoryRequestServerPayloadHandler();
    }

}
