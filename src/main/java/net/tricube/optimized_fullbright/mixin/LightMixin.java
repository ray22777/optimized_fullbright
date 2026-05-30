package net.tricube.optimized_fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LightEngine;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(value = LightEngine.class, priority = 10005)
public abstract class LightMixin {

    @Inject(method = "getLightValue", at = @At("HEAD"), cancellable = true)
    private void disableGetLightValue(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
		if(!Config.forceMaxBrightness.get()) return;
        if (ThreadChecker.shouldCancel()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

}
