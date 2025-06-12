package com.lukasabbe.bookshelfinspector.network.packets;

import com.lukasabbe.bookshelfinspector.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public record BookShelfInventoryPayload(ItemStack itemStack, BlockPos pos, int slotNum) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<BookShelfInventoryPayload> ID = new CustomPacketPayload.Type<>(Constants.BOOK_SHELF_INVENTORY_PACKET_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, BookShelfInventoryPayload> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC, BookShelfInventoryPayload::itemStack,
            BlockPos.STREAM_CODEC, BookShelfInventoryPayload::pos,
            ByteBufCodecs.INT, BookShelfInventoryPayload::slotNum,
            BookShelfInventoryPayload::new
    );
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() { return ID; }
}