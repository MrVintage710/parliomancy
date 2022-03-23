package me.mrvintage.kingdom.mixin;

import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(ServerRecipeBook.class)
public class ServerRecipeBookMixin {

    @Inject(at = @At("HEAD"), method = "unlockRecipes", cancellable = true)
    public void unlockRecipe(Collection<Recipe<?>> recipes, ServerPlayerEntity player, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
        cir.cancel();
    }
}
