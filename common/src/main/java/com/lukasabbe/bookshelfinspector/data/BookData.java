package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

/**
 * Data for a slot in a chiseled bookshelf
 */
public class BookData {
    public ItemStack itemStack;
    public BlockPos pos;
    public int slotId;

    /**
     * Creates data object with its data
     */
    public BookData(ItemStack itemStack, BlockPos pos, int slotId) {
        this.itemStack = itemStack;
        this.pos = pos;
        this.slotId = slotId;
    }

    /**
     * Gets an empty BookData
     */
    public static BookData empty(){
        return new BookData(ItemStack.EMPTY, null, -1);
    }
}
