package net.ray.fullbright.mixin;


import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightTexture.class)
public abstract class LightmapMixin {
    @Inject(method = "getDarknessGamma", at = @At("HEAD"), cancellable = true)
    private void onGetDarknessFactor(float tickDelta, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0.0F);
    }

    @Inject(method = "updateLightTexture", at = @At("HEAD"), cancellable = true)
    private void onUpdate(float delta, CallbackInfo ci) {
        ci.cancel();
    }
}

