package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * Tags for blocks used in the mod
 */
public class Tags {
    /**
     * Tag for chiseled bookshelves using the common tag, so inspection can work for modded bookshelf's
     */
    public static final TagKey<Block> CHISELED_BOOKSHELVES = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "chiseled_bookshelves"));
    /**
     * Tag for lecterns using the common tag, so inspection can work for modded lectern's
     */
    public static final TagKey<Block> LECTERNS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "lectern"));
}
