package me.lukasabbe.bookshelfinspector.network.client;

import me.lukasabbe.bookshelfinspector.Bookshelfinspector;
import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class ModPayloadHandler implements ClientPlayNetworking.PlayPayloadHandler<ModCheckPayload> {
    @Override
    public void receive(ModCheckPayload modCheckPayload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            Bookshelfinspector.LOGGER.info("[bookshelfinspector] Connected to server");
            BookshelfinspectorClient.modAvailable = true;
        });
    }
}
