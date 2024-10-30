package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.mixin.BookshelfInvoker;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import me.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.OptionalInt;

@Environment(EnvType.CLIENT)
public class Inspector {
    public void inspect(MinecraftClient client){
        if(!BookshelfinspectorClient.modAvailable) return;

        if(client.cameraEntity == null || client.player == null) return;

        HitResult hit = client.cameraEntity.raycast(5f,0f,false);
        final HitResult.Type type = hit.getType();
        if(type != HitResult.Type.BLOCK) {
            BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            BookshelfinspectorClient.currentBookData = BookData.empty();
        }
        final BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos pos = blockHitResult.getBlockPos();
        if(client.player.getWorld().getBlockState(pos).isOf(Blocks.CHISELED_BOOKSHELF)){
            bookShelfInspect(pos, blockHitResult, client);
        }else if(client.player.getWorld().getBlockState(pos).isOf(Blocks.LECTERN) && BookshelfinspectorClient.config.lecternToggle){
            lecternInspect(pos, client);
        }else{
            BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            BookshelfinspectorClient.currentBookData = BookData.empty();
            BookshelfinspectorClient.bookShelfData.latestPos = null;
            BookshelfinspectorClient.bookShelfData.requestSent = false;
        }
    }


    private void lecternInspect(BlockPos pos, MinecraftClient client){
        Optional<LecternBlockEntity> optionalLecternBlockEntity = client.player.getWorld().getBlockEntity(pos, BlockEntityType.LECTERN);
        if(optionalLecternBlockEntity.isEmpty()){
            BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            BookshelfinspectorClient.currentBookData = BookData.empty();
            return;
        }

        if(BookshelfinspectorClient.bookShelfData.latestPos != null && BookshelfinspectorClient.bookShelfData.latestPos.equals(pos)){
            return;
        }

        if(!BookshelfinspectorClient.bookShelfData.requestSent){
            BookshelfinspectorClient.bookShelfData.requestSent = true;
            ClientPlayNetworking.send(new LecternInventoryRequestPayload(pos));
            BookshelfinspectorClient.bookShelfData.latestPos = pos;
        }
    }


    private void bookShelfInspect(BlockPos pos, BlockHitResult blockHitResult, MinecraftClient client){
        Optional<ChiseledBookshelfBlockEntity> optionalChiseledBookshelfBlockEntity = client.player.getWorld().getBlockEntity(pos, BlockEntityType.CHISELED_BOOKSHELF);
        if(optionalChiseledBookshelfBlockEntity.isEmpty()){
            BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            BookshelfinspectorClient.currentBookData = BookData.empty();
            return;
        }

        final BlockState blockState = client.player.getWorld().getBlockState(pos);

        ChiseledBookshelfBlock bookshelfBlock = (ChiseledBookshelfBlock) blockState.getBlock();

        OptionalInt optionalInt = ((BookshelfInvoker)bookshelfBlock).invokerGetSlotForHitPos(blockHitResult,blockState);
        if(optionalInt.isEmpty()) {
            BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
            return;
        }

        final BookData currentBookData = BookshelfinspectorClient.currentBookData;

        int temp = BookshelfinspectorClient.bookShelfData.currentSlotInt;
        final int slotNum = optionalInt.getAsInt();
        BookshelfinspectorClient.bookShelfData.currentSlotInt = slotNum;

        if(currentBookData.slotId!= slotNum && currentBookData.slotId!=-2 && !BookshelfinspectorClient.bookShelfData.requestSent){
            BookshelfinspectorClient.bookShelfData.requestSent = true;
            ClientPlayNetworking.send(new BookShelfInventoryRequestPayload(pos, slotNum));
        }
        else {
            if(temp == slotNum)
                BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = currentBookData.slotId != -2;
            else{
                BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled = false;
                BookshelfinspectorClient.currentBookData = BookData.empty();
            }
        }
    }
}
