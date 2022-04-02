package me.mrvintage.kingdom.event;

import me.mrvintage.kingdom.magic.parser.SpellTokenList;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface OnSpellcastAttemptCallback {

    Event<OnSpellcastAttemptCallback> EVENT = EventFactory.createArrayBacked(OnSpellcastAttemptCallback.class,
            (listeners) -> (player, tokens, message) -> {
                for(OnSpellcastAttemptCallback listener : listeners) {
                    listener.onSpellcastAttempt(player, tokens, message);
                }
            }
    );

    void onSpellcastAttempt(ServerPlayerEntity player, SpellTokenList tokens, String message);
}
