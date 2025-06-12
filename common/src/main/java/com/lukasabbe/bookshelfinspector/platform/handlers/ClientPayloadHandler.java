package com.lukasabbe.bookshelfinspector.platform.handlers;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface ClientPayloadHandler<T extends CustomPacketPayload> {
    void receive(T payload, LocalPlayer player);
}
