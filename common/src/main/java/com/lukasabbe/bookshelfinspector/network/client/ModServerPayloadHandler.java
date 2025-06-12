package com.lukasabbe.bookshelfinspector.network.client;

import com.lukasabbe.bookshelfinspector.BookshelfInspectorClient;
import com.lukasabbe.bookshelfinspector.Constants;
import com.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import com.lukasabbe.bookshelfinspector.platform.handlers.ClientPayloadHandler;
import net.minecraft.client.player.LocalPlayer;

public class ModServerPayloadHandler implements ClientPayloadHandler<ModCheckPayload> {
    @Override
    public void receive(ModCheckPayload payload, LocalPlayer player) {
        Constants.LOG.info("[bookshelfinspector] Connected to server");
        BookshelfInspectorClient.modAvailable = true;
    }
}
