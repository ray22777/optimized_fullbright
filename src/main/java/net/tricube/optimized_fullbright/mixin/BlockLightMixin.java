package net.tricube.optimized_fullbright.mixin;

import net.minecraft.world.level.lighting.BlockLightEngine;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlockLightEngine.class, priority = 10005)
public abstract class BlockLightMixin {

    @Inject(method = "propagateIncrease", at = @At("HEAD"), cancellable = true)
    private void disablePropagateIncrease(long l, long m, int i, CallbackInfo ci) {
		if(!Config.disablelightPropagation.get()) return;
        if (ThreadChecker.shouldCancel()) {
                ci.cancel();
        }
    }
    @Inject(method = "propagateDecrease", at = @At("HEAD"), cancellable = true)
    private void disablePropagateDecrease(long l, long m, CallbackInfo ci) {
		if(!Config.disablelightPropagation.get()) return;
        if (ThreadChecker.shouldCancel()) {
            ci.cancel();
        }
    }

}
