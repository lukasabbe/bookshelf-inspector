package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public class BookData {
    public ItemStack itemStack;
    public BlockPos pos;
    public int slotId;

    public BookData(ItemStack itemStack, BlockPos pos, int slotId) {
        this.itemStack = itemStack;
        this.pos = pos;
        this.slotId = slotId;
    }

    public static BookData empty(){
        return new BookData(ItemStack.EMPTY, null, -1);
    }
}
