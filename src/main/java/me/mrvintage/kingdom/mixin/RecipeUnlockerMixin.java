package me.mrvintage.kingdom.mixin;

import net.minecraft.recipe.RecipeUnlocker;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeUnlocker.class)
public interface RecipeUnlockerMixin {

//    @Inject(at = @At("HEAD"), method = "shouldCraftRecipe", cancellable = true)
//    default void shouldCraftRecipe(World world, ServerPlayerEntity player, Recipe<?> recipe, CallbackInfoReturnable<Boolean> cir){
//        cir.setReturnValue(false);
//        cir.cancel();
//    }

}
