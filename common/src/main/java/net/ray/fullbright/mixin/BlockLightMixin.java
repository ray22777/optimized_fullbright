package net.ray.fullbright.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.BlockLightEngine;
import net.minecraft.world.level.lighting.LightEngine;
import net.ray.fullbright.FullBrightToggle;
import net.ray.fullbright.ThreadChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockLightEngine.class, priority = 10005)
public abstract class BlockLightMixin {

    @Inject(
            method = "propagateIncrease",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disablePropagateIncrease(long l, long m, int i, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled() && ThreadChecker.isClient()) {
                ci.cancel();
        }
    }
    @Inject(
            method = "propagateDecrease",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disablePropagateDecrease(long l, long m, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled() && ThreadChecker.isClient()) {
            ci.cancel();
        }
    }

}