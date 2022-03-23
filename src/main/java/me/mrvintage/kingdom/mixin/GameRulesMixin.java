package me.mrvintage.kingdom.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRules.class)
public class GameRulesMixin {

    @Inject(at = @At("HEAD"), method = "getBoolean", cancellable = true)
    public void getBoolean(GameRules.Key<GameRules.BooleanRule> rule, CallbackInfoReturnable<Boolean> cir) {
        if(rule == GameRules.DO_LIMITED_CRAFTING) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
