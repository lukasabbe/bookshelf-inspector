package com.lukasabbe.bookshelfinspector.network.packets;

import com.lukasabbe.bookshelfinspector.Constants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record ModCheckPayload(boolean modActivated) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ModCheckPayload> ID = new CustomPacketPayload.Type<>(Constants.MOD_CHECK_PACKET_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ModCheckPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ModCheckPayload::modActivated,
            ModCheckPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return ID; }
}
