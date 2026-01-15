package net.ray.fullbright.forge;

import net.minecraftforge.fml.common.Mod;

import net.ray.fullbright.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
