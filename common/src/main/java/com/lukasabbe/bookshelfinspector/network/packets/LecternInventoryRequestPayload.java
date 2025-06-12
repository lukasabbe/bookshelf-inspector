package com.lukasabbe.bookshelfinspector.network.packets;

import com.lukasabbe.bookshelfinspector.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record LecternInventoryRequestPayload(BlockPos pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<LecternInventoryRequestPayload> ID = new CustomPacketPayload.Type<>(Constants.LECTERN_INVENTORY_REQUEST_PACKET_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, LecternInventoryRequestPayload> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,LecternInventoryRequestPayload::pos,
            LecternInventoryRequestPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return ID; }
}
