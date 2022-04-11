package me.mrvintage.kingdom.magic.parser;

import me.mrvintage.kingdom.magic.parser.token.SpellToken;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.spell.SpellTarget;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface NounParsable {

    String[] matches();

    @NotNull
    SpellTarget getTarget(SpellToken token);
}
