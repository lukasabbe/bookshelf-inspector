package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import me.lukasabbe.bookshelfinspector.data.Tags;
import me.lukasabbe.bookshelfinspector.mixin.BookshelfInvoker;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import me.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.OptionalInt;

import static me.lukasabbe.bookshelfinspector.BookshelfinspectorClient.*;

@Environment(EnvType.CLIENT)
public class Inspector {
    public void inspect(MinecraftClient client){
        if(!modAvailable) return;

        if(client.cameraEntity == null || client.player == null) return;

        //Send raycast max 5 blocks
        HitResult hit = client.cameraEntity.raycast(5f,0f,false);

        //find block hit, if not found block returns
        final HitResult.Type type = hit.getType();
        if(type != HitResult.Type.BLOCK) {
            resetBookShelfData();
            return;
        }

        final BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos pos = blockHitResult.getBlockPos();

        if(bookShelfData.latestPos == null)
            bookShelfData.latestPos = pos;

        //If you look at a new block
        if(!bookShelfData.latestPos.equals(pos)){
            resetBookShelfData();
            currentBookData = BookData.empty();
        }
        bookShelfData.latestPos = pos;


        if(client.player.getWorld().getBlockState(pos).isIn(Tags.CHISELED_BOOKSHELVES)){
            bookShelfInspect(pos, blockHitResult, client);
        }else if(client.player.getWorld().getBlockState(pos).isOf(Blocks.LECTERN) && config.lecternToggle){
            lecternInspect(pos);
        }else{

            bookShelfData.requestSent = false; // Just for servers that don't have the latest version of mod

            if(!bookShelfData.isCurrentBookDataToggled) return;
            resetBookShelfData();
        }
    }


    private void lecternInspect(BlockPos pos){

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfinspectorClient.currentBookData;

        if(currentBookData.pos != null && currentBookData.pos.equals(pos)) return;

        if(!bookShelfData.requestSent){
            bookShelfData.requestSent = true;
            ClientPlayNetworking.send(new LecternInventoryRequestPayload(pos));
        }
    }


    private void bookShelfInspect(BlockPos pos, BlockHitResult blockHitResult, MinecraftClient client){
        final BlockState blockState = client.player.getWorld().getBlockState(pos);

        //Gets index position for a book in the bookshelf
        ChiseledBookshelfBlock bookshelfBlock = (ChiseledBookshelfBlock) blockState.getBlock();
        OptionalInt optionalInt = ((BookshelfInvoker)bookshelfBlock).invokerGetSlotForHitPos(blockHitResult,blockState);

        //if the position is empty, return
        if(optionalInt.isEmpty()) {
            resetBookShelfData();
            return;
        }

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfinspectorClient.currentBookData;

        //Changes the id for the new one if it's new.
        final int temp = bookShelfData.currentSlotInt;
        final int slotNum = optionalInt.getAsInt();
        bookShelfData.currentSlotInt = slotNum;

        if(currentBookData.slotId!= slotNum && currentBookData.slotId!=-2 && !bookShelfData.requestSent){
            bookShelfData.requestSent = true;
            ClientPlayNetworking.send(new BookShelfInventoryRequestPayload(pos, slotNum));
        }
        else {
            if(temp == slotNum)
                bookShelfData.isCurrentBookDataToggled = currentBookData.slotId != -2;
            else{
                bookShelfData.isCurrentBookDataToggled = false;
                BookshelfinspectorClient.currentBookData = BookData.empty();
            }
        }
    }

    private void resetBookShelfData(){
        if(!bookShelfData.isCurrentBookDataToggled) return;

        bookShelfData.isCurrentBookDataToggled = false;
        currentBookData = BookData.empty();
    }
}
