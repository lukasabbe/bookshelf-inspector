package com.lukasabbe.bookshelfinspector.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;

public class BlockTools {
    public static ItemStack getBookInChiseledBookShelf(BlockPos pos, int slotNum, Level world){
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof ChiseledBookShelfBlockEntity chiseledBookShelf) {
            final ItemStack stack = chiseledBookShelf.getItem(slotNum);
            if(stack.isEmpty()) return null;
            return stack;
        }
        return null;
    }

    public static ItemStack getBookInLectern(BlockPos pos, Level world){
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof LecternBlockEntity lecternBlockEntity) return lecternBlockEntity.getBook();
        return null;
    }

    public static ItemStack getItemInShelf(BlockPos pos, int slotNum, Level world){
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ShelfBlockEntity shelfBlock) {
            final ItemStack stack = shelfBlock.getItem(slotNum);
            if (stack.isEmpty()) return null;
            return stack;
        }
        return null;
    }
}
