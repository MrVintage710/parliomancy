package me.mrvintage.kingdom.magic.parser;

import static me.mrvintage.kingdom.magic.parser.token.SpellTokenType.*;

import me.mrvintage.kingdom.event.OnPlayerChatCallback;
import me.mrvintage.kingdom.lang.LanguageManager;
import me.mrvintage.kingdom.lang.LanguageMap;
import me.mrvintage.kingdom.magic.parser.adjective.SpellAdjective;
import me.mrvintage.kingdom.magic.parser.adverb.SpellAdverb;
import me.mrvintage.kingdom.magic.parser.noun.SpellNoun;
import me.mrvintage.kingdom.magic.parser.part.NounPairPart;
import me.mrvintage.kingdom.magic.parser.part.VerbPairPart;
import me.mrvintage.kingdom.magic.parser.preposition.SpellPreposition;
import me.mrvintage.kingdom.magic.parser.token.SpellToken;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.verb.SpellVerb;
import me.mrvintage.kingdom.magic.spell.*;

import java.util.HashMap;
import java.util.Optional;

public class SpellParser {

    private static HashMap<String, SpellPreposition> prepositions = new HashMap<>();
    private static HashMap<String, SpellAdjective> adjectives = new HashMap<>();
    private static HashMap<String, SpellNoun> nouns = new HashMap<>();
    private static HashMap<String, SpellVerb> verbs = new HashMap<>();
    private static HashMap<String, SpellAdverb> adverbs = new HashMap<>();

    public static void init() {
        addNouns();
        addAdjectives();
        addVerbs();
        addPrepositions();
        addAdverbs();

        OnPlayerChatCallback.EVENT.register(((player, message) -> {
            var tokens = SpellParser.tokenizeSpell(message, LanguageManager.ELVEN);
            var spellAction = SpellParser.parseSpellAction(tokens);
            if(spellAction.isPresent())
                spellAction.get().executeSpell(new SpellTarget(player, SpellTargetType.ENTITY), player.getWorld());
            return true;
        }));
    }

    public static Optional<Spell> parseSpell(SpellTokenList list) {
        return Optional.empty();
    }

    public Optional<Object> parseSpellSentence(SpellTokenList list) {
        return Optional.empty();
    }

    public static Optional<SpellExecutable> parseSpellAction(SpellTokenList list) {
        var verbPair = parseVerbPair(list);
        var group  = parseGroup(list);

        if(verbPair.isPresent()) {
            var spellAction = new SpellAction(verbPair.get(), group);
            while(list.match(PREPOSITION)) {
                var preposition = parsePreposition(list);
                var g = parseGroup(list);
                if(g.isPresent() && preposition.isPresent()) {
                    spellAction.add(preposition.get(), g.get());
                }
            }

            return Optional.of(spellAction);
        }

        return Optional.empty();
    }

    public static Optional<SpellEffect> parseVerbPair(SpellTokenList list) {
        var adverb = parseAdverb(list);
        var verb = parseVerb(list);

        if(verb.isPresent()) {
            var verbPair = new VerbPairPart(verb.get());

            if(adverb.isPresent()) {
                verbPair.setAdverb(adverb.get().getModifier());
            }

            return Optional.of(verbPair);
        }

        return Optional.empty();
    }

    public static Optional<TargetProvider> parseGroup(SpellTokenList tokens) {
        var target = parseTarget(tokens);
        if(target.isPresent()) {
            SpellGroup group = new SpellGroup();
            group.add(target.get());
            while(tokens.matchAndConsume(AND)) {
                var t = parseTarget(tokens);
                if(t.isPresent()) group.add(t.get());
            }

            return Optional.of(group);
        }

        return Optional.empty();
    }

    public static Optional<TargetProvider> parseTarget(SpellTokenList list) {
        return parseNounPair(list);
    }

