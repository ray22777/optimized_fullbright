
package net.tricube.optimized_fullbright.mixin;


import net.minecraft.core.BlockPos;
//~ if >=26.1 '.BlockAndTintGetter' -> '.BlockAndLightGetter'
import net.minecraft.world.level.BlockAndTintGetter;

import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.lighting.LightEventListener;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//?if fabric{
//~ if >=26.1 'BlockAndTintGetter' -> 'BlockAndLightGetter'
@Mixin(value = BlockAndTintGetter.class, priority = 10005) //disable when in forge/neoforge as they seem to break
public interface  BlockTintGetterMixin {
	@Inject(method = "getBrightness", at = @At("HEAD"), cancellable = true, require = 0)
	private void disableGetBrightness(CallbackInfoReturnable<Integer> cir) {
		if(!Config.forceMaxBrightness.get()) return;
		if(ThreadChecker.shouldCancel()) {
			cir.setReturnValue(15);
			cir.cancel();
		}
	}

	@Inject(method = "getRawBrightness", at = @At("HEAD"), cancellable = true, require = 0)
	private void disableGetRawBrightness(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
		if(!Config.forceMaxBrightness.get()) return;
		if(Config.enableFullbright.get()&& ThreadChecker.isClient()) {
			cir.setReturnValue(15);
			cir.cancel();
		}
	}
}
//?}else{
/*@Mixin(value = Config.class, priority = 1)
public abstract class   BlockTintGetterMixin {

}
*///?}

