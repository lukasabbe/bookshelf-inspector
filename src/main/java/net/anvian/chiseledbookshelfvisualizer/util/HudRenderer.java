package net.anvian.chiseledbookshelfvisualizer.util;

import net.anvian.chiseledbookshelfvisualizer.ChiseledBookshelfVisualizerClient;
import net.anvian.chiseledbookshelfvisualizer.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class HudRenderer {
    public static void hudRender(DrawContext context, MinecraftClient client){
        if(!ChiseledBookshelfVisualizerClient.modAvailable) return;

        if(client.options.hudHidden) return;

        if(ChiseledBookshelfVisualizerClient.bookShelfData.isCurrentBookDataToggled){
            final BookData currentBookData = ChiseledBookshelfVisualizerClient.currentBookData;
            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();
            int x = screenWidth / 2;
            int y = screenHeight / 2;
            final ItemStack itemStack = currentBookData.itemStack;
            int color = 0xFFFFFFFF;
            if(itemStack.getRarity().getFormatting().getColorValue() != null){
                color = itemStack.getRarity().getFormatting().getColorValue();
            }
            context.drawCenteredTextWithShadow(client.textRenderer, itemStack.getName(), x,y+10, color);

            var storedComponets = itemStack.getComponents().get(DataComponentTypes.STORED_ENCHANTMENTS);
            if(storedComponets != null){
                int i = 20;
                for(RegistryEntry<Enchantment> enchantment : storedComponets.getEnchantments()){
                    context.drawCenteredTextWithShadow(client.textRenderer, enchantment.value().description(), x,y+i, 0xFFFFFFFF);
                    i+=10;
                }
            }

            var writtenBookContentComponent = itemStack.getComponents().get(DataComponentTypes.WRITTEN_BOOK_CONTENT);

            if(writtenBookContentComponent != null){
                context.drawCenteredTextWithShadow(client.textRenderer, Text.translatable("book.byAuthor",writtenBookContentComponent.author()), x,y+20, 0xFFFFFFFF);
            }

        }
    }
}
