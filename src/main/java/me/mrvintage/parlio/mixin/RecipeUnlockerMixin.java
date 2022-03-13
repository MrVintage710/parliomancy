package me.mrvintage.parlio.mixin;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeUnlocker.class)
public interface RecipeUnlockerMixin {

//    @Inject(at = @At("HEAD"), method = "shouldCraftRecipe", cancellable = true)
//    default void shouldCraftRecipe(World world, ServerPlayerEntity player, Recipe<?> recipe, CallbackInfoReturnable<Boolean> cir){
//        cir.setReturnValue(false);
//        cir.cancel();
//    }

}
