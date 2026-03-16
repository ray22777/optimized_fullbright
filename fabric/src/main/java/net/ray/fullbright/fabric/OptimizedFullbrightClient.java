package net.ray.fullbright.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.ray.fullbright.ThreadChecker;
import net.ray.fullbright.config.ModConfig;
import net.ray.fullbright.mixin.LightLevelEngineMixin;

public class OptimizedFullbrightClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        Keybind.register();
        ModConfig.load();
    }
}
