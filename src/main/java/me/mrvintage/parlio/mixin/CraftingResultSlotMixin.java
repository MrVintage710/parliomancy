package me.mrvintage.parlio.mixin;

import me.mrvintage.parlio.progresion.ProgressionManager;
import me.mrvintage.parlio.progresion.level.LevelAction;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {

    @Shadow @Final private PlayerEntity player;

    @Shadow private int amount;

    @Inject(at = @At("HEAD"), method = "onCrafted(Lnet/minecraft/item/ItemStack;)V", cancellable = true)
    public void onCrafted(ItemStack stack, CallbackInfo ci) {
        //System.out.println("CRAFTED " + amount + " " + stack.getName().asString());
        if(!player.getEntityWorld().isClient) {
            Item item = stack.getItem();
            if(
                item == Items.STICK ||
                item == Items.OAK_BUTTON ||
                item == Items.BIRCH_BUTTON ||
                item == Items.SPRUCE_BUTTON ||
                item == Items.ACACIA_BUTTON ||
                item == Items.DARK_OAK_BUTTON ||
                item == Items.CRIMSON_BUTTON ||
                item == Items.WARPED_BUTTON ||
                item == Items.JUNGLE_BUTTON
            ) {
                ProgressionManager.envokeLevelAction((ServerPlayerEntity) player, LevelAction.CRAFT_SIMPLE_WOOD_ITEM, amount);
            } else if(
                item == Items.OAK_PLANKS ||
                item == Items.BIRCH_PLANKS ||
                item == Items.SPRUCE_PLANKS ||
                item == Items.JUNGLE_PLANKS ||
                item == Items.ACACIA_PLANKS ||
                item == Items.DARK_OAK_PLANKS
            ) {
                ProgressionManager.envokeLevelAction((ServerPlayerEntity) player, LevelAction.CRAFT_WOODEN_PLANK_ITEM, amount);
            } else if(
                    item == Items.WOODEN_AXE ||
                    item == Items.WOODEN_HOE ||
                    item == Items.WOODEN_PICKAXE ||
                    item == Items.WOODEN_SHOVEL ||
                    item == Items.WOODEN_SWORD
            ) {
                ProgressionManager.envokeLevelAction((ServerPlayerEntity) player, LevelAction.CRAFT_WOODEN_PLANK_ITEM, amount);
            }
        }


    }

}
