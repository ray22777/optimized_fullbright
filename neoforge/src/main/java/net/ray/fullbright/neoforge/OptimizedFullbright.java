package net.ray.fullbright.neoforge;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
import net.ray.fullbright.FullBrightToggle;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("optimizedfullbright")
public class OptimizedFullbright {
    public static final KeyMapping.Category KEY_CATEGORY =
            KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath("fullbright", "fullbrightoptimized"));
//    public static final String KEY_CATEGORY = "key.category.fullbright.fullbrightoptimized";
    public static final String MOD_ID = "optimizedfullbright";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String KEY_TOGGLE_FULLBRIGHT = "key.fullbright.togglefullbright";
    public static KeyMapping toggleFullbright;
    private static long scheduledRefreshTick = -1;

    public OptimizedFullbright(IEventBus modEventBus, Dist dist) {
        if (dist.isClient()) {
            modEventBus.addListener(this::registerKeyMappings);
            NeoForge.EVENT_BUS.addListener(this::onClientTick);

            LOGGER.info("Optimized Fullbright client initialized");
        }
    }

    private void registerKeyMappings(RegisterKeyMappingsEvent event) {
        toggleFullbright = new KeyMapping(
                KEY_TOGGLE_FULLBRIGHT,
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY
        );
        event.register(toggleFullbright);
        LOGGER.debug("Registered Fullbright toggle key");
    }

    private void onClientTick(ClientTickEvent.Post event) {
        if (toggleFullbright != null && toggleFullbright.consumeClick()) {
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
    }
}