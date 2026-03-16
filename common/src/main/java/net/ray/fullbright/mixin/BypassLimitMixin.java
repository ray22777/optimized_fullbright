

package net.ray.fullbright.mixin;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Environment(EnvType.CLIENT)
@Mixin(value = OptionInstance.UnitDouble.class, priority = 10005)
public abstract class BypassLimitMixin {
    @Inject(
            method = {"validateValue(Ljava/lang/Double;)Ljava/util/Optional;"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private void stopValidation(Double p_231747_, CallbackInfoReturnable<Optional<Double>> cir) {
        cir.setReturnValue(Optional.of(p_231747_));

    }
}
