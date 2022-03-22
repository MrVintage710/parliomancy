package me.mrvintage.parlio.mixin;

import me.mrvintage.parlio.progresion.ProgressionManager;
import me.mrvintage.parlio.progresion.level.LevelAction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(at = @At("HEAD"), method = "postPlacement", cancellable = true)
    public void postPlacement(BlockPos pos, World world, PlayerEntity player, ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(!world.isClient()) {
            Block block = state.getBlock();
            if(
                block == Blocks.OAK_PLANKS ||
                block == Blocks.BIRCH_PLANKS ||
                block == Blocks.SPRUCE_PLANKS ||
                block == Blocks.JUNGLE_PLANKS ||
                block == Blocks.ACACIA_PLANKS ||
                block == Blocks.DARK_OAK_PLANKS
            ) {
                ProgressionManager.envokeLevelAction((ServerPlayerEntity) player, LevelAction.PLACE_WOODEN_PLANKS, 1);
            }
        }
    }
}
