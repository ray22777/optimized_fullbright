package net.ray.fullbright.mixin;



import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.LevelRenderer;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = {LevelRenderer.class},
        priority = 10005
)
public abstract class WorldRendererMixin {
    @Inject(
            method = {"getLightCoords*"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private static void onGetLightmapCoordinates(CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled()) {
            cir.setReturnValue(15728880);
            cir.cancel();
        }

    }
}
