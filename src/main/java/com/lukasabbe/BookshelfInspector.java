package com.lukasabbe;

import com.lukasabbe.network.Handlers;
import com.lukasabbe.network.packets.ModCheckPayload;
import net.minecraft.server.MinecraftServer;

public class BookshelfInspector {
    public static MinecraftServer serverInstance;
    public static Handlers networkHandlers;

    public static void init(){
        networkHandlers = new Handlers();
        ModLoaderAccess.INSTANCE.registerOnPlayerJoinEvent((player, server) -> {
            serverInstance = server;
            ModLoaderAccess.INSTANCE.sendPacketFromServer(player, new ModCheckPayload(true));
        });
    }
}
