package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.Bookshelfinspector;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class BookshelfTools {
    public static ItemStack getItemById(BlockPos pos, int slotNum, PlayerEntity player){
        final World world = Bookshelfinspector.serverInstance.getPlayerManager().getPlayer(player.getUuid()).getWorld();

        if(world == null) return null;

        final Optional<ChiseledBookshelfBlockEntity> blockEntityOptional = world.getBlockEntity(pos,BlockEntityType.CHISELED_BOOKSHELF);

        if(blockEntityOptional.isEmpty()) return null;

        final ChiseledBookshelfBlockEntity blockEntity = blockEntityOptional.get();
        final ItemStack stack = blockEntity.getStack(slotNum);

        if(stack.isEmpty()) return null;

        return stack;
    }
}
