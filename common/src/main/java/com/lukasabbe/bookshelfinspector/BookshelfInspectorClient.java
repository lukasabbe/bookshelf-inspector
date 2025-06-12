package com.lukasabbe.bookshelfinspector;

import com.lukasabbe.bookshelfinspector.config.Config;
import com.lukasabbe.bookshelfinspector.data.BookData;
import com.lukasabbe.bookshelfinspector.data.BookShelfData;
import com.lukasabbe.bookshelfinspector.platform.Services;

public class BookshelfInspectorClient {
    public static BookData currentBookData = BookData.empty();
    public static BookShelfData bookShelfData = new BookShelfData();
    public static boolean modAvailable = false;
    public static Config config = new Config();

    public static void clientInit(){
        config.loadConfig();

        Services.EVENTS_HELPER.registerOnPlayerDisconnect(() -> {
            modAvailable = false;
            bookShelfData = new BookShelfData();
        });

    }
}
