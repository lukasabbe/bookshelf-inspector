package com.lukasabbe.bookshelfinspector.network.packets;

import com.lukasabbe.bookshelfinspector.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record ShelfInventoryRequestPayload(BlockPos pos, int slotNum) implements CustomPacketPayload {

    public static final Type<ShelfInventoryRequestPayload> ID = new Type<>(Constants.SHELF_INVENTORY_REQUEST_PACKET_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ShelfInventoryRequestPayload> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, ShelfInventoryRequestPayload::pos,
            ByteBufCodecs.INT, ShelfInventoryRequestPayload::slotNum,
            ShelfInventoryRequestPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return ID; }
}
