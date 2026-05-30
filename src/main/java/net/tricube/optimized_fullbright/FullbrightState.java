package net.tricube.optimized_fullbright;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.tricube.optimized_fullbright.config.Config;

public  class FullbrightState {
	public static boolean requireUpdate;

	public static void setRequireUpdate(boolean reqUpdate) {
		requireUpdate = reqUpdate;
	}

	public static boolean requireUpdate() {
		return requireUpdate;
	}
}
