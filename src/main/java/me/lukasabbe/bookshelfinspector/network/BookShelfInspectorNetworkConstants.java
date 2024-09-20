package me.lukasabbe.bookshelfinspector.network;

import me.lukasabbe.bookshelfinspector.Bookshelfinspector;
import net.minecraft.util.Identifier;

public class BookShelfInspectorNetworkConstants {
    public static final Identifier BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID = Identifier.of(Bookshelfinspector.MOD_ID,"book_shelf_inventory_request");
    public static final Identifier BOOK_SHELF_INVENTORY_PACKET_ID = Identifier.of(Bookshelfinspector.MOD_ID,"book_shelf_inventory");
    public static final Identifier MOD_CHECK_PACKET_ID = Identifier.of(Bookshelfinspector.MOD_ID,"mod_check");
}
