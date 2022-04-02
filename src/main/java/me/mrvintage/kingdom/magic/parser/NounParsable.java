package me.mrvintage.kingdom.magic.parser;

import me.mrvintage.kingdom.magic.spell.SpellTarget;

import java.util.Optional;

public interface NounParsable {

    String[] matches();

    Optional<SpellTarget> parse(SpellTokenList tokens);
}
