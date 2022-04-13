package me.mrvintage.kingdom.magic.spell;

import me.mrvintage.kingdom.magic.parser.preposition.SpellPreposition;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class SpellPrepositionList {

    private ArrayList<SpellPreposition> prepositions = new ArrayList<>();
    private ArrayList<SpellTargetList> groups = new ArrayList<SpellTargetList>();

    public void addEntry(SpellPreposition preposition, SpellTargetList spellGroup) {
        prepositions.add(preposition);
        groups.add(spellGroup);
    }

    public void addEntry(SpellPreposition preposition, SpellTarget... targets) {
        prepositions.add(preposition);
        groups.add(new SpellTargetList(targets));
    }

    public int size() {
        return prepositions.size();
    }

    public boolean checkIndex(int index, SpellPreposition preposition, SpellTargetType type) {
        if(index < prepositions.size() && index < groups.size()) {
            return prepositions.get(index) == preposition && groups.get(index).isAllType(type);
        }

        return false;
    }

    public SpellTargetList getGroup(int index) {
        return groups.get(MathHelper.clamp(index, 0, groups.size() - 1));
    }
}
