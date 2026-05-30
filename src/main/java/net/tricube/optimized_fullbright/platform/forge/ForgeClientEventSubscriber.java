package net.tricube.optimized_fullbright.platform.forge;

//? forge {

/*import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tricube.optimized_fullbright.ModInit;
import net.tricube.optimized_fullbright.ThreadChecker;

@Mod.EventBusSubscriber(modid = ModInit.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientEventSubscriber {

	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		ModInit.onInitializeClient();
		if(ModList.get().isLoaded("scalablelux")){
			ThreadChecker.hasScalableLux();
			ModInit.LOGGER.info("Found ScalableLux, enabling compatibility fix.");
		};
	}
}
*///?}
