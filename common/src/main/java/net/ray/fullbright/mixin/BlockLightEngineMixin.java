//package net.ray.fullbright.mixin;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.lighting.BlockLightEngine;
//import net.ray.fullbright.config.ModConfig;
//import net.ray.fullbright.ThreadChecker;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Environment(EnvType.CLIENT)
//@Mixin(BlockLightEngine.class)
//public class BlockLightEngineMixin {
//
//
//    @Inject(method = "propagateIncrease", at = @At("HEAD"), cancellable = true)
//    private void DisablepropagateIncrease(long l, long m, int i, CallbackInfo ci){
//        if (ModConfig.enableFullbright && ThreadChecker.isClient() ) {
//            if (ModConfig.debugMode) {
//                ci.cancel();
//            }
//        }
//    }
//    @Inject(method = "propagateDecrease", at = @At("HEAD"), cancellable = true)
//    private void DisablepropagateDecrease(long l, long m, CallbackInfo ci){
//        if (ModConfig.enableFullbright && ThreadChecker.isClient()) {
//            if (ModConfig.debugMode) {
//                ci.cancel();
//            }
//        }
//    }
//    @Inject(method = "getEmission", at = @At("HEAD"), cancellable = true)
//    private void disableEmission(long l, BlockState blockState, CallbackInfoReturnable<Integer> cir){
//        if (ModConfig.enableFullbright && ThreadChecker.isClient()) {
//            if (ModConfig.debugMode) {
//                cir.setReturnValue(15);
//            }
//        }
//    }
//    @Inject(method = "propagateLightSources", at = @At("HEAD"), cancellable = true)
//    private void disablepropagateLightSources(ChunkPos chunkPos, CallbackInfo ci){
//        if (ModConfig.enableFullbright && ThreadChecker.isClient()) {
//            if (ModConfig.debugMode) {
//                ci.cancel();
//            }
//        }
//    }
//    @Inject(method = "checkNode", at = @At("HEAD"), cancellable = true)
//    private void disableCheckNode(long pos, CallbackInfo ci) {
//        if (ModConfig.enableFullbright && ThreadChecker.isClient()&& !ThreadChecker.shouldDelay()) {
//            if (ModConfig.debugMode) {
//                ci.cancel();
//            }
//        }
//    }
//}