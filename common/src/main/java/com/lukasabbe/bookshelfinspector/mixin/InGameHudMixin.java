package com.lukasabbe.bookshelfinspector.mixin;

import com.lukasabbe.bookshelfinspector.renderer.HudRenderer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "render",at=@At("RETURN"))
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci){
        HudRenderer.hudRender(context, minecraft);
    }
}
