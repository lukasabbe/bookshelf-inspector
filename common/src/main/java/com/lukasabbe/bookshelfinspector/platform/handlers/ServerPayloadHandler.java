package com.lukasabbe.bookshelfinspector.platform.handlers;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public interface ServerPayloadHandler<T extends CustomPacketPayload> {
    void receive(T payload, ServerPlayer player);
}
