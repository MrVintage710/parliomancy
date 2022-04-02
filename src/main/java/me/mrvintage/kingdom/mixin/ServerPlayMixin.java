package me.mrvintage.kingdom.mixin;

import me.mrvintage.kingdom.event.OnPlayerChatCallback;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayMixin {

    @Shadow public ServerPlayerEntity player;

    @Inject(at = @At("HEAD"), method = "onChatMessage", cancellable = true)
    public void onMessage(ChatMessageC2SPacket packet, CallbackInfo ci){
        if(!OnPlayerChatCallback.EVENT.invoker().OnPlayerChat(player, packet.getChatMessage())) ci.cancel();
    }

//    @Inject(at = @At("HEAD"), method = "onCraftRequest", cancellable = true)
//    public void onCraftCheck(CraftRequestC2SPacket packet, CallbackInfo ci) {
//        System.out.println("Test");
//        ci.cancel();
//    }

//    @Inject(at = @At("HEAD"), method = "onRecipeBookData", cancellable = true)
//    public void onRecipeBookData(RecipeBookDataC2SPacket packet, CallbackInfo ci) {
//        System.out.println("NEW RECIPE");
//        ci.cancel();
//    }


}
