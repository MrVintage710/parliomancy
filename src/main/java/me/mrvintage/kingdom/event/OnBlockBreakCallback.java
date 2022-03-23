package me.mrvintage.kingdom.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface OnBlockBreakCallback {

    Event<OnBlockBreakCallback> EVENT = EventFactory.createArrayBacked(OnBlockBreakCallback.class,
            (listeners) -> ((serverWorld, player, pos, state) -> {
                for(OnBlockBreakCallback listener : listeners) {
                    listener.OnBlockBreak(serverWorld, player, pos, state);
                }
            }));

    void OnBlockBreak(ServerWorld serverWorld, ServerPlayerEntity player, BlockPos pos, BlockState state);
}
