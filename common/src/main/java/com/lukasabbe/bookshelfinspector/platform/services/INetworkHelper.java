package com.lukasabbe.bookshelfinspector.platform.services;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public interface INetworkHelper {
    void sendPacketFromServer(ServerPlayer player, CustomPacketPayload payload);
    void sendPacketFromClient(CustomPacketPayload payload);
}