    public static Optional<TargetProvider> parseNounPair(SpellTokenList tokens) {
        if(tokens.match(ADJECTIVE, NOUN)) {
            var adjective = parseAdjective(tokens);
            var noun = parseNoun(tokens);

            if(noun.isPresent()) {
                var pair = new NounPairPart(noun.get());
                if(adjective.isPresent()) pair.setAdjective(adjective.get());
                return Optional.of(pair);
            }
        }

        return Optional.empty();
    }

    public static SpellTokenList tokenizeSpell(String spell, LanguageMap language) {
        SpellTokenList list = new SpellTokenList();

        String[] sentences = spell.toLowerCase().split("\\.");
        for(String sentence : sentences) {
            String[] words = sentence.split(" ");
            for(String word : words) {
                list.add(tokenizeWord(word, language));
            }
            list.add(new SpellToken(PERIOD, language, "."));
        }

        return list;
    }

    private static SpellToken tokenizeWord(String word, LanguageMap language) {
        if(word.equals("and"))
            return new SpellToken(AND, language, word);

        if(nouns.containsKey(word))
            return new SpellToken(NOUN, language, word);

        if(verbs.containsKey(word))
            return new SpellToken(VERB, language, word);

        if(prepositions.containsKey(word))
            return new SpellToken(PREPOSITION, language, word);

        if(adjectives.containsKey(word))
            return new SpellToken(ADJECTIVE, language, word);

        if(adverbs.containsKey(word))
            return new SpellToken(ADVERB, language, word);

        return new SpellToken(NONE, language, word);
    }

    public static Optional<SpellAdjective> parseAdjective(SpellTokenList tokens) {
        if(tokens.match(ADJECTIVE)) {
            String name = tokens.pop().getValue();
            if(adjectives.containsKey(name)) {
                return Optional.of(adjectives.get(name));
            }
        }

        return Optional.empty();
    }

    public static Optional<SpellNoun> parseNoun(SpellTokenList tokens) {
        if(tokens.match(NOUN)) {
            String name = tokens.pop().getValue();
            if(nouns.containsKey(name)) {
                return Optional.of(nouns.get(name));
            }
        }

        return Optional.empty();
    }

    public static Optional<SpellEffect> parseVerb(SpellTokenList tokens) {
        if(tokens.match(VERB)) {
            String name = tokens.pop().getValue();
            if(verbs.containsKey(name)) {
                return Optional.of(verbs.get(name));
            }
        }

        return Optional.empty();
    }

    public static Optional<SpellAdverb> parseAdverb(SpellTokenList tokens) {
        if(tokens.match(ADVERB)) {
            String name = tokens.pop().getValue();
            if(adverbs.containsKey(name)) {
                return Optional.of(adverbs.get(name));
            }
        }

        return Optional.empty();
    }

    public static Optional<SpellPreposition> parsePreposition(SpellTokenList tokens) {
        if(tokens.match(PREPOSITION)) {
            String name = tokens.pop().getValue();
            if(prepositions.containsKey(name)) {
                return Optional.of(prepositions.get(name));
            }
        }

        return Optional.empty();
    }

    private static void addNouns() {
        for(SpellNoun noun : SpellNoun.values()) {
            for(String match : noun.getMatches()) {
                nouns.put(match, noun);
            }
        }
    }

    private static void addVerbs() {
        for(SpellVerb verb : SpellVerb.values()) {
            for(String match : verb.getMatches()) {
                verbs.put(match, verb);
            }
        }
    }

    private static void addAdverbs() {
        for(SpellAdverb verb : SpellAdverb.values()) {
            for(String match : verb.getMatches()) {
                adverbs.put(match, verb);
            }
        }
    }

    private static void addAdjectives() {
        for(SpellAdjective adjective : SpellAdjective.values()) {
            for(String match : adjective.getMatches()) {
                adjectives.put(match, adjective);
            }
        }
    }

    private static void addPrepositions() {
        for(SpellPreposition preposition : SpellPreposition.values()) {
            prepositions.put(preposition.getMatch(), preposition);
        }
    }
}
