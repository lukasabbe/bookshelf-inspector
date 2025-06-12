package com.lukasabbe.bookshelfinspector;

import com.lukasabbe.bookshelfinspector.network.Handlers;
import com.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import com.lukasabbe.bookshelfinspector.platform.Services;
import net.minecraft.server.MinecraftServer;

public class BookshelfInspector {
    public static MinecraftServer serverInstance;
    public static Handlers networkHandlers;

    public static void init() {
        registerEvents();
        networkHandlers = new Handlers();
    }

    private static void registerEvents(){
        Services.EVENTS_HELPER.registerOnPlayerJoinEvent((player, server) -> {
            serverInstance = server;
            Services.NETWORK_HELPER.sendPacketFromServer(player, new ModCheckPayload(true));
        });
    }

}