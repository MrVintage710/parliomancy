package me.mrvintage.kingdom.magic.parser.verb;

import me.mrvintage.kingdom.magic.parser.token.SpellTokenList;
import me.mrvintage.kingdom.magic.spell.*;

import java.util.List;
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
    public int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList) {
        return effect.getCost(target, source, prepositionList);
    }

    @Override
    public boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList) {
        return effect.execute(target, source, prepositionList);
    }
}
