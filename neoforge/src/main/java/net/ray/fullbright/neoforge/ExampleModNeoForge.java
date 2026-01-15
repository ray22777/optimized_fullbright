package net.ray.fullbright.neoforge;

import net.neoforged.fml.common.Mod;

import net.ray.fullbright.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
