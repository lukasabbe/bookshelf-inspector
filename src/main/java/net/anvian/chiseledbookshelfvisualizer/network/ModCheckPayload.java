package net.anvian.chiseledbookshelfvisualizer.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ModCheckPayload(boolean modActivated) implements CustomPayload {

    public static final CustomPayload.Id<ModCheckPayload> ID = new CustomPayload.Id<>(BookShelfInspectorNetworkConstants.MOD_CHECK_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, ModCheckPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, ModCheckPayload::modActivated,
            ModCheckPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
