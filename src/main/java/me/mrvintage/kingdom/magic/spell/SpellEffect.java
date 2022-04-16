package me.mrvintage.kingdom.magic.spell;

import java.util.Optional;

public interface SpellEffect {

    int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier);

    boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier);
}
