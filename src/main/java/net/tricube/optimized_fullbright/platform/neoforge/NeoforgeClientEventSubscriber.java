package net.tricube.optimized_fullbright.platform.neoforge;

//? neoforge {

/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.tricube.optimized_fullbright.ModInit;
import net.tricube.optimized_fullbright.ThreadChecker;

@EventBusSubscriber(modid = ModInit.MOD_ID, value = Dist.CLIENT)
public class NeoforgeClientEventSubscriber {
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
