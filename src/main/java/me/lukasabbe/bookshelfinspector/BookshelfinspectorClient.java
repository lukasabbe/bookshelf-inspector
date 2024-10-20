package me.lukasabbe.bookshelfinspector;

import me.lukasabbe.bookshelfinspector.config.Config;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.data.BookShelfData;
import me.lukasabbe.bookshelfinspector.network.BookShelfInventoryPayload;
import me.lukasabbe.bookshelfinspector.network.ModCheckPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.Items;

@Environment(EnvType.CLIENT)
public class BookshelfinspectorClient implements ClientModInitializer {

    public static BookData currentBookData = BookData.empty();
    public static BookShelfData bookShelfData = new BookShelfData();
    public static boolean modAvailable = false;
    public static Config config = new Config();

    @Override
    public void onInitializeClient() {
        config.loadConfig();
        ClientPlayNetworking.registerGlobalReceiver(BookShelfInventoryPayload.ID,
                ((payload, context) ->
                        context.client().execute(() ->{
                            bookShelfData.requestSent = false;
                            if(payload.itemStack().isOf(Items.AIR)){
                                bookShelfData.isCurrentBookDataToggled = false;
                                currentBookData = BookData.empty();
                                currentBookData.slotId = -2;
                            }
                            else{
                                bookShelfData.isCurrentBookDataToggled = true;
                                currentBookData = new BookData(payload.itemStack(),payload.pos(),payload.slotNum());
                            }
                        })));

        ClientPlayNetworking.registerGlobalReceiver(ModCheckPayload.ID,
                (payload, context) -> context.client().execute(() ->{
                    Bookshelfinspector.LOGGER.info("[bookshelfinspector] Connected to server");
                    modAvailable = true;
                }));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->  {
            modAvailable = false;
            bookShelfData = new BookShelfData();
        });
    }
}
