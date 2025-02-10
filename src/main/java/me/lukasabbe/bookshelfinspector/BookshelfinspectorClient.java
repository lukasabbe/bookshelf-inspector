package me.lukasabbe.bookshelfinspector;

import me.lukasabbe.bookshelfinspector.config.Config;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.data.BookShelfData;
import me.lukasabbe.bookshelfinspector.network.client.BookShelfInventoryHandler;
import me.lukasabbe.bookshelfinspector.network.client.ModPayloadHandler;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import me.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class BookshelfinspectorClient implements ClientModInitializer {

    public static BookData currentBookData = BookData.empty();
    public static BookShelfData bookShelfData = new BookShelfData();
    public static boolean modAvailable = false;
    public static Config config = new Config();

    @Override
    public void onInitializeClient() {

        config.loadConfig();

        ClientPlayNetworking.registerGlobalReceiver(BookShelfInventoryPayload.ID, new BookShelfInventoryHandler());
        ClientPlayNetworking.registerGlobalReceiver(ModCheckPayload.ID,new ModPayloadHandler());

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->  {
            modAvailable = false;
            bookShelfData = new BookShelfData();
        });
    }
}
