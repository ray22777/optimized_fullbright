package net.ray.fullbright.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
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
    @Shadow @Final private NativeImage lightPixels;
    @Shadow @Final private DynamicTexture lightTexture;
    @Inject(method = "getDarknessGamma", at = @At("HEAD"), cancellable = true)
    private void onGetDarknessFactor(float tickDelta, CallbackInfoReturnable<Float> cir) {
        if(FullBrightToggle.isEnabled){
            cir.setReturnValue(0.0F);
        }
    }

    @Inject(method = "updateLightTexture", at = @At("HEAD"), cancellable = true)
    private void fullbright(float partialTick, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled) {
            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    lightPixels.setPixelRGBA(x, y, 0xFFFFFFFF);
                }
            }

            lightTexture.upload();
            ci.cancel();
        }
    }
}

