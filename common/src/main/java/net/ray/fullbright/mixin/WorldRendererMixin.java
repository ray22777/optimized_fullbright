package net.ray.fullbright.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Environment(EnvType.CLIENT)
@Mixin(value = LevelRenderer.class, priority = -9999) //lower priority for sodium
public abstract class WorldRendererMixin {

    @Inject(
            method = "getLightColor*",
            at = @At("HEAD"),
            cancellable = true)
    private static void onGetLightmapCoordinates(BlockAndTintGetter level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(FullBrightToggle.isEnabled()){
            cir.setReturnValue(15728880);
            cir.cancel();
        }
    }
    @Final
    @Mutable
    private static LevelRenderer.BrightnessGetter DEFAULT;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onClinit(CallbackInfo ci) {
        if (FullBrightToggle.isEnabled()) {
            DEFAULT = (level, pos) -> 15728880;
        }
    }
}