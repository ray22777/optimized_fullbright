package net.ray.fullbright.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.chunk.DataLayer;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.ray.fullbright.FullBrightToggle;
import net.ray.fullbright.ThreadChecker;
import net.ray.fullbright.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(value = LevelLightEngine.class, priority = 10005)
public abstract class LightLevelEngineMixin {
    @Inject(method = "runLightUpdates", at = @At("HEAD"), cancellable = true)
    private void disableLightUpates(CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled() && ThreadChecker.isClient() && ThreadChecker.checkScalableLux()) {
            cir.setReturnValue(0);
        }
    } // only enable when scalableLux is present, otherwise it breaks mc
    //BlockLightMixin disables subclasses of this while not using scalablelux

    @Inject(
            method = "getRawBrightness",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableGetRawBrightness(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
        if (FullBrightToggle.isEnabled() && ThreadChecker.isClient()) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

}