package me.mrvintage.kingdom.magic.spell.verb;

import me.mrvintage.kingdom.magic.parser.SpellTokenType;
import me.mrvintage.kingdom.magic.spell.SpellAction;
import me.mrvintage.kingdom.magic.parser.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.VerbParsable;

import java.util.Optional;

public class TravelVerb implements VerbParsable {

    @Override
    public String[] matches() {
        return new String[]{"travel", "traverse"};
    }

    @Override
    public Optional<SpellAction> parse(SpellTokenList tokens) {
        if(tokens.peekType(SpellTokenType.PREPOSITION)) {
            var preposition = tokens.pop();

            if(preposition.matches("to")) {
                if(tokens.peekType(SpellTokenType.NOUN)) {

                }
            }
        }


        return Optional.empty();
    }
}
