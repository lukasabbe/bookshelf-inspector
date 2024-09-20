package me.lukasabbe.bookshelfinspector.mixin;

import me.lukasabbe.bookshelfinspector.BookshelfinspectorClient;
import me.lukasabbe.bookshelfinspector.data.BookData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)

public class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render",at=@At("RETURN"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci){
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
                context.drawCenteredTextWithShadow(client.textRenderer, Text.translatableWithFallback("bookshelfinspector.text.book","By ").append(writtenBookContentComponent.author()), x,y+20, 0xFFFFFFFF);
            }

        }
    }
}
