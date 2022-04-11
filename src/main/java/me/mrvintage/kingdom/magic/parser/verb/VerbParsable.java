package me.mrvintage.kingdom.magic.parser.verb;

import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.spell.SpellAction;

import java.util.Optional;

public interface VerbParsable {

    String[] matches();

    Optional<SpellAction> parse(SpellTokenList tokens);
}
