package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.platform.services.INetworkHelper;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeNetworkHelper implements INetworkHelper {
    @Override
    public void sendPacketFromServer(ServerPlayer player, CustomPacketPayload payload) {
        try{
            PacketDistributor.sendToPlayer(player, payload);
        }catch (Exception ignored) {}
    }

    @Override
    public void sendPacketFromClient(CustomPacketPayload payload) {PacketDistributor.sendToServer(payload);
    }
}
