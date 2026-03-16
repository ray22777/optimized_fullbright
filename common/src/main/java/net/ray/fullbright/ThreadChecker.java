package net.ray.fullbright;


import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.slf4j.Logger;

public class ThreadChecker {
    public static boolean propagatedLight = false;
    public static boolean isClient() {
        Minecraft mc = Minecraft.getInstance();
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) {
            return true;
        }
        return !server.isSameThread() && mc.isSameThread();

    }
//    public static boolean shouldDelay(){
//        if(System.currentTimeMillis() - lastJoined > 5*1000){
//            return true;
//        }
//        return false;
//    }
//    public static boolean hasLightWorkLeft() {
//        Minecraft mc = Minecraft.getInstance();
//
//        if (mc.level != null) {
//            LevelLightEngine lightEngine = mc.level.getLightEngine();
//
//            if (lightEngine.hasLightWork()) {
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        return false;
//    }
}