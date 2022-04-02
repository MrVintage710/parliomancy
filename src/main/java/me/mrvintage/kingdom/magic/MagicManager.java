package me.mrvintage.kingdom.magic;

import me.mrvintage.kingdom.magic.parser.*;
import me.mrvintage.kingdom.magic.spell.Spell;
import me.mrvintage.kingdom.magic.spell.SpellAction;
import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.verb.TravelVerb;
import me.mrvintage.kingdom.progresion.PlayerMemory;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Optional;

public class MagicManager {

    private static HashMap<String, VerbParsable> verbs = new HashMap<>();
    private static HashMap<String, NounParsable> nouns = new HashMap<>();

    public static void init() {
        //Add Verbs
        addVerb(new TravelVerb());

        //Add Nouns
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

    public static Optional<SpellAction> parseVerb(SpellTokenList tokens) {
        return verbs.get(tokens.peek().getValue()).parse(tokens);
    }

    public static Optional<SpellTarget> parseNoun(SpellTokenList tokens) {
        return nouns.get(tokens.peek().getValue()).parse(tokens);
    }

    private static void addVerb(VerbParsable parsable) {
        for(String match : parsable.matches()) {
            verbs.put(match, parsable);
        }
    }

    private static void addNoun(NounParsable parsable) {
        for(String match : parsable.matches()) {
            nouns.put(match, parsable);
        }
    }
}
