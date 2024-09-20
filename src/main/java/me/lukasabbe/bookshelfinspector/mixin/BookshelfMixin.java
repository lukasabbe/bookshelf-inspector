package me.lukasabbe.bookshelfinspector.mixin;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.network.BookShelfInventoryRequestPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.OptionalInt;

@Mixin(ClientPlayerEntity.class)
public class BookshelfMixin{

    @Shadow @Final protected MinecraftClient client;

    @Inject(method = "tick", at= @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    public void injectTick(CallbackInfo ci){
        bookShelfInspect();
    }

    @Unique
    public void bookShelfInspect(){
        if(!BookshelfinspectorClient.modAvailable) return;

        if(client.cameraEntity == null || client.player == null) return;

        HitResult hit = client.cameraEntity.raycast(20f,0f,false);
        final HitResult.Type type = hit.getType();
        if(type != HitResult.Type.BLOCK) return;
        final BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos pos = blockHitResult.getBlockPos();

        Optional<ChiseledBookshelfBlockEntity> optionalChiseledBookshelfBlockEntity = client.player.getWorld().getBlockEntity(pos, BlockEntityType.CHISELED_BOOKSHELF);
        if(optionalChiseledBookshelfBlockEntity.isEmpty()){
            BookshelfinspectorClient.isCurrentBookDataToggled = false;
            return;
        }

        final BlockState blockState = client.player.getWorld().getBlockState(pos);

        ChiseledBookshelfBlock bookshelfBlock = (ChiseledBookshelfBlock) blockState.getBlock();

        OptionalInt optionalInt = ((BookshelfInvoker)bookshelfBlock).invokerGetSlotForHitPos(blockHitResult,blockState);
        if(optionalInt.isEmpty()) {
            BookshelfinspectorClient.isCurrentBookDataToggled = false;
            return;
        }

        BookshelfinspectorClient.latestHit = hit;

        final BookData currentBookData = BookshelfinspectorClient.currentBookData;
        if(currentBookData.itemStack == ItemStack.EMPTY || currentBookData.slotId!=optionalInt.getAsInt() || currentBookData.pos != pos){
            ClientPlayNetworking.send(new BookShelfInventoryRequestPayload(pos, optionalInt.getAsInt()));
        }
    }
}
