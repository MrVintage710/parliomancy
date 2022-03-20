package me.mrvintage.parlio.mixin;

import me.mrvintage.parlio.progresion.ProgressionManager;
import me.mrvintage.parlio.progresion.level.LevelAction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {

    @Shadow @Final private PlayerEntity player;

    @Shadow private int amount;

    @Inject(at = @At("HEAD"), method = "onCrafted(Lnet/minecraft/item/ItemStack;)V", cancellable = true)
    public void onCrafted(ItemStack stack, CallbackInfo ci) {
        System.out.println("CRAFTED " + amount + " " + stack.getName().asString());
        if(!player.getEntityWorld().isClient && stack.getItem() == Items.STICK) {
            System.out.println("CRAFT STICK");
            ProgressionManager.envokeLevelAction((ServerPlayerEntity) player, LevelAction.CRAFT_STICK, amount);
        }
    }

}
