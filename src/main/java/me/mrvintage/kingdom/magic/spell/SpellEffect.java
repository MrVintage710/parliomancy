package me.mrvintage.kingdom.magic.spell;

import java.util.List;

public interface SpellEffect {

    int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList);

    boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList);
}
