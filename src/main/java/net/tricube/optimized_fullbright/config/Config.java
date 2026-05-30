package net.tricube.optimized_fullbright.config;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import net.tricube.optimized_fullbright.FullbrightState;
import net.tricube.optimized_fullbright.ModInit;
import net.tricube.CraftConfig.api.controller.BooleanController;
import net.tricube.CraftConfig.api.registry.CraftConfigRegistry;
import net.tricube.CraftConfig.api.v1.ConfigCategory;
import net.tricube.CraftConfig.api.v1.ConfigKeybinds;
import net.tricube.CraftConfig.api.v1.ConfigOption;
import net.tricube.CraftConfig.api.v1.ConfigSection;
import net.tricube.CraftConfig.api.v1.CraftConfig;
import net.tricube.optimized_fullbright.ThreadChecker;

@SuppressWarnings("unchecked")
public class Config {

	public static final ConfigOption<Boolean> enableFullbright =
			ConfigOption.booleanOption(Component.literal("Fullbright"), true)
					.description(Component.literal("Enables fullbright for your game."));

	public static final ConfigOption<Boolean> overrideGamma =
			ConfigOption.booleanOption(Component.literal("Override Gamma"), true)
					.description(Component.literal("Bypass Minecraft's gamma limit and set it to maximum levels."));

	public static final ConfigOption<Boolean> disablelightPropagation =
			ConfigOption.booleanOption(Component.literal("Disable Light Propagation"), true)
					.description(Component.literal("Stop light updates"));

	public static final ConfigOption<Boolean> forceMaxBrightness =
			ConfigOption.booleanOption(Component.literal("Force Max Brightness"), true)
					.description(Component.literal("Skips light level calculations and returns max value (15)."));

	public static final ConfigOption<Boolean> disableLightUpdates =
			ConfigOption.booleanOption(Component.literal("Disable Light Updates"), true)
					.description(Component.literal("Automatically disabled when ScalableLux mod is used."));

	public static final ConfigOption<Boolean> disableTextureUpdates =
			ConfigOption.booleanOption(Component.literal("Disable Texture Updates"), true)
					.description(Component.literal("Stop light color/texture updates."));

	public static final CraftConfig config = CraftConfig.create(ModInit.MOD_ID)
			.title(Component.literal("Optimized Fullbright Config"))
			.category(ConfigCategory.builder(Component.literal("General"))
					.section(ConfigSection.builder(Component.literal("Options"))
							.option(enableFullbright.controller(new BooleanController())
									.onChanged(v -> {
										if(ThreadChecker.isClient()){//ensure it fires on client thread (forge is buggy in dev environment)
											Minecraft.getInstance().levelRenderer.allChanged();
										}
										else{
											Minecraft.getInstance().execute(()->Minecraft.getInstance().levelRenderer.allChanged());
										}
										FullbrightState.setRequireUpdate(true);
									})
									.keybind(ConfigKeybinds.create()
											.defaultKey(InputConstants.KEY_G)
											.mode(ConfigKeybinds.Mode.TOGGLE)
											.notify(true)))
							.build())
					.section(ConfigSection.builder(Component.literal("Compatibility"))
							.description(Component.literal("If you encounter compatibility issues with certain mods,\ntry turning off individual optimizations.\n Some settings may require a restart."))
							.option(overrideGamma.controller(new BooleanController()))
							.option(disablelightPropagation.controller(new BooleanController()))
							.option(forceMaxBrightness.controller(new BooleanController()))
							.option(disableLightUpdates.controller(new BooleanController()))
							.option(disableTextureUpdates.controller(new BooleanController()))
							.build())
					.build())
			.build();


	public static void init() {
		config.load();
		CraftConfigRegistry.register(ModInit.MOD_ID, config, ModInit.MOD_FRIENDLY_NAME)
				.setModMenuEnabled(true)
				.setCommandEnabled(true)
				.setCustomCommand("optimizedfullbright")
				.build();
	}
}
