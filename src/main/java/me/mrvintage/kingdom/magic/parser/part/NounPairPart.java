package me.mrvintage.kingdom.magic.parser.part;

import me.mrvintage.kingdom.magic.parser.adjective.SpellAdjective;
import me.mrvintage.kingdom.magic.parser.noun.SpellNoun;
import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.TargetProvider;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NounPairPart implements TargetProvider {

    private SpellNoun noun;
    private Optional<SpellAdjective> adjective = Optional.empty();

    public NounPairPart(SpellNoun noun) {
        this.noun = noun;
    }

    public NounPairPart setAdjective(SpellAdjective adjective) {
        this.adjective = Optional.of(adjective);
        return this;
    }

    @Override
    public List<SpellTarget> getTargets(SpellTarget source, ServerWorld world) {
        var targets = noun.getTargets(source, world);

        if(adjective.isPresent()) {
            return adjective.get().filterTargets(targets, world);
        }

        return targets;
    }
}
