package me.lukasabbe.bookshelfinspector.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.lukasabbe.bookshelfinspector.util.HudRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render",at=@At("RETURN"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci){
        HudRenderer.hudRender(context, client);
    }
}
