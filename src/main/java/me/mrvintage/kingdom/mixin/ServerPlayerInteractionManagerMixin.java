package me.mrvintage.kingdom.mixin;

import me.mrvintage.kingdom.event.OnBlockBreakCallback;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import me.mrvintage.kingdom.progresion.level.LevelAction;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

    @Shadow @Final protected ServerPlayerEntity player;

    @Inject(method = "finishMining", at = @At(value = "HEAD"))
    public void finishMining(BlockPos pos, PlayerActionC2SPacket.Action action, String reason, CallbackInfo ci) {
        var blockState = player.getWorld().getBlockState(pos);
        OnBlockBreakCallback.EVENT.invoker().OnBlockBreak(player.getWorld(), player, pos, blockState);

        if(
                blockState.getBlock() == Blocks.OAK_LOG ||
                blockState.getBlock() == Blocks.BIRCH_LOG ||
                blockState.getBlock() == Blocks.SPRUCE_LOG ||
                blockState.getBlock() == Blocks.ACACIA_LOG ||
                blockState.getBlock() == Blocks.JUNGLE_LOG ||
                blockState.getBlock() == Blocks.DARK_OAK_LOG
        ) {
            ProgressionManager.envokeLevelAction(player, LevelAction.BREAK_LOG, 1);
        }
    }
}
