package me.lukasabbe.bookshelfinspector.util;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.GuiLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import org.joml.Matrix3x2fStack;

@Environment(EnvType.CLIENT)
public class HudRenderer {
    public static void hudRender(DrawContext context, MinecraftClient client){
        if(!BookshelfinspectorClient.modAvailable) return;
        if(client.options.hudHidden) return;
        if(!BookshelfinspectorClient.bookShelfData.isCurrentBookDataToggled) return;

        context.pushGuiLayer(GuiLayer.HUD);
        final BookData currentBookData = BookshelfinspectorClient.currentBookData;
        final int screenWidth = client.getWindow().getScaledWidth();
        final int screenHeight = client.getWindow().getScaledHeight();
        final int x = screenWidth / 2;
        final int y = screenHeight / 2;
        final ItemStack itemStack = currentBookData.itemStack;

        int color = 0xFFFFFFFF;

        final Integer colorValue = itemStack.getRarity().getFormatting().getColorValue();

        if(colorValue != null){
            color = colorValue;
        }

        float scaleFactor = ((float) BookshelfinspectorClient.config.scale /10);
        drawScaledText(context, itemStack.getName(), x,y+((int)(10*scaleFactor)), color, client.textRenderer);

        ItemEnchantmentsComponent storedComponents = itemStack.getComponents().get(DataComponentTypes.STORED_ENCHANTMENTS);
        if(storedComponents != null){
            int i = ((int)(20*scaleFactor));
            for(RegistryEntry<Enchantment> enchantment : storedComponents.getEnchantments()){
                String lvl = "";
                final int level = storedComponents.getLevel(enchantment);
                if(level != 1)
                    lvl = String.valueOf(level);
                final MutableText enchantmentText;

                if(!BookshelfinspectorClient.config.useRoman || level == -1)
                    enchantmentText = enchantment.value().description().copy().append(" " + lvl);
                else if (level != 1)
                    enchantmentText = enchantment.value().description().copy().append(" " + RomanNumerals.toRoman(level));
                else
                    enchantmentText = enchantment.value().description().copy();

                if(enchantment.isIn(EnchantmentTags.CURSE)) {
                    Texts.setStyleIfAbsent(enchantmentText, Style.EMPTY.withColor(Formatting.RED));
                }else {
                    Texts.setStyleIfAbsent(enchantmentText, Style.EMPTY.withColor(Formatting.GRAY));
                }
                drawScaledText(context, enchantmentText, x,y+i, 0xFFFFFFFF,client.textRenderer);
                i+=(int)(10*scaleFactor);
            }
        }

        var writtenBookContentComponent = itemStack.getComponents().get(DataComponentTypes.WRITTEN_BOOK_CONTENT);

        if(writtenBookContentComponent != null){
            drawScaledText(context, Text.translatable("book.byAuthor",writtenBookContentComponent.author()), x,y+(int)(20*scaleFactor), 0xFFFFFFFF,client.textRenderer);
        }

        context.popGuiLayer();

    }
    private static void drawScaledText(DrawContext context, Text text, int centerX, int y, int color, TextRenderer textRenderer){
        Matrix3x2fStack stack = context.getMatrices();
        stack.pushMatrix();
        stack.translate(centerX,y);
        final float scale = (float) BookshelfinspectorClient.config.scale / 10;
        stack.scale(scale, scale);
        stack.translate(-centerX,-y);
        context.drawCenteredTextWithShadow(textRenderer,text,centerX,y,color);
        stack.popMatrix();
    }
}
