
package net.ray.fullbright.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.state.LightmapRenderState;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(
        value = {Lightmap.class},
        priority = 10005
)
public abstract class LightmapMixin {
    @Inject(
            method = {"render"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void cancelLightUpdate(LightmapRenderState renderState, CallbackInfo ci) {
        if (FullBrightToggle.isEnabled()) {
            if (FullBrightToggle.update) {
                FullBrightToggle.update = false;
            } else {
                ci.cancel();
            }
        }

    }
}
