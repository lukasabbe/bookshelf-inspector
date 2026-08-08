package com.lukasabbe;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public static final String MOD_ID = "bookshelfinspector";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String VERSION = /*$ mod_version*/ "2.4";
    public static final String MINECRAFT = /*$ minecraft*/ "26.3-snapshot-7";
    public static final Identifier BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID = id(MOD_ID,"book_shelf_inventory_request");
    public static final Identifier BOOK_SHELF_INVENTORY_PACKET_ID = id(MOD_ID,"book_shelf_inventory");
    public static final Identifier MOD_CHECK_PACKET_ID = id(MOD_ID,"mod_check");
    public static final Identifier LECTERN_INVENTORY_REQUEST_PACKET_ID = id(MOD_ID, "lectern_inventory_request");
    public static final Identifier SHELF_INVENTORY_REQUEST_PACKET_ID = id(MOD_ID,"shelf_inventory_request");

    /**
     * Adapts to the {@link Identifier} changes introduced in 1.21.
     */
    public static Identifier id(String namespace, String path) {
        //? if <1.21 {
        /*return new Identifier(namespace, path);
         *///?} else
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
}
