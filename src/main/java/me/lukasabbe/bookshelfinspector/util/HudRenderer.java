package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
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
            float scaleFactor = ((float) BookshelfinspectorClient.config.scale /10);
            drawScaledText(context, itemStack.getName(), x,y+((int)(10*scaleFactor)), color, client.textRenderer);

            ItemEnchantmentsComponent storedComponents = itemStack.getComponents().get(DataComponentTypes.STORED_ENCHANTMENTS);
            if(storedComponents != null){
                int i = ((int)(20*scaleFactor));
                for(RegistryEntry<Enchantment> enchantment : storedComponents.getEnchantments()){
                    String lvl = "";
                    if(storedComponents.getLevel(enchantment) != 1)
                        lvl = String.valueOf(storedComponents.getLevel(enchantment));

                    drawScaledText(context,enchantment.value().description().copy().append(" " + lvl), x,y+i, 0xFFFFFFFF,client.textRenderer);
                    i+=(int)(10*scaleFactor);
                }
            }

            var writtenBookContentComponent = itemStack.getComponents().get(DataComponentTypes.WRITTEN_BOOK_CONTENT);

            if(writtenBookContentComponent != null){
                drawScaledText(context, Text.translatable("book.byAuthor",writtenBookContentComponent.author()), x,y+(int)(20*scaleFactor), 0xFFFFFFFF,client.textRenderer);
            }

        }
    }
    private static void drawScaledText(DrawContext context, Text text, int centerX, int y, int color, TextRenderer textRenderer){
        MatrixStack stack = context.getMatrices();
        stack.push();
        stack.translate(centerX,y,0);
        stack.scale((float) BookshelfinspectorClient.config.scale /10, (float) BookshelfinspectorClient.config.scale /10, (float) BookshelfinspectorClient.config.scale /10);
        stack.translate(-centerX,-y,0);
        context.drawCenteredTextWithShadow(textRenderer,text,centerX,y,color);
        stack.pop();
    }
}
