package net.ray.fullbright;

import net.ray.fullbright.config.ModConfig;

public class FullBrightToggle {
    public static boolean update = true;

    public static boolean isEnabled() {
        return ModConfig.enableFullbright;
    }

    public static void disable() {
        ModConfig.enableFullbright = false;
        ModConfig.save();
    }

    public static void enable() {
        ModConfig.enableFullbright = true;
        ModConfig.save();
        update = true;
    }
}
