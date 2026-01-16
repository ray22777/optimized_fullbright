package net.ray.fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.lighting.LightEngine;
import net.ray.fullbright.FullBrightToggle;
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
        if (FullBrightToggle.isEnabled) {
            cir.setReturnValue(15); // Maximum light level
        }
    }

//    // Also override the individual engine methods
//    @Inject(
//            method = "getLightValue",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private void onGetLightValue(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
//        if (FullBrightToggle.isEnabled) {
//            cir.setReturnValue(15); // Maximum light level
//        }
//    }
}