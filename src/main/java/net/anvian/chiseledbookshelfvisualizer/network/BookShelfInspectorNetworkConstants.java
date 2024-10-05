package net.anvian.chiseledbookshelfvisualizer.network;

import net.anvian.chiseledbookshelfvisualizer.ChiseledBookshelfVisualizer;
import net.minecraft.util.Identifier;

public class BookShelfInspectorNetworkConstants {
    public static final Identifier BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID = Identifier.of(ChiseledBookshelfVisualizer.MOD_ID,"book_shelf_inventory_request");
    public static final Identifier BOOK_SHELF_INVENTORY_PACKET_ID = Identifier.of(ChiseledBookshelfVisualizer.MOD_ID,"book_shelf_inventory");
    public static final Identifier MOD_CHECK_PACKET_ID = Identifier.of(ChiseledBookshelfVisualizer.MOD_ID,"mod_check");
}
