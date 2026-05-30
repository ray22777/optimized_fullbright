
package net.tricube.optimized_fullbright.mixin;

//~ if >=26.1 'LightTexture' -> 'Lightmap'
import net.minecraft.client.renderer.LightTexture;
//? if>=26.1
//import net.minecraft.client.renderer.state.LightmapRenderState;
import net.tricube.optimized_fullbright.FullbrightState;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//~ if >=26.1 'LightTexture' -> 'Lightmap'
@Mixin(value = LightTexture.class, priority = 10005)
public abstract class LightmapMixin {
	//~ if >=26.1 'updateLightTexture(F)V' -> 'render'
    @Inject(method = {"updateLightTexture(F)V"}, at = {@At("HEAD")}, cancellable = true)
	//~ if >=26.1 'float partialTick' -> 'LightmapRenderState renderState'
    private void disableLightTextureUpdate(float partialTick, CallbackInfo ci) {
		if(!Config.disableTextureUpdates.get()) return;
        if (Config.enableFullbright.get()) {
            if (FullbrightState.requireUpdate) {
                FullbrightState.setRequireUpdate(false);
            } else {
                ci.cancel();
            }
        }

    }
}
