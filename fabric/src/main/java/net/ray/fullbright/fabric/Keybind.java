package net.ray.fullbright.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class Keybind {
    public static final String KEY_CATEGORY = "key.category.fullbright.fullbrightoptimized";
    public static final String KEY_TOGGLE_FULLBRIGHT = "key.fullbright.togglefullbright";
    public static KeyMapping toggleFullbright;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleFullbright.consumeClick()) {
                if (!FullBrightToggle.disableFullBright) {
                    FullBrightToggle.disableFullBright = true;
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(
                                Component.literal("Fullbright Disabled"),
                                false
                        );
                    }
                    System.out.print("disabled");
                    Minecraft.getInstance().levelRenderer.allChanged();
                } else {
                    FullBrightToggle.disableFullBright = false;
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(
                                Component.literal("Fullbright Enabled"),
                                false
                        );
                    }
                    System.out.print("enabled");
                    Minecraft.getInstance().levelRenderer.allChanged();
                }
            }
        });
    }

    public static void register() {
        toggleFullbright = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_TOGGLE_FULLBRIGHT,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY
        ));
        registerKeyInputs();
    }
}