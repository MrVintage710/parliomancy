package me.mrvintage.kingdom.magic.parser.verb;

import me.mrvintage.kingdom.magic.spell.*;

import java.util.Optional;

public enum SpellVerb implements SpellEffect {
    TRAVEL(new TravelVerbEffect(), "travel", "sojourn", "journey");

    private SpellEffect effect;
    private String[] matches;

    SpellVerb(SpellEffect effect, String... matches) {
        this.effect = effect;
        this.matches = matches;
    }

    public String[] getMatches() {
        return matches;
    }

    @Override
    public int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        return effect.getCost(target, source, prepositionList, Optional.empty());
    }

    @Override
    public boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        return effect.execute(target, source, prepositionList, Optional.empty());
    }
}
