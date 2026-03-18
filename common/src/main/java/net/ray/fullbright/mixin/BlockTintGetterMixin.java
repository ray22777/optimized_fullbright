
package net.ray.fullbright.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.ray.fullbright.FullBrightToggle;
import net.ray.fullbright.ThreadChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(value = BlockAndTintGetter.class, priority = 10005)
public interface  BlockTintGetterMixin {
    @Inject(
            method = "getBrightness",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableGetBrightness(CallbackInfoReturnable<Integer> cir) {
        if(FullBrightToggle.isEnabled() && ThreadChecker.isClient()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

    @Inject(
            method = "getRawBrightness",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableGetRawBrightness(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
        if(FullBrightToggle.isEnabled() && ThreadChecker.isClient()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }
}
