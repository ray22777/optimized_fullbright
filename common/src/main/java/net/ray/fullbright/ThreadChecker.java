package net.ray.fullbright;


import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class ThreadChecker {
    public static boolean isIntegratedServerThread() {
        Minecraft mc = Minecraft.getInstance();
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) {
            return false;
        }
        return server.isSameThread();
    }
}