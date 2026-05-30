

package net.tricube.optimized_fullbright.mixin;

import net.minecraft.client.OptionInstance;
import net.tricube.optimized_fullbright.ThreadChecker;
import net.tricube.optimized_fullbright.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = OptionInstance.UnitDouble.class, priority = 10005)
public abstract class BypassLimitMixin {
    @Inject(method = {"validateValue(Ljava/lang/Double;)Ljava/util/Optional;"}, at = {@At("RETURN")}, cancellable = true)
    private void stopValidation(Double d, CallbackInfoReturnable<Optional<Double>> cir) {
		if(!Config.overrideGamma.get()) return;
        cir.setReturnValue(Optional.of(d));
    }
}
