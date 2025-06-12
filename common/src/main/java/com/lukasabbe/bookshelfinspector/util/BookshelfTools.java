package com.lukasabbe.bookshelfinspector.util;

import com.lukasabbe.bookshelfinspector.BookshelfInspector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;

import java.util.Optional;

public class BookshelfTools {
    public static ItemStack getItemById(BlockPos pos, int slotNum, Player player){
        final Level world = BookshelfInspector.serverInstance.getPlayerList().getPlayer(player.getUUID()).level();

        final Optional<ChiseledBookShelfBlockEntity> blockEntityOptional = world.getBlockEntity(pos, BlockEntityType.CHISELED_BOOKSHELF);

        if(blockEntityOptional.isEmpty()) return null;

        final ChiseledBookShelfBlockEntity blockEntity = blockEntityOptional.get();
        final ItemStack stack = blockEntity.getItem(slotNum);

        if(stack.isEmpty()) return null;

        return stack;
    }
}
