package me.mrvintage.kingdom.test;

import me.mrvintage.kingdom.lang.LanguageManager;
import me.mrvintage.kingdom.lang.LanguageMap;
import me.mrvintage.kingdom.magic.parser.SpellParser;
import me.mrvintage.kingdom.magic.parser.token.SpellToken;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellParserTest {

    @BeforeAll
    static void setup() {
        SpellParser.init();
    }

    @Test
    void parseNounPair() {
        SpellTokenList list = new SpellTokenList();
        list.add(new SpellToken(SpellTokenType.NOUN, LanguageManager.ELVEN, "me"));
        list.add(new SpellToken(SpellTokenType.ADJECTIVE, LanguageManager.ELVEN, "rock"));

        var out = SpellParser.parseNounPair(list);

        assertFalse(out.isEmpty());
    }

    @Test
    void parseNoun() {
        SpellTokenList list = new SpellTokenList();
        list.add(new SpellToken(SpellTokenType.NOUN, LanguageManager.ELVEN, "soulhome"));

        var noun = SpellParser.parseNoun(list);

        assertFalse(noun.isEmpty());
        assertFalse(!list.isAtEnd());
    }

    @Test
    void parseVerb() {
        SpellTokenList list = new SpellTokenList();
        list.add(new SpellToken(SpellTokenType.VERB, LanguageManager.ELVEN, "travel"));

        var noun = SpellParser.parseVerb(list);

        assertFalse(noun.isEmpty());
        assertFalse(!list.isAtEnd());
    }

    @Test
    void parseAdjective() {
        SpellTokenList list = new SpellTokenList();
        list.add(new SpellToken(SpellTokenType.ADJECTIVE, LanguageManager.ELVEN, "rocky"));

        var noun = SpellParser.parseAdjective(list);

        assertFalse(noun.isEmpty());
    }

}