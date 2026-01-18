
package net.ray.fullbright.mixin;

import net.minecraft.client.renderer.LightTexture;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LightTexture.class})
public abstract class LightmapMixin {
    @Inject(
            method = {"updateLightTexture(F)V"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void fullbright(float partialTick, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled()) {
            if (FullBrightToggle.update) {
                FullBrightToggle.update = false;
            } else {
                ci.cancel();
            }
        }

    }
}
