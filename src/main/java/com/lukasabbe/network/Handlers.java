package com.lukasabbe.network;

import com.lukasabbe.network.client.BookShelfInventoryHandlerServer;
import com.lukasabbe.network.client.ModServerPayloadHandler;
import com.lukasabbe.network.server.BookShelfInventoryRequestServerPayloadHandler;
import com.lukasabbe.network.server.LecternInventoryRequestServerPayloadHandler;
//?if >= 1.21.9 {

import com.lukasabbe.network.server.ShelfInventoryRequestServerPayloadHandler;
//?}

public class Handlers {
    public BookShelfInventoryHandlerServer bookShelfInventoryHandlerServer;
    public ModServerPayloadHandler modServerPayloadHandler;
    public BookShelfInventoryRequestServerPayloadHandler bookShelfInventoryRequestServerPayloadHandler;
    public LecternInventoryRequestServerPayloadHandler lecternInventoryRequestServerPayloadHandler;
    //?if >= 1.21.9 {
    
    public ShelfInventoryRequestServerPayloadHandler shelfInventoryRequestServerPayloadHandler;
    //?}

    public Handlers(){
        bookShelfInventoryHandlerServer = new BookShelfInventoryHandlerServer();
        modServerPayloadHandler = new ModServerPayloadHandler();
        bookShelfInventoryRequestServerPayloadHandler = new BookShelfInventoryRequestServerPayloadHandler();
        lecternInventoryRequestServerPayloadHandler = new LecternInventoryRequestServerPayloadHandler();
        //?if >= 1.21.9 {
        
        shelfInventoryRequestServerPayloadHandler = new ShelfInventoryRequestServerPayloadHandler();
        //?}
    }

}
