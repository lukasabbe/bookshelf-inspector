package com.lukasabbe.bookshelfinspector.network.packets;

import com.lukasabbe.bookshelfinspector.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record BookShelfInventoryRequestPayload(BlockPos pos, int slotNum) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BookShelfInventoryRequestPayload> ID = new CustomPacketPayload.Type<>(Constants.BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, BookShelfInventoryRequestPayload> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,BookShelfInventoryRequestPayload::pos,
            ByteBufCodecs.INT, BookShelfInventoryRequestPayload::slotNum,
            BookShelfInventoryRequestPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return ID; }
}
