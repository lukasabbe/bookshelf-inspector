package me.lukasabbe.bookshelfinspector.data;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class Tags {
    public static final TagKey<Block> CHISELED_BOOKSHELVES = TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "chiseled_bookshelves"));
}
