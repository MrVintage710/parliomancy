package me.mrvintage.kingdom.magic.spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellEffectModifier {
    private float castTimeModifier = 1;
    private float effectivenessModifier = 1;
    private float costModifier = 1;
    private ArrayList<String> specialTypes = new ArrayList<>();

    public SpellEffectModifier withCastTimeModifier(float mod) {
        this.castTimeModifier = mod;
        return this;
    }

    public SpellEffectModifier withEffectivenessModifier(float mod) {
        this.effectivenessModifier = mod;
        return this;
    }

    public SpellEffectModifier withCostModifier(float modifier) {
        this.costModifier = modifier;
        return this;
    }

    public SpellEffectModifier withSpecialType(String... type) {
        specialTypes.addAll(Arrays.stream(type).toList());
        return this;
    }

    public SpellEffectModifier withSpecialType(List<String> type) {
        specialTypes.addAll(type);
        return this;
    }

    public float getCastTimeModifier() {
        return castTimeModifier;
    }

    public float getEffectivenessModifier() {
        return effectivenessModifier;
    }

    public float getCostModifier() {
        return costModifier;
    }

    public List<String> getSpecialTypes() {
        return specialTypes;
    }

    public SpellEffectModifier merge(SpellEffectModifier other) {
        SpellEffectModifier r = new SpellEffectModifier()
                .withCastTimeModifier((this.getCastTimeModifier() + other.getCastTimeModifier()) / 2.0f)
                .withEffectivenessModifier((this.getEffectivenessModifier() + other.getEffectivenessModifier()) / 2.0f)
                .withCostModifier((this.getCostModifier() + other.getCostModifier()) / 2.0f)
                .withSpecialType(this.getSpecialTypes()).withSpecialType(other.getSpecialTypes());

        return r;
    }
}
