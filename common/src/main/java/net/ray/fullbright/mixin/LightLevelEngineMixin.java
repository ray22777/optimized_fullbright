package net.ray.fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.ray.fullbright.FullBrightToggle;
import net.ray.fullbright.ThreadChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LevelLightEngine.class)
public abstract class LightLevelEngineMixin {

    @Inject(
            method = "getRawBrightness",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetRawBrightness(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled && !ThreadChecker.isIntegratedServerThread()) {
                cir.setReturnValue(15);
        }
    }

}