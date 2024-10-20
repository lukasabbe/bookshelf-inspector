package me.lukasabbe.bookshelfinspector.mixin;

import me.lukasabbe.bookshelfinspector.util.Inspector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class BookshelfMixin{

    @Shadow @Final protected MinecraftClient client;

    @Unique
    private final Inspector inspector = new Inspector();

    @Inject(method = "tick", at= @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    public void injectTick(CallbackInfo ci){
        inspector.inspect(client);
    }


}
