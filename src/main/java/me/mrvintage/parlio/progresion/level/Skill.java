package me.mrvintage.parlio.progresion.level;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public interface Skill {

    void onLevelUp(ServerPlayerEntity player, int level);

    double getExpReward(LevelAction action, int level);

    double[] getProgression();

    Identifier getIdentifier();

    String getDisplayName();
}
