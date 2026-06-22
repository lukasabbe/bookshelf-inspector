package com.lukasabbe.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.lukasabbe.renderer.HudRenderer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >= 26.1 {

import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} elif < 26.1 {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}

@Mixin(Gui.class)
public class InGameHudMixin {
    @Shadow @Final private Minecraft minecraft;

    //? if >= 26.1 {
    
    @Inject(method = "extractRenderState",at=@At("RETURN"))
    public void render(DeltaTracker deltaTracker, boolean shouldRenderLevel, boolean resourcesLoaded, CallbackInfo ci, @Local(name = "graphics") GuiGraphicsExtractor graphics) {
        HudRenderer.hudRender(graphics, minecraft);
    }
    //?} elif < 26.1 {
    /*@Inject(method = "render",at=@At("RETURN"))
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci){
        HudRenderer.hudRender(context, minecraft);
    }
    *///?}
}
