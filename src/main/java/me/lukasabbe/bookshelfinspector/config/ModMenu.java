package me.lukasabbe.bookshelfinspector.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder
                    .create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("bookshelfinspector.config.title"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            builder
                    .getOrCreateCategory(Text.translatable("bookshelfinspector.config.category"))
                    .addEntry(entryBuilder
                            .startBooleanToggle(Text.translatable("bookshelfinspector.config.lectern.toggle"), BookshelfinspectorClient.config.lecternToggle)
                            .setTooltip(Text.translatable("bookshelfinspector.config.lectern.toggle.tooltip"))
                            .setDefaultValue(true)
                            .setSaveConsumer(val -> BookshelfinspectorClient.config.lecternToggle = val).build())
                    .addEntry(entryBuilder
                            .startIntSlider(Text.translatable("bookshelfinspector.config.scale"),BookshelfinspectorClient.config.scale,0,20)
                            .setTooltip(Text.translatable("bookshelfinspector.config.scale.tooltip"))
                            .setDefaultValue(10).setSaveConsumer(val -> BookshelfinspectorClient.config.scale = val)
                            .build());
            builder.setSavingRunnable(BookshelfinspectorClient.config::saveConfig);
            return builder.build();
        };
    }
}
