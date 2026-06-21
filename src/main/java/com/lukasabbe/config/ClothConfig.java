package com.lukasabbe.config;

import com.lukasabbe.BookshelfInspectorClient;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfig {
    public static Screen getClothConfigScreen(Screen parent){
        ConfigBuilder builder = ConfigBuilder
                .create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("bookshelfinspector.config.title"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        Config config = BookshelfInspectorClient.CONFIG;
        builder
                .getOrCreateCategory(Component.translatable("bookshelfinspector.config.category"))
                .addEntry(entryBuilder
                        .startBooleanToggle(Component.translatable("bookshelfinspector.config.lectern.toggle"), config.lecternToggle)
                        .setTooltip(Component.translatable("bookshelfinspector.config.lectern.toggle.tooltip"))
                        .setDefaultValue(true)
                        .setSaveConsumer(val -> config.lecternToggle = val).build())
                .addEntry(entryBuilder
                        .startBooleanToggle(Component.translatable("bookshelfinspector.config.shelf.toggle"), config.shelfToggle)
                        .setTooltip(Component.translatable("bookshelfinspector.config.shelf.toggle.tooltip"))
                        .setDefaultValue(true)
                        .setSaveConsumer(val -> config.shelfToggle = val).build())
                .addEntry(entryBuilder
                        .startBooleanToggle(Component.translatable("bookshelfinspector.config.shelf.display.normal"), config.shelfDisplayNormal)
                        .setTooltip(Component.translatable("bookshelfinspector.config.shelf.display.normal.tooltip"))
                        .setDefaultValue(true)
                        .setSaveConsumer(val -> config.shelfDisplayNormal = val).build())
                .addEntry(entryBuilder
                        .startIntSlider(Component.translatable("bookshelfinspector.config.scale"), config.scale,0,20)
                        .setTooltip(Component.translatable("bookshelfinspector.config.scale.tooltip"))
                        .setDefaultValue(10).setSaveConsumer(val -> config.scale = val)
                        .build())
                .addEntry(entryBuilder
                        .startBooleanToggle(Component.translatable("bookshelfinspector.config.roman_scale"), config.useRoman)
                        .setTooltip(Component.translatable("bookshelfinspector.config.roman_scale.tooltip"))
                        .setDefaultValue(false)
                        .setSaveConsumer(val -> config.useRoman = val)
                        .build());

        builder.setSavingRunnable(config::saveConfig);
        return builder.build();
    }
}
