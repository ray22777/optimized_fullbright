package net.tricube.optimized_fullbright;


import net.tricube.optimized_fullbright.config.Config;
import net.minecraft.resources.ResourceLocation;

import net.tricube.optimized_fullbright.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import net.tricube.optimized_fullbright.platform.fabric.FabricPlatform;
//?} neoforge {
/*import net.tricube.optimized_fullbright.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import net.tricube.optimized_fullbright.platform.forge.ForgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class ModInit {

	public static final String MOD_ID = /*$ mod_id*/ "optimized_fullbright";
//	public static final String MOD_VERSION = /*$ mod_version*/ "0.1.0";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Optimized Fullbright";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {

//		LOGGER.info("Initializing {} on {}", MOD_ID, ModTemplate.xplat().loader());
//		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	public static void onInitializeClient() {
		Config.init();
		LOGGER.info("Optimized Fullbright Loaded");
//		LOGGER.info("Initializing {} Client on {}", MOD_ID, ModTemplate.xplat().loader());
//		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);

	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		 *///?}
	}

	private static ResourceLocation id(String path) {
		//? >=1.21 {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
		 //?} < 1.21 {
		/*return new ResourceLocation(MOD_ID, path);
		*///?}
	}

	private static ResourceLocation id(String namespace, String path) {
		//? >=1.21 {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
		//?} < 1.21 {
		/*return new ResourceLocation(namespace, path);
		*///?}
	}


}
