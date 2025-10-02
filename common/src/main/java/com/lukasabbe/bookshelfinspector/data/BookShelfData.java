package com.lukasabbe.bookshelfinspector.data;

import net.minecraft.core.BlockPos;

/**
 * Data for mod
 */
public class BookShelfData {
    /**
     * If mod should display text on the screen.
     */
    public boolean isCurrentBookDataToggled = false;
    /**
     * latest position of bookshelf, used for reducing requests to server
     */
    public BlockPos latestPos = null;
    /**
     * If a request has been sent, used to limit request to one at a time.
     */
    public boolean requestSent = false;
    /**
     * Last spot in bookshelf, used to limit requests.
     */
    public int currentSlotInt = -1;
}
