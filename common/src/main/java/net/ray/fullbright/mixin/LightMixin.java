package net.ray.fullbright.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public abstract class LightMixin {

    // Override individual light values (block light AND sky light)
    @Inject(
            method = "getLightValue",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetLightValue(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled) {
            cir.setReturnValue(15); // Maximum light value
        }
    }

    // Make blocks NOT block any light
    @Inject(
            method = "getOpacity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetOpacity(BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled) {
            cir.setReturnValue(0); // Zero opacity = no light blocking
        }
    }

    // Also override the static method that checks light properties
    @Inject(
            method = "hasDifferentLightProperties",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onHasDifferentLightProperties(
            net.minecraft.world.level.BlockGetter level,
            BlockPos pos,
            BlockState oldState,
            BlockState newState,
            CallbackInfoReturnable<Boolean> cir
    ) {
        // Always return false when fullbright is enabled
        // This prevents lighting updates when blocks change
        if (FullBrightToggle.isEnabled) {
            cir.setReturnValue(false);
        }
    }
}