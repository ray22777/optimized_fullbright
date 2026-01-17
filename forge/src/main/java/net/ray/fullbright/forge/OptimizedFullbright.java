
package net.ray.fullbright;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("optimizedfullbright")
public class OptimizedFullbright {
    public static final String MOD_ID = "optimizedfullbright";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final String KEY_CATEGORY = "key.category.fullbright.fullbrightoptimized";
    public static final String KEY_TOGGLE_FULLBRIGHT = "key.fullbright.togglefullbright";
    public static KeyMapping toggleFullbright;
    private static long scheduledRefreshTick = -1;


    public OptimizedFullbright() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::registerKeyMappings);
        forgeEventBus.register(new ClientEventHandler());
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
    }

    @Mod.EventBusSubscriber(modid = "optimizedfullbright", value = Dist.CLIENT)
    public static class ClientEventHandler {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (toggleFullbright != null && toggleFullbright.consumeClick()) {
                if (FullBrightToggle.isEnabled) {
                    FullBrightToggle.isEnabled = false;
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(Component.literal("§bFullbright §7- §cOFF"), true);
                    }
                    Minecraft.getInstance().levelRenderer.allChanged();
                } else {
                    FullBrightToggle.isEnabled = true;
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.displayClientMessage(Component.literal("§bFullbright §7- §aON"), true);
                    }
                    Minecraft.getInstance().levelRenderer.allChanged();
                }
            }
        }
    }
}