package com.lukasabbe.bookshelfinspector;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "bookshelfinspector";
	public static final String MOD_NAME = "Bookshelf Inspector";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	//Packets
	public static final ResourceLocation BOOK_SHELF_INVENTORY_REQUEST_PACKET_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID,"book_shelf_inventory_request");
	public static final ResourceLocation BOOK_SHELF_INVENTORY_PACKET_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID,"book_shelf_inventory");
	public static final ResourceLocation MOD_CHECK_PACKET_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID,"mod_check");
	public static final ResourceLocation LECTERN_INVENTORY_REQUEST_PACKET_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "lectern_inventory_request");
}