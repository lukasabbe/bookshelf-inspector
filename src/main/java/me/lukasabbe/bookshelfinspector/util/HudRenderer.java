package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class HudRenderer {
    public static void hudRender(DrawContext context, MinecraftClient client){
        if(!BookshelfinspectorClient.modAvailable) return;

        if(client.options.hudHidden) return;

        if(BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled){
            final BookData currentBookData = BookshelfinspectorClient.currentBookData;
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

            ItemEnchantmentsComponent storedComponents = itemStack.getComponents().get(DataComponentTypes.STORED_ENCHANTMENTS);
            if(storedComponents != null){
                int i = 20;
                for(RegistryEntry<Enchantment> enchantment : storedComponents.getEnchantments()){
                    String lvl = "";
                    if(storedComponents.getLevel(enchantment) != 1)
                        lvl = String.valueOf(storedComponents.getLevel(enchantment));

                    context.drawCenteredTextWithShadow(client.textRenderer, enchantment.value().description().copy().append(" " + lvl), x,y+i, 0xFFFFFFFF);
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
