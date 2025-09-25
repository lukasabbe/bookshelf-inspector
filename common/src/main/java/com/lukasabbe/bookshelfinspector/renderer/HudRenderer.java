package com.lukasabbe.bookshelfinspector.renderer;

import com.lukasabbe.bookshelfinspector.BookshelfInspectorClient;
import com.lukasabbe.bookshelfinspector.data.BookData;
import com.lukasabbe.bookshelfinspector.util.RomanNumerals;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.joml.Matrix3x2fStack;

public class HudRenderer {
    public static void hudRender(GuiGraphics context, Minecraft client){
        if(!BookshelfInspectorClient.modAvailable) return;
        if(client.options.hideGui) return;
        if(!BookshelfInspectorClient.bookShelfData.isCurrentBookDataToggled) return;

        final BookData currentBookData = BookshelfInspectorClient.currentBookData;
        final int screenWidth = client.getWindow().getGuiScaledWidth();
        final int screenHeight = client.getWindow().getGuiScaledHeight();
        final int x = screenWidth / 2;
        final int y = screenHeight / 2;
        final ItemStack itemStack = currentBookData.itemStack;

        int color = 0xFFFFFFFF;

        final Integer colorValue = itemStack.getRarity().color().getColor();

        if(colorValue != null){
            color = colorValue + 0xFF000000;
        }

        float scaleFactor = ((float) BookshelfInspectorClient.config.scale /10);
        drawScaledText(context, itemStack.getHoverName(), x,y+((int)(10*scaleFactor)), color, client.font);

        ItemEnchantments storedComponents = itemStack.getComponents().get(DataComponents.STORED_ENCHANTMENTS);
        if(storedComponents != null){
            int i = ((int)(20*scaleFactor));
            for(Holder<Enchantment> enchantment : storedComponents.keySet()){
                String lvl = "";
                final int level = storedComponents.getLevel(enchantment);
                if(level != 1)
                    lvl = String.valueOf(level);
                final MutableComponent enchantmentText;

                if(!BookshelfInspectorClient.config.useRoman || level == -1)
                    enchantmentText = enchantment.value().description().copy().append(" " + lvl);
                else if (level != 1)
                    enchantmentText = enchantment.value().description().copy().append(" " + RomanNumerals.toRoman(level));
                else
                    enchantmentText = enchantment.value().description().copy();

                if(enchantment.is(EnchantmentTags.CURSE)) {
                    ComponentUtils.mergeStyles(enchantmentText, Style.EMPTY.withColor(ChatFormatting.RED));
                }else {
                    ComponentUtils.mergeStyles(enchantmentText, Style.EMPTY.withColor(ChatFormatting.GRAY));
                }
                drawScaledText(context, enchantmentText, x,y+i, 0xFFFFFFFF,client.font);
                i+=(int)(10*scaleFactor);
            }
        }

        var writtenBookContentComponent = itemStack.getComponents().get(DataComponents.WRITTEN_BOOK_CONTENT);

        if(writtenBookContentComponent != null){
            drawScaledText(context, Component.translatable("book.byAuthor",writtenBookContentComponent.author()), x,y+(int)(20*scaleFactor), 0xFFFFFFFF, client.font);
        }
    }
    private static void drawScaledText(GuiGraphics context, Component text, int centerX, int y, int color, Font textRenderer){
        PoseStack stack = context.pose();
        stack.pushPose();
        stack.translate(centerX, y, 0);
        final float scale = (float) BookshelfInspectorClient.config.scale / 10;
        stack.scale(scale, scale, scale);
        stack.translate(-centerX, -y, 0);
        context.drawCenteredString(textRenderer,text,centerX,y,color);
        stack.popPose();
    }
}
