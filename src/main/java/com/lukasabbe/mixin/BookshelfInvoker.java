package com.lukasabbe.mixin;

import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalInt;

@Mixin(ChiseledBookShelfBlock.class)
public interface BookshelfInvoker {
    //?if < 1.21.10 {
    
    /*@Invoker("getHitSlot")
    OptionalInt invokerGetSlotForHitPos(BlockHitResult hit, BlockState state);
    *///?}
}