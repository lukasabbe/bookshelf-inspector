package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.EventHandler;
import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerDisconnectEvent;
import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerJoinEvent;
import com.lukasabbe.bookshelfinspector.platform.services.IEventHelper;

public class NeoForgeEventHelper implements IEventHelper {
    @Override
    public void registerOnPlayerJoinEvent(OnPlayerJoinEvent event) {
        EventHandler.playerJoinEvents.add(event);
    }

    @Override
    public void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event) {
        EventHandler.playerDisconnectEvents.add(event);
    }
}
