package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;

public interface SpellExecutable {

    boolean executeSpell(SpellTarget source, ServerWorld world);
}
