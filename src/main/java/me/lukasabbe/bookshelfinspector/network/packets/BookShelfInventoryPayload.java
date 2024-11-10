package me.lukasabbe.bookshelfinspector.network.packets;

import me.lukasabbe.bookshelfinspector.network.BookShelfInspectorNetworkConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record BookShelfInventoryPayload(ItemStack itemStack, BlockPos pos, int slotNum) implements CustomPayload{
    public static final CustomPayload.Id<BookShelfInventoryPayload> ID = new CustomPayload.Id<>(BookShelfInspectorNetworkConstants.BOOK_SHELF_INVENTORY_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, BookShelfInventoryPayload> CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, BookShelfInventoryPayload::itemStack,
            BlockPos.PACKET_CODEC, BookShelfInventoryPayload::pos,
            PacketCodecs.INTEGER, BookShelfInventoryPayload::slotNum,
            BookShelfInventoryPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
