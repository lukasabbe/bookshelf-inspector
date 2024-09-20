package me.lukasabbe.bookshelfinspector;

import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.network.BookShelfInventoryPayload;
import me.lukasabbe.bookshelfinspector.network.ModCheckPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;

@Environment(EnvType.CLIENT)
public class BookshelfinspectorClient implements ClientModInitializer {

    public static BookData currentBookData = new BookData(ItemStack.EMPTY, null, -1);
    public static boolean isCurrentBookDataToggled = false;
    public static boolean modAvailable = false;
    public static HitResult latestHit;

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(BookShelfInventoryPayload.ID,
                ((payload, context) ->
                        context.client().execute(() ->{
                            if(payload.itemStack().isOf(Items.AIR))
                                isCurrentBookDataToggled = false;
                            else{
                                BookshelfinspectorClient.isCurrentBookDataToggled = true;
                                currentBookData = new BookData(payload.itemStack(),payload.pos(),payload.slotNum());
                            }
                        })));

        ClientPlayNetworking.registerGlobalReceiver(ModCheckPayload.ID,
                (payload, context) -> context.client().execute(() ->{
                    Bookshelfinspector.LOGGER.info("Connected to server");
                    modAvailable = true;
                }));
    }
}
