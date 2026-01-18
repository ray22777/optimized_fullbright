package net.ray.fullbright.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.network.chat.Component;
import net.ray.fullbright.FullBrightToggle;
import org.lwjgl.glfw.GLFW;

public class Keybind {
    //    public static final KeyMapping.Cat KEY_CATEGORY =
//            KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath("fullbright", "fullbrightoptimized"));
    public static final String KEY_CATEGORY = "key.category.fullbright.fullbrightoptimized";
    public static final String KEY_TOGGLE_FULLBRIGHT = "key.fullbright.togglefullbright";
    public static KeyMapping toggleFullbright;
    private static long scheduledRefreshTick = -1;
    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleFullbright.consumeClick()) {
                if (FullBrightToggle.isEnabled()) {
                    FullBrightToggle.disable();
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(Component.literal("§bFullbright §7- §cOFF"), true);
                    }
                    Minecraft.getInstance().levelRenderer.allChanged();

                } else {
                    FullBrightToggle.enable();
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(Component.literal("§bFullbright §7- §aON"), true);
                    }
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