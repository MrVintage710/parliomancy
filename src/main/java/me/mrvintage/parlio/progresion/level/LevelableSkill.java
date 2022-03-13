package me.mrvintage.parlio.progresion.level;

import net.minecraft.server.network.ServerPlayerEntity;

public interface LevelableSkill {

    void rewardExp(ServerPlayerEntity entity, LevelAction action);

    void levelUp(ServerPlayerEntity entity, int level);

    int[] getProgression();
}
