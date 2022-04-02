package me.mrvintage.kingdom.magic.spell;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface SpellAction {

    int getCost();

    void onCast(ServerPlayerEntity source, ParticleManager manager);
}
