package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static final TagKey<Block> CHISELED_BOOKSHELVES = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "chiseled_bookshelves"));
    public static final TagKey<Block> LECTERNS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "lectern"));
    public static final TagKey<Block> SHELVES = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "shelves"));
}
