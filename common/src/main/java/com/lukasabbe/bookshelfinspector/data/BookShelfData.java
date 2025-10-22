package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BookShelfData {
    public boolean isCurrentBookDataToggled = false;
    public BlockPos latestPos = null;
    public BlockState latestBlockState = null;
    public boolean requestSent = false;
    public int currentSlotInt = -1;
}
