package com.lukasabbe.bookshelfinspector.renderer;

import com.lukasabbe.bookshelfinspector.BookshelfInspectorClient;
import com.lukasabbe.bookshelfinspector.data.BookData;
import com.lukasabbe.bookshelfinspector.data.Tags;
import com.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import com.lukasabbe.bookshelfinspector.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.OptionalInt;

import static com.lukasabbe.bookshelfinspector.BookshelfInspectorClient.*;

public class Inspector {
    public void inspect(Minecraft client){
        if(!modAvailable) return;

        if(client.getCameraEntity() == null || client.player == null) return;

        HitResult hit = client.getCameraEntity().pick(5f,0f,false);

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


        if(client.player.level().getBlockState(pos).is(Tags.CHISELED_BOOKSHELVES)){
            bookShelfInspect(pos, blockHitResult, client);
        }else if(client.player.level().getBlockState(pos).is(Tags.LECTERNS) && config.lecternToggle){
            lecternInspect(pos);
        }else{

            bookShelfData.requestSent = false; // Just for servers that don't have the latest version of mod

            if(!bookShelfData.isCurrentBookDataToggled) return;
            resetBookShelfData();
        }
    }


    private void lecternInspect(BlockPos pos){

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfInspectorClient.currentBookData;

        if(currentBookData.pos != null && currentBookData.pos.equals(pos)) return;

        if(!bookShelfData.requestSent){
            bookShelfData.requestSent = true;
            Services.NETWORK_HELPER.sendPacketFromClient(new LecternInventoryRequestPayload(pos));
        }
    }


    private void bookShelfInspect(BlockPos pos, BlockHitResult blockHitResult, Minecraft client){
        final BlockState blockState = client.player.level().getBlockState(pos);

        //Gets index position for a book in the bookshelf
        ChiseledBookShelfBlock bookshelfBlock = (ChiseledBookShelfBlock) blockState.getBlock();
        OptionalInt optionalInt = bookshelfBlock.getHitSlot(blockHitResult, blockState.getValue(ChiseledBookShelfBlock.FACING));

        //if the position is empty, return
        if(optionalInt.isEmpty()) {
            resetBookShelfData();
            return;
        }

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfInspectorClient.currentBookData;

        //Changes the id for the new one if it's new.
        final int temp = bookShelfData.currentSlotInt;
        final int slotNum = optionalInt.getAsInt();
        bookShelfData.currentSlotInt = slotNum;

        if(currentBookData.slotId!= slotNum && currentBookData.slotId!=-2 && !bookShelfData.requestSent){
            bookShelfData.requestSent = true;
            Services.NETWORK_HELPER.sendPacketFromClient(new BookShelfInventoryRequestPayload(pos, slotNum));
        }
        else {
            if(temp == slotNum)
                bookShelfData.isCurrentBookDataToggled = currentBookData.slotId != -2;
            else{
                bookShelfData.isCurrentBookDataToggled = false;
                BookshelfInspectorClient.currentBookData = BookData.empty();
            }
        }
    }

    private void resetBookShelfData(){
        if(!bookShelfData.isCurrentBookDataToggled) return;

        bookShelfData.isCurrentBookDataToggled = false;
        currentBookData = BookData.empty();
    }
}
