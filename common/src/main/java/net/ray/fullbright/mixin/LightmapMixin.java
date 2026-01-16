package net.ray.fullbright.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.level.dimension.DimensionType;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LightTexture.class)
public abstract class LightmapMixin {
    @Shadow
    @Final
    private NativeImage lightPixels;

    @Inject(method = "getDarknessGamma", at = @At("HEAD"), cancellable = true)
    private void onGetDarknessFactor(float tickDelta, CallbackInfoReturnable<Float> cir) {
        if(FullBrightToggle.isEnabled){
            cir.setReturnValue(0.0F);
        }
    }
    @Inject(
            method = "updateLightTexture",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onUpdateLightTexture(float partialTick, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled) {
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    this.lightPixels.setPixelRGBA(j, i, 0xFFF0F000);
                }
            }
            ci.cancel();
        }
    }


//    @Inject(method = "updateLightTexture", at = @At("HEAD"), cancellable = true)
//    private void onUpdate(float delta, CallbackInfo ci) {
//        if(FullBrightToggle.isEnabled) {
//            ci.cancel();
//        }
//    }
}

