package me.mrvintage.parlio.mixin;

import me.mrvintage.parlio.ParlioMod;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CraftRequestC2SPacket;
import net.minecraft.network.packet.c2s.play.RecipeBookDataC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayMixin {

    private static CommandBossBar bar = null;

    @Shadow public ServerPlayerEntity player;

    @Inject(at = @At("HEAD"), method = "onChatMessage", cancellable = true)
    public void onMessage(ChatMessageC2SPacket packet, CallbackInfo ci){
        System.out.println("CHAT");
        if(packet.getChatMessage().startsWith(":")) {

        }
    }

    @Inject(at = @At("HEAD"), method = "onCraftRequest", cancellable = true)
    public void onCraftCheck(CraftRequestC2SPacket packet, CallbackInfo ci) {
        System.out.println("Test");
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "onRecipeBookData", cancellable = true)
    public void onRecipeBookData(RecipeBookDataC2SPacket packet, CallbackInfo ci) {
        System.out.println("NEW RECIPE");
        ci.cancel();
    }
}
