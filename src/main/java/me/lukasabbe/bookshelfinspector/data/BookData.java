package me.lukasabbe.bookshelfinspector.data;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class BookData {
    public ItemStack itemStack;
    public BlockPos pos;
    public int slotId;

    public BookData(ItemStack itemStack, BlockPos pos, int slotId) {
        this.itemStack = itemStack;
        this.pos = pos;
        this.slotId = slotId;
    }
}
