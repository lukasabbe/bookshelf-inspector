package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;

public class BookShelfData {
    public boolean isCurrentBookDataToggled = false;
    public BlockPos latestPos = null;
    public boolean requestSent = false;
    public int currentSlotInt = -1;
}
