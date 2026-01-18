

package net.ray.fullbright.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.ray.fullbright.FullBrightToggle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Options.class})
public abstract class GammaMixin {
    @Final
    @Shadow
    private OptionInstance<Double> gamma;

    @Inject(
            method = {"gamma()Lnet/minecraft/client/OptionInstance;"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private void changeGamma(CallbackInfoReturnable<OptionInstance<Double>> info) {
        if (FullBrightToggle.isEnabled()) {
            if ((Double)this.gamma.get() != (double)1000.0F) {
                this.gamma.set((double)1000.0F);
            }
        } else if ((Double)this.gamma.get() == (double)1000.0F) {
            this.gamma.set((double)1.0F);
        }

    }
}
