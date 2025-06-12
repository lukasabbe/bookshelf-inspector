package com.lukasabbe.bookshelfinspector;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder
                    .create()
                    .setParentScreen(parent)
                    .setTitle(Component.translatable("bookshelfinspector.config.title"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            builder
                    .getOrCreateCategory(Component.translatable("bookshelfinspector.config.category"))
                    .addEntry(entryBuilder
                            .startBooleanToggle(Component.translatable("bookshelfinspector.config.lectern.toggle"), BookshelfInspectorClient.config.lecternToggle)
                            .setTooltip(Component.translatable("bookshelfinspector.config.lectern.toggle.tooltip"))
                            .setDefaultValue(true)
                            .setSaveConsumer(val -> BookshelfInspectorClient.config.lecternToggle = val).build())
                    .addEntry(entryBuilder
                            .startIntSlider(Component.translatable("bookshelfinspector.config.scale"),BookshelfInspectorClient.config.scale,0,20)
                            .setTooltip(Component.translatable("bookshelfinspector.config.scale.tooltip"))
                            .setDefaultValue(10).setSaveConsumer(val -> BookshelfInspectorClient.config.scale = val)
                            .build())
                    .addEntry(entryBuilder
                            .startBooleanToggle(Component.translatable("bookshelfinspector.config.roman_scale"), BookshelfInspectorClient.config.useRoman)
                            .setTooltip(Component.translatable("bookshelfinspector.config.roman_scale.tooltip"))
                            .setDefaultValue(false)
                            .setSaveConsumer(val -> BookshelfInspectorClient.config.useRoman = val)
                            .build());

            builder.setSavingRunnable(BookshelfInspectorClient.config::saveConfig);
            return builder.build();
        };
    }
}
