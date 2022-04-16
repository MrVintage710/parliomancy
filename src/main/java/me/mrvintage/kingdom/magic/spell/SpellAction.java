package me.mrvintage.kingdom.magic.spell;

import me.mrvintage.kingdom.magic.parser.preposition.SpellPreposition;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.Optional;

public class SpellAction implements SpellExecutable {

    private SpellEffect action;
    private Optional<TargetProvider> group;

    private ArrayList<SpellPreposition> prepositions = new ArrayList<>();
    private ArrayList<TargetProvider> prepositionTargets = new ArrayList<>();

    public SpellAction(SpellEffect action, Optional<TargetProvider> group) {
        this.action = action;
        this.group = group;
    }

    public void add(SpellPreposition preposition, TargetProvider provider) {
        prepositions.add(preposition);
        prepositionTargets.add(provider);
    }

    @Override
    public boolean executeSpell(SpellTarget source, ServerWorld world) {
        var targets = group.orElse(source.toProvider()).getTargets(source, world);

        var p = new SpellPrepositionList();
        for(int i = 0; i < prepositions.size(); i++) {
            p.addEntry(
                    prepositions.get(i),
                    prepositionTargets.get(i).toSpellTargetList(source, world)
            );
        }

        if(p.size() == 0) return false;

        for(SpellTarget target : targets) {
            if(!action.execute(target, source, p, Optional.empty())) return false;
        }

        return true;
    }
}
