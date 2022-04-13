package me.mrvintage.kingdom.magic.spell;

import me.mrvintage.kingdom.magic.parser.preposition.SpellPreposition;

import java.util.ArrayList;

public class SpellAction {

    private SpellEffect action;
    private TargetProvider group;

    private ArrayList<SpellPreposition> prepositions = new ArrayList<>();
    private ArrayList<TargetProvider> prepositionTargets = new ArrayList<>();

    public SpellAction(SpellEffect action, TargetProvider group) {
        this.action = action;
        this.group = group;
    }

    public void add(SpellPreposition preposition, TargetProvider provider) {
        prepositions.add(preposition);
        prepositionTargets.add(provider);
    }
}
