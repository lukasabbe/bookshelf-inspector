package com.lukasabbe.bookshelfinspector.mixin;

import com.lukasabbe.bookshelfinspector.renderer.Inspector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class BookshelfMixin {

    @Shadow @Final protected Minecraft minecraft;
    @Unique private final Inspector bookshelfInspectorMultiloader$inspector = new Inspector();

    @Inject(method = "tick", at= @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    public void injectTick(CallbackInfo ci){
        bookshelfInspectorMultiloader$inspector.inspect(this.minecraft);
    }
}
