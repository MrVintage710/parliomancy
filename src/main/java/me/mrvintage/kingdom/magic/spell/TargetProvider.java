package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;

import java.util.List;

public interface TargetProvider {

    List<SpellTarget> getTargets(SpellTarget source, ServerWorld serverWorld);

    default SpellTargetList toSpellTargetList(SpellTarget source, ServerWorld world) {
        var targets = getTargets(source, world);
        return new SpellTargetList(targets.toArray(new SpellTarget[0]));
    }
}
