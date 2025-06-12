package com.lukasabbe.bookshelfinspector.platform.services;

import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerDisconnectEvent;
import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerJoinEvent;

public interface IEventHelper {
    void registerOnPlayerJoinEvent(OnPlayerJoinEvent event);
    void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event);
}
