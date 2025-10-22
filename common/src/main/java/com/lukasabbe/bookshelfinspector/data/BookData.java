package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

/**
 * Data for a slot in a chiseled bookshelf
 */
public class BookData {
    /**
     * Item in that slot
     */
    public ItemStack itemStack;
    /**
     * Block position for bookshelf
     */
    public BlockPos pos;
    /**
     * Slot were the item is located in
     */
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
