package net.anvian.chiseledbookshelfvisualizer.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInput {
    public static KeyBinding activateKey;

    public static void register() {
        activateKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.chiseled-bookshelf-visualizer.title",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "key.chiseled-bookshelf-visualizer.category"
        ));
        registerKeyInputs();
    }

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (activateKey.wasPressed()) {
                HudRenderer.toggleCrosshair();

                if (HudRenderer.shouldRenderCrosshair()) {
                    client.player.sendMessage(net.minecraft.text.Text.translatable("key.chiseled-bookshelf-visualizer.enabled"), true);
                } else {
                    client.player.sendMessage(net.minecraft.text.Text.translatable("key.chiseled-bookshelf-visualizer.disabled"), true);
                }
            }
        });
    }
}
