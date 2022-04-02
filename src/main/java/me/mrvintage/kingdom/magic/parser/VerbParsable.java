package me.mrvintage.kingdom.magic.parser;

import me.mrvintage.kingdom.magic.spell.SpellAction;

import java.util.Optional;

public interface VerbParsable {

    String[] matches();

    Optional<SpellAction> parse(SpellTokenList tokens);
}
