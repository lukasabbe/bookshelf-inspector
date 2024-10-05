package net.anvian.chiseledbookshelfvisualizer.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record BookShelfInventoryRequestPayload(BlockPos pos, int slotNum) implements CustomPayload {

    public static final CustomPayload.Id<BookShelfInventoryRequestPayload> ID = new CustomPayload.Id<>(BookShelfInspectorNetworkConstants.BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, BookShelfInventoryRequestPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC,BookShelfInventoryRequestPayload::pos,
            PacketCodecs.INTEGER, BookShelfInventoryRequestPayload::slotNum,
            BookShelfInventoryRequestPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
