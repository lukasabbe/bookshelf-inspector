package com.lukasabbe.inspector;

import com.lukasabbe.BookshelfInspectorClient;
import com.lukasabbe.ModLoaderAccess;
import com.lukasabbe.data.BookData;
import com.lukasabbe.data.Tags;
import com.lukasabbe.network.packets.BookShelfInventoryRequestPayload;
import com.lukasabbe.network.packets.LecternInventoryRequestPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
//?if >= 1.21.9 {

import com.lukasabbe.network.packets.ShelfInventoryRequestPayload;
import net.minecraft.world.level.block.ShelfBlock;
//?}

//?if < 1.21.10{
/*import com.lukasabbe.mixin.BookshelfInvoker;
*///?}

import java.util.OptionalInt;
import static com.lukasabbe.BookshelfInspectorClient.*;

public class Inspector {
    public void inspect(Minecraft client) {
        if (!modAvailable) return;

        if (client.getCameraEntity() == null || client.player == null) return;

        HitResult hit = client.getCameraEntity().pick(5f, 0f, false);

        //find block hit, if not found block returns
        final HitResult.Type type = hit.getType();
        if (type != HitResult.Type.BLOCK) {
            resetBookShelfData();
            return;
        }

        final BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos pos = blockHitResult.getBlockPos();

        if (bookShelfData.latestPos == null)
            bookShelfData.latestPos = pos;

        //If you look at a new block
        if (!bookShelfData.latestPos.equals(pos)) {
            resetBookShelfData();
            currentBookData = BookData.empty();
        }
        bookShelfData.latestPos = pos;

        BlockState blockState = client.player.level().getBlockState(pos);
        bookShelfData.latestBlockState = blockState;
        if (blockState.is(Tags.CHISELED_BOOKSHELVES)) {
            bookShelfInspect(pos, blockHitResult, client);
            return;
        }

        if (blockState.is(Tags.LECTERNS) && CONFIG.lecternToggle) {
            lecternInspect(pos);
            return;
        }

        //?if > 1.21.9 {
        
        if (blockState.is(Tags.SHELVES) && CONFIG.shelfToggle) {
            shelfInspect(pos, blockHitResult, client);
            return;
        }
        //?}

        // No match
        bookShelfData.requestSent = false; // Just for servers that don't have the latest version of mod

        if (!bookShelfData.isCurrentBookDataToggled) return;
        resetBookShelfData();
    }


    private void lecternInspect(BlockPos pos) {

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfInspectorClient.currentBookData;

        if (currentBookData.pos != null && currentBookData.pos.equals(pos)) return;

        if (!bookShelfData.requestSent) {
            bookShelfData.requestSent = true;
            ModLoaderAccess.INSTANCE.sendPacketFromClient(new LecternInventoryRequestPayload(pos));
        }
    }


    private void bookShelfInspect(BlockPos pos, BlockHitResult blockHitResult, Minecraft client) {
        final BlockState blockState = client.player.level().getBlockState(pos);

        //Gets index position for a book in the bookshelf
        ChiseledBookShelfBlock bookshelfBlock = (ChiseledBookShelfBlock) blockState.getBlock();
        //?if >= 1.21.10 {
        
        OptionalInt optionalInt = bookshelfBlock.getHitSlot(blockHitResult, blockState.getValue(ChiseledBookShelfBlock.FACING));
         //?}else {
        /*OptionalInt optionalInt = ((BookshelfInvoker)bookshelfBlock).invokerGetSlotForHitPos(blockHitResult,blockState);
        *///?}

        //if the position is empty, return
        if (optionalInt.isEmpty()) {
            resetBookShelfData();
            return;
        }

        //Checks if there is saved data.
        final BookData currentBookData = BookshelfInspectorClient.currentBookData;

        //Changes the id for the new one if it's new.
        final int temp = bookShelfData.currentSlotInt;
        final int slotNum = optionalInt.getAsInt();
        bookShelfData.currentSlotInt = slotNum;

        if (currentBookData.slotId != slotNum && currentBookData.slotId != -2 && !bookShelfData.requestSent) {
            bookShelfData.requestSent = true;
            ModLoaderAccess.INSTANCE.sendPacketFromClient(new BookShelfInventoryRequestPayload(pos, slotNum));
        } else {
            if (temp == slotNum)
                bookShelfData.isCurrentBookDataToggled = currentBookData.slotId != -2;
            else {
                bookShelfData.isCurrentBookDataToggled = false;
                BookshelfInspectorClient.currentBookData = BookData.empty();
            }
        }
    }

    //?if > 1.21.9 {
    
    private void shelfInspect(BlockPos pos, BlockHitResult blockHitResult, Minecraft client) {
        final BlockState blockState = client.player.level().getBlockState(pos);

        ShelfBlock shelfBlock = (ShelfBlock) blockState.getBlock();
        OptionalInt optionalInt = shelfBlock.getHitSlot(blockHitResult, blockState.getValue(ShelfBlock.FACING));

        // If the position is empty, return
        if (optionalInt.isEmpty()) {
            resetBookShelfData();
            return;
        }

        final BookData currentBookData = BookshelfInspectorClient.currentBookData;

        final int temp = bookShelfData.currentSlotInt;
        final int slotNum = optionalInt.getAsInt();
        bookShelfData.currentSlotInt = slotNum;

        if (currentBookData.slotId != slotNum && !bookShelfData.requestSent) {
            bookShelfData.requestSent = true;
            ModLoaderAccess.INSTANCE.sendPacketFromClient(new ShelfInventoryRequestPayload(pos, slotNum));
        } else {
            if (temp == slotNum)
                bookShelfData.isCurrentBookDataToggled = currentBookData.slotId != -2;
            else {
                bookShelfData.isCurrentBookDataToggled = false;
                BookshelfInspectorClient.currentBookData = BookData.empty();
            }
        }
    }
    //?}

    private void resetBookShelfData(){
        if(!bookShelfData.isCurrentBookDataToggled) return;

        bookShelfData.isCurrentBookDataToggled = false;
        currentBookData = BookData.empty();
    }
}
