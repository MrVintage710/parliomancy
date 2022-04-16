package me.mrvintage.kingdom.magic.parser.part;

import me.mrvintage.kingdom.magic.spell.SpellEffect;
import me.mrvintage.kingdom.magic.spell.SpellEffectModifier;
import me.mrvintage.kingdom.magic.spell.SpellPrepositionList;
import me.mrvintage.kingdom.magic.spell.SpellTarget;

import java.util.Optional;

public class VerbPairPart implements SpellEffect {

    private SpellEffect verb;
    private Optional<SpellEffectModifier> adverb = Optional.empty();


    public VerbPairPart(SpellEffect verb) {
        this.verb = verb;
    }

    public VerbPairPart setAdverb(SpellEffectModifier adverb) {
        this.adverb = Optional.of(adverb);
        return this;
    }

    @Override
    public int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        if(adverb.isPresent() && modifier.isPresent()) return verb.getCost(target, source, prepositionList, Optional.of(adverb.get().merge(modifier.get())));
        return verb.getCost(target, source, prepositionList, adverb);
    }

    @Override
    public boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        if(adverb.isPresent() && modifier.isPresent()) return verb.execute(target, source, prepositionList, Optional.of(adverb.get().merge(modifier.get())));
        return verb.execute(target, source, prepositionList, adverb);
    }
}
