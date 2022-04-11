package me.mrvintage.kingdom.magic.parser;

import static me.mrvintage.kingdom.magic.parser.token.SpellTokenType.*;

import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.verb.VerbParsable;
import me.mrvintage.kingdom.magic.spell.Spell;
import me.mrvintage.kingdom.magic.spell.SpellAction;
import me.mrvintage.kingdom.magic.spell.SpellTarget;

import java.util.HashMap;
import java.util.Optional;

public class SpellParser {

    private static HashMap<String, VerbParsable> verbs = new HashMap<>();
    private static HashMap<String, NounParsable> nouns = new HashMap<>();

    public static void init() {

    }

    private static Optional<Spell> parseSpell(SpellTokenList list) {
        return Optional.empty();
    }

    private Optional<Object> parseSpellSentence(SpellTokenList list) {
        return Optional.empty();
    }

    private Optional<Object> parseSpellAction(SpellTokenList list) {
        return Optional.empty();
    }

    private Optional<Object> parseVerbPair(SpellTokenList list) {
        return Optional.empty();
    }

    private static Optional<Object> parseTarget(SpellTokenList list) {
        return Optional.empty();
    }

    private static Optional<Object> parseNounPair(SpellTokenList list) {
        if(list.match(ADJECTIVE, NOUN)) {
            if(list.match(ADJECTIVE)) {

            }
        }

        return Optional.empty();
    }

    public static Optional<SpellAction> parseVerb(SpellTokenList tokens) {
        return verbs.get(tokens.peek().getValue()).parse(tokens);
    }

    public static Optional<SpellTarget> parseNoun(SpellTokenList tokens) {
        //return nouns.get(tokens.peek().getValue()).getTarget(tokens);
        return Optional.empty();
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
