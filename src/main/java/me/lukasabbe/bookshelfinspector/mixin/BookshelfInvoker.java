package me.lukasabbe.bookshelfinspector.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalInt;

@Mixin(ChiseledBookshelfBlock.class)
public interface BookshelfInvoker {
    @Invoker("getSlotForHitPos")
    int getSlotForHitPos(Vec2f hitPos);
}
