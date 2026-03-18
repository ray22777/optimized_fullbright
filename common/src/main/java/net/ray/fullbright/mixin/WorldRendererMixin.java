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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Environment(EnvType.CLIENT)
@Mixin(value = LevelRenderer.class, priority = 10005)
public abstract class WorldRendererMixin {

    @Inject(
            method = "getLightColor*",
            at = @At("HEAD"),
            cancellable = true)
    private static void onGetLightmapCoordinates(CallbackInfoReturnable<Integer> cir) {
        if(FullBrightToggle.isEnabled()){
            cir.setReturnValue(15728880);
            cir.cancel();
        }
    }
    }