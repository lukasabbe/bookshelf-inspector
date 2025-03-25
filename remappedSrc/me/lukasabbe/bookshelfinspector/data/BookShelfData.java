package me.lukasabbe.bookshelfinspector.data;

import net.minecraft.util.math.BlockPos;

public class BookShelfData {
    public boolean isCurrentBookDataToggled = false;
    public BlockPos latestPos = null;
    public boolean requestSent = false;
    public int currentSlotInt = -1;
}
