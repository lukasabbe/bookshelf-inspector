package com.lukasabbe.bookshelfinspector.util;

import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerDisconnectEvent;
import com.lukasabbe.bookshelfinspector.platform.handlers.OnPlayerJoinEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    public static List<OnPlayerJoinEvent> playerJoinEvents = new ArrayList<>();
    public static List<OnPlayerDisconnectEvent> playerDisconnectEvents = new ArrayList<>();

    public static void initClient(){
        NeoForge.EVENT_BUS.addListener(EventHandler::onDisconnect);
    }
    public static void initServer(){ NeoForge.EVENT_BUS.addListener(EventHandler::onJoin); }

    private static void onJoin(PlayerEvent.PlayerLoggedInEvent event){
        playerJoinEvents.forEach(onPlayerJoinEvent -> onPlayerJoinEvent.onPlayerJoin((ServerPlayer) event.getEntity(), ((ServerPlayer)event.getEntity()).level().getServer()));
    }

    private static void onDisconnect(ClientPlayerNetworkEvent.LoggingOut event){
        playerDisconnectEvents.forEach(OnPlayerDisconnectEvent::onDisconnect);
    }

}
