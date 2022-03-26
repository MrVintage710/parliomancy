package me.mrvintage.kingdom.mixin;

import me.mrvintage.kingdom.event.OnPlayerConnectCallback;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    private static ArrayList<Identifier> startingRecipes = new ArrayList<>();

    static {
        startingRecipes.add(new Identifier("minecraft:stick"));
        startingRecipes.add(new Identifier("minecraft:crafting_table"));
        startingRecipes.add(new Identifier("minecraft:wooden_axe"));
        startingRecipes.add(new Identifier("minecraft:wooden_pickaxe"));
        startingRecipes.add(new Identifier("minecraft:wooden_shovel"));
        startingRecipes.add(new Identifier("minecraft:wooden_hoe"));
        startingRecipes.add(new Identifier("minecraft:wooden_sword"));
    }

    @Inject(at = @At("TAIL"), method = "onPlayerConnect")
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        OnPlayerConnectCallback.EVENT.invoker().onPlayerConnect(player);

//        if(!ProgressionManager.hasBeenInitialized(player)) {
//            ProgressionManager.initPlayer(player);
//        }

        //System.out.println(player.getDisplayName().asString() + " entered the battlefield.");
    }
}
