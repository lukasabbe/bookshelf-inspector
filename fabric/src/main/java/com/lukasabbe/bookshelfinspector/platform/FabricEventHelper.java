package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerDisconnectEvent;
import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerJoinEvent;
import com.lukasabbe.bookshelfinspector.platform.services.IEventHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class FabricEventHelper implements IEventHelper {
    @Override
    public void registerOnPlayerJoinEvent(OnPlayerJoinEvent event) {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> event.onPlayerJoin(handler.getPlayer(), server));
    }

    @Override
    public void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event) {
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> event.onDisconnect());
    }
}
