

package net.tricube.optimized_fullbright.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;

import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Options.class, priority = 10005)
public abstract class GammaMixin {
    @Final
    @Shadow
    private OptionInstance<Double> gamma;

    @Inject(method = {"gamma()Lnet/minecraft/client/OptionInstance;"}, at = {@At("RETURN")}, cancellable = true)
    private void changeGamma(CallbackInfoReturnable<OptionInstance<Double>> info) {
		if(!Config.overrideGamma.get()) return;
		if(Config.enableFullbright.get()){
			if(this.gamma.get() != 1000.0F){
				this.gamma.set((double)1000.0F);
			}
		}
		else{
			if(this.gamma.get() == 1000.0F){
				this.gamma.set((double)1.0F);
			}
		}
    }
}
