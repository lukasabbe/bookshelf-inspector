package net.anvian.chiseledbookshelfvisualizer.util;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import net.anvian.chiseledbookshelfvisualizer.ChiseledBookshelfVisualizerClient;
import net.anvian.chiseledbookshelfvisualizer.data.BookData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class HudRenderer {
    private static boolean renderCrosshair = true;

    public static void toggleCrosshair() {
        renderCrosshair = !renderCrosshair;
    }

    public static boolean shouldRenderCrosshair() {
        return renderCrosshair;
    }

    public static void hudRender(DrawContext context, MinecraftClient client) {
        float scale = (float) ChiseledBookshelfVisualizerClient.CONFIG.scale();

        if (shouldRenderCrosshair()) {
            if (!ChiseledBookshelfVisualizerClient.modAvailable) return;
            if (client.options.hudHidden) return;
            if (client.currentScreen != null) return;

            if (ChiseledBookshelfVisualizerClient.bookShelfData.isCurrentBookDataToggled) {
                final BookData currentBookData = ChiseledBookshelfVisualizerClient.currentBookData;
                int screenWidth = client.getWindow().getScaledWidth();
                int screenHeight = client.getWindow().getScaledHeight();
                int x = screenWidth / 2;
                int y = screenHeight / 2;
                final ItemStack itemStack = currentBookData.itemStack;
                int color = 0xFFFFFFFF;

                if (itemStack.getRarity().getFormatting().getColorValue() != null) {
                    color = itemStack.getRarity().getFormatting().getColorValue();
                }

                context.getMatrices().push();
                context.getMatrices().scale(scale, scale, 1.0f);

                drawScaledCenteredText(context, client.textRenderer, itemStack.getName().getString(), x, y + 10, color, scale);

                ItemEnchantmentsComponent storedComponents = itemStack.getComponents().get(DataComponentTypes.STORED_ENCHANTMENTS);
                if (storedComponents != null) {
                    int i = (int) (20 * (scale > 1 ? scale : 1));

                    for (RegistryEntry<Enchantment> enchantment : storedComponents.getEnchantments()) {
                        if (storedComponents.getLevel(enchantment) != 1)
                            drawScaledCenteredText(context, client.textRenderer, enchantment.value().description().copy().append(" " + new RomanNumeralFormat().format(storedComponents.getLevel(enchantment))).getString(), x, y + i, 0xFFCECECE, scale);
                        else
                            drawScaledCenteredText(context, client.textRenderer, enchantment.value().description().getString(), x, y + i, 0xFFCECECE, scale);

                        i += (int) (10 * (scale > 1 ? scale : 1));
                    }
                }

                var writtenBookContentComponent = itemStack.getComponents().get(DataComponentTypes.WRITTEN_BOOK_CONTENT);

                if (writtenBookContentComponent != null) {
                    String authorText = Text.translatable("book.byAuthor", writtenBookContentComponent.author()).getString();
                    drawScaledCenteredText(context, client.textRenderer, authorText, x, y + 20, 0xFFCECECE, scale);
                }
                context.getMatrices().pop();
            }
        }
    }

    private static void drawScaledCenteredText(DrawContext context, TextRenderer textRenderer, String text, int x, int y, int color, float scale) {
        int textWidth = textRenderer.getWidth(text);
        float scaledX = x / scale - (textWidth / 2f);
        float scaledY = y / scale;

        textRenderer.draw(
                text,
                scaledX,
                scaledY,
                color,
                true,
                context.getMatrices().peek().getPositionMatrix(),
                context.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL,
                0,
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                false
        );
    }
}