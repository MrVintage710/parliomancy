package me.mrvintage.parlio.progresion.level;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public interface Skill {

    void onLevelUp(ServerPlayerEntity player, int level);

    int getExpReward(LevelAction action);

    int[] getProgression();

    Identifier getIdentifier();

    String getDisplayName();
}
