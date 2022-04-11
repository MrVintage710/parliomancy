package me.mrvintage.kingdom.magic.parser.verb;

import me.mrvintage.kingdom.magic.parser.token.SpellTokenType;
import me.mrvintage.kingdom.magic.spell.SpellAction;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;

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
