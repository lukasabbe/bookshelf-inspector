package me.lukasabbe.bookshelfinspector.network.packets;

import me.lukasabbe.bookshelfinspector.network.BookShelfInspectorNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record LecternInventoryRequestPayload(BlockPos pos) implements CustomPayload{
    public static final CustomPayload.Id<LecternInventoryRequestPayload> ID = new CustomPayload.Id<>(BookShelfInspectorNetworkConstants.LECTERN_INVENTORY_REQUEST_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, LecternInventoryRequestPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC,LecternInventoryRequestPayload::pos,
            LecternInventoryRequestPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
