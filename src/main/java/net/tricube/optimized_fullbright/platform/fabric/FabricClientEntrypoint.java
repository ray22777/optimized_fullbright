package net.tricube.optimized_fullbright.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.tricube.optimized_fullbright.ModInit;
import net.fabricmc.loader.api.FabricLoader;
import net.tricube.optimized_fullbright.ThreadChecker;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModInit.onInitializeClient();
		if(FabricLoader.getInstance().isModLoaded("scalablelux")){
			ThreadChecker.hasScalableLux();
			ModInit.LOGGER.info("Found ScalableLux, enabling compatibility fix.");
		};
	}
}
//?}
