package com.lukasabbe.bookshelfinspector.platform.handlers;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public interface OnPlayerJoinEvent {
    void onPlayerJoin(ServerPlayer handler, MinecraftServer server);
}
