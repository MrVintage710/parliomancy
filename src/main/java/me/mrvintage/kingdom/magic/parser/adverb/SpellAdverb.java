package me.mrvintage.kingdom.magic.parser.adverb;

import me.mrvintage.kingdom.magic.spell.SpellEffectModifier;

public enum SpellAdverb {
    QUICK(new SpellEffectModifier()
            .withCastTimeModifier(0.8f)
            .withEffectivenessModifier(0.8f)
            .withCostModifier(1.1f)
            .withSpecialType("quickened"), "quickly");

    private SpellEffectModifier modifier;
    private String[] matches;

    SpellAdverb(SpellEffectModifier modifier, String... matches) {
        this.modifier = modifier;
        this.matches = matches;
    }

    public SpellEffectModifier getModifier() {
        return modifier;
    }

    public String[] getMatches() {
        return matches;
    }
}
