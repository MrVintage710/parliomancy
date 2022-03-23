package me.mrvintage.kingdom.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface OnPlayerConnectCallback {

    Event<OnPlayerConnectCallback> EVENT = EventFactory.createArrayBacked(OnPlayerConnectCallback.class,
            (listeners) -> player -> {
                for(OnPlayerConnectCallback listener : listeners) {
                    listener.onPlayerConnect(player);
                }
            }
    );

    void onPlayerConnect(ServerPlayerEntity player);
}
