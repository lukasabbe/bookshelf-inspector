package com.lukasabbe.bookshelfinspector.util;

import com.google.common.collect.Iterables;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Optional;

public class ItemTools {
    public static ItemEnchantments getItemEnchantments(ItemStack itemStack) {
        return Optional
                .ofNullable(itemStack.getComponents().get(DataComponents.STORED_ENCHANTMENTS)) // STORED_ENCHANTMENTS is for books
                .orElse(itemStack.getComponents().get(DataComponents.ENCHANTMENTS));// ENCHANTMENTS is for items that apply the enchantment
    }

    public static PotionContents getPotionContents(ItemStack itemStack) {
        return itemStack.getComponents().get(DataComponents.POTION_CONTENTS);
    }

    public static boolean isNormalStack(ItemStack itemStack) {
        if (!itemStack.getHoverName().equals(itemStack.getItemName())) return false;

        ItemEnchantments itemEnchantments = getItemEnchantments(itemStack);
        if (itemEnchantments != null && !itemEnchantments.isEmpty()) return false;

        PotionContents itemPotionContents = getPotionContents(itemStack);
        return itemPotionContents == null || Iterables.size(itemPotionContents.getAllEffects()) == 0;
    }
}
