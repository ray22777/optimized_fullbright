package net.ray.fullbright;

public class FullBrightToggle {
    public static boolean enabled = true;
    public static boolean update = true;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void disable() {
        enabled = false;
    }

    public static void enable() {
        enabled = true;
        update = true;
    }
}
