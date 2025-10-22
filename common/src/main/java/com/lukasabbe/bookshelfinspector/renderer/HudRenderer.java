package com.lukasabbe.bookshelfinspector.renderer;

import com.lukasabbe.bookshelfinspector.BookshelfInspectorClient;
import com.lukasabbe.bookshelfinspector.data.BookData;
import com.lukasabbe.bookshelfinspector.data.Tags;
import com.lukasabbe.bookshelfinspector.util.ItemTools;
import com.lukasabbe.bookshelfinspector.util.RomanNumerals;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
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

        // If item is normal (no enchantments/potion effects/custom names), do not continue if "Display normal items" option is turned off
        if(BookshelfInspectorClient.bookShelfData.latestBlockState.is(Tags.SHELVES) && ItemTools.isNormalStack(itemStack) && !BookshelfInspectorClient.config.shelfDisplayNormal) return;

        int color = 0xFFFFFFFF;

        final Integer colorValue = itemStack.getRarity().color().getColor();

        if(colorValue != null){
            color = colorValue + 0xFF000000;
        }

        // Item name
        float scaleFactor = ((float) BookshelfInspectorClient.config.scale /10);
        final MutableComponent itemName = itemStack.getHoverName().copy();

        drawScaledText(context, itemName, x,y+((int)(10*scaleFactor)), color, client.font);

        // Item count
        if (itemStack.isStackable()) {
            float rightEdge = x + (client.font.width(itemName) / 2f) * scaleFactor;
            float spacing = 9 * scaleFactor;
            int nextX = (int) (rightEdge + spacing);

            final MutableComponent count = Component.empty();
            count.append(" (" + itemStack.getCount() + ")");

            ComponentUtils.mergeStyles(count, Style.EMPTY.withColor(ChatFormatting.GRAY));
            drawScaledText(context, count, nextX,y+((int)(10*scaleFactor)), color, client.font);
        }

        // Enchantments
        ItemEnchantments itemEnchantments = ItemTools.getItemEnchantments(itemStack);
        if(itemEnchantments != null){
            int i = ((int)(20*scaleFactor));
            for(Holder<Enchantment> enchantment : itemEnchantments.keySet()){
                String lvl = "";
                final int level = itemEnchantments.getLevel(enchantment);
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

        // Potion components
        PotionContents itemPotionContents = ItemTools.getPotionContents(itemStack);
        if (itemPotionContents != null) {
            int i = ((int)(20*scaleFactor));
            for (MobEffectInstance effect : itemPotionContents.getAllEffects()) {
                final MutableComponent potionText;

                int amplifier = effect.getAmplifier();
                potionText = effect.getEffect().value().getDisplayName().copy();
                if (amplifier > 0) {
                    String amplifierNumeral = BookshelfInspectorClient.config.useRoman ? RomanNumerals.toRoman(amplifier) : String.valueOf(amplifier);
                    potionText.append(" " + amplifierNumeral);
                }
                potionText.append(" (" + MobEffectUtil.formatDuration(effect, 1, client.level.tickRateManager().tickrate()).getString() + ")");

                if (!effect.getEffect().value().isBeneficial()) {
                    ComponentUtils.mergeStyles(potionText, Style.EMPTY.withColor(ChatFormatting.RED));
                } else {
                    ComponentUtils.mergeStyles(potionText, Style.EMPTY.withColor(ChatFormatting.GRAY));
                }
                drawScaledText(context, potionText, x, y + i, 0xFFFFFFFF, client.font);
                i += (int) (10 * scaleFactor);
            }
        }

        // Written book author
        var writtenBookContentComponent = itemStack.getComponents().get(DataComponents.WRITTEN_BOOK_CONTENT);

        if(writtenBookContentComponent != null){
            drawScaledText(context, Component.translatable("book.byAuthor",writtenBookContentComponent.author()), x,y+(int)(20*scaleFactor), 0xFFFFFFFF, client.font);
        }
    }

    private static void drawScaledText(GuiGraphics context, Component text, int centerX, int y, int color, Font textRenderer){
        Matrix3x2fStack stack = context.pose();
        stack.pushMatrix();
        stack.translate(centerX, y);
        final float scale = (float) BookshelfInspectorClient.config.scale / 10;
        stack.scale(scale, scale);
        stack.translate(-centerX, -y);
        context.drawCenteredString(textRenderer,text,centerX,y,color);
        stack.popMatrix();
    }
}
