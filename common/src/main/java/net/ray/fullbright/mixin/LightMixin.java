package net.ray.fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LightEngine;
import net.ray.fullbright.FullBrightToggle;
import net.ray.fullbright.ThreadChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(value = LightEngine.class, priority = 2000)
public abstract class LightMixin {

    @Inject(
            method = "getLightValue",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetLightValue(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled && !ThreadChecker.isIntegratedServerThread()) {
                cir.setReturnValue(15);
        }
    }

}