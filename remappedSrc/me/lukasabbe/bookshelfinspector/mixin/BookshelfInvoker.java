package me.lukasabbe.bookshelfinspector.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalInt;

@Mixin(ChiseledBookshelfBlock.class)
public interface BookshelfInvoker {
    @Invoker("getSlotForHitPos")
    OptionalInt invokerGetSlotForHitPos(BlockHitResult hit, BlockState state);
}
