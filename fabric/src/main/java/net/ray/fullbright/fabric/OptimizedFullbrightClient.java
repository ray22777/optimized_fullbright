package net.ray.fullbright.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.KeyMapping;

public class OptimizedFullbrightClient implements ClientModInitializer {

    private static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {
        Keybind.register();
    }
}
