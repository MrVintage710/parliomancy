package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;

import java.util.List;

public interface TargetFilter {

    List<SpellTarget> filterTargets(List<SpellTarget> targets, ServerWorld world);
}
