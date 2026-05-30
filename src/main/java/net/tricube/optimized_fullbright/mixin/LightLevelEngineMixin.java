package net.tricube.optimized_fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelLightEngine.class, priority = 10005)
public abstract class LightLevelEngineMixin {
    @Inject(method = "runLightUpdates", at = @At("HEAD"), cancellable = true)
    private void disableLightUpates(CallbackInfoReturnable<Integer> cir) {
		if(!Config.disableLightUpdates.get()) return;
        if (ThreadChecker.shouldCancel() && ThreadChecker.checkScalableLux()) {
            cir.setReturnValue(0);
        }
    } // only enable when scalableLux is present, otherwise it breaks mc
    //BlockLightMixin disables subclasses of this while not using scalablelux

    @Inject(method = "getRawBrightness", at = @At("HEAD"), cancellable = true)
    private void disableGetRawBrightness(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
		if(!Config.forceMaxBrightness.get()) return;
        if (ThreadChecker.shouldCancel()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

}
