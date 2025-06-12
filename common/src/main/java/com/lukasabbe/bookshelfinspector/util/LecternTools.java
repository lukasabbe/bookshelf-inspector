package com.lukasabbe.bookshelfinspector.util;

import com.lukasabbe.bookshelfinspector.BookshelfInspector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;

import java.util.Optional;

public class LecternTools {
    public static ItemStack getItemStack(BlockPos pos, Player player){
        final Level world = BookshelfInspector.serverInstance.getPlayerList().getPlayer(player.getUUID()).level();

        Optional<LecternBlockEntity> blockEntityOptional = world.getBlockEntity(pos, BlockEntityType.LECTERN);
        if(blockEntityOptional.isEmpty()) return null;

        LecternBlockEntity lecternBlock = blockEntityOptional.get();

        return lecternBlock.getBook();
    }
}
