package com.lukasabbe;

import com.lukasabbe.config.Config;
import com.lukasabbe.data.BookData;
import com.lukasabbe.data.BookShelfData;

public class BookshelfInspectorClient {
    public static BookData currentBookData = BookData.empty();
    public static BookShelfData bookShelfData = new BookShelfData();
    public static boolean modAvailable = false;
    public static final Config CONFIG = new Config();

    public static void clientInit(){
        CONFIG.loadConfig();
        ModLoaderAccess.INSTANCE.registerOnPlayerDisconnect(() -> {
            modAvailable = false;
            bookShelfData = new BookShelfData();
        });
    }
}
