package me.mrvintage.kingdom.magic;

import me.mrvintage.kingdom.event.OnSpellcastAttemptCallback;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenType;
import me.mrvintage.kingdom.magic.spell.Spell;
import me.mrvintage.kingdom.progresion.PlayerMemory;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.minecraft.server.network.ServerPlayerEntity;

public class MagicManager {



    public static void init() {
        OnSpellcastAttemptCallback.EVENT.register(MagicManager::onSpellcastAttempt);

    }

    public static void onSpellcastAttempt(ServerPlayerEntity player, SpellTokenList tokens, String message) {
        if(message.equalsIgnoreCase("travel to soulhome")) {
            PlayerMemory mem = ProgressionManager.getMemory(player);
            player.teleport(mem.getSoulhome(), 0, 121, 0, 0, 0);
        }
    }

    private static Spell parseSpell(SpellTokenList tokens) {

        while(!tokens.isAtEnd()) {

        }

        if(tokens.peekType(SpellTokenType.VERB)) {

        }

        return null;
    }

    private void parseSpellAction(SpellTokenList tokens) {

    }


}
