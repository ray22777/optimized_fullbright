package net.tricube.optimized_fullbright.mixin;


import net.minecraft.client.renderer.LevelRenderer;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelRenderer.class, priority = 10005)
public abstract class WorldRendererMixin {
	//? if <26.2{
	@Inject(method = "getLightCoords*", at = @At("HEAD"), cancellable = true)
	private static void onGetLightmapCoordinates(CallbackInfoReturnable<Integer> cir) {
		if(!Config.forceMaxBrightness.get()) return;
		if(Config.enableFullbright.get()){
			cir.setReturnValue(15728880);
			cir.cancel();
		}
	}
	//?}

    }
