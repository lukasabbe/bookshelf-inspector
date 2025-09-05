package com.lukasabbe.bookshelfinspector.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;

/**
 * Tools for getting books from blocks, like lecterns and chiseled bookshelf's
 */
public class BlockTools {

    /**
     * Gets a specific book from a chiseled bookshelf
     * @param pos position of bookshelf
     * @param slotNum slot number were the book you want is located
     * @param world the world the bookshelf is in
     * @return returns the book in that position or null if its empty
     */
    public static ItemStack getBookInChiseledBookShelf(BlockPos pos, int slotNum, Level world){
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof ChiseledBookShelfBlockEntity chiseledBookShelf) {
            final ItemStack stack = chiseledBookShelf.getItem(slotNum);
            if(stack.isEmpty()) return null;
            return stack;
        }
        return null;
    }

    /**
     * Gets the book in the lectern
     * @param pos position of lectern
     * @param world the world the bookshelf is in
     * @return returns the book in the lectern or if its empty it returns null
     */
    public static ItemStack getBookInLectern(BlockPos pos, Level world){
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof LecternBlockEntity lecternBlockEntity) return lecternBlockEntity.getBook();
        return null;
    }
}
