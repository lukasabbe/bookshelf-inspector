package com.lukasabbe.network.client;

import com.lukasabbe.BookshelfInspectorClient;
import com.lukasabbe.Constants;
import com.lukasabbe.handlers.ClientPayloadHandler;
import com.lukasabbe.network.packets.ModCheckPayload;
import net.minecraft.client.player.LocalPlayer;

public class ModServerPayloadHandler implements ClientPayloadHandler<ModCheckPayload> {
    @Override
    public void receive(ModCheckPayload payload, LocalPlayer player) {
        Constants.LOGGER.info("[bookshelfinspector] Connected to server");
        BookshelfInspectorClient.modAvailable = true;
    }
}
