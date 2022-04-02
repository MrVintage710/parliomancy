package me.mrvintage.kingdom.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface OnPlayerChatCallback {

    Event<OnPlayerChatCallback> EVENT = EventFactory.createArrayBacked(OnPlayerChatCallback.class,
            (listeners) -> ((player, message) -> {
                for(OnPlayerChatCallback listener : listeners) {
                    if(!listener.OnPlayerChat(player, message)) return false;
                }

                return true;
            }));

    boolean OnPlayerChat(ServerPlayerEntity player, String message);
}
