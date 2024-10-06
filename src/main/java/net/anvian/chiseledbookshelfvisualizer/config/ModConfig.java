package net.anvian.chiseledbookshelfvisualizer.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;
import net.anvian.chiseledbookshelfvisualizer.ChiseledBookshelfVisualizer;

@SuppressWarnings("unused")
@Modmenu(modId = ChiseledBookshelfVisualizer.MOD_ID)
@Config(name = ChiseledBookshelfVisualizer.MOD_ID + "-config", wrapperName = "ChiseledBookshelfVisualizerConfig")
public class ModConfig {
    @RestartRequired
    public double scale = 1.0;
}