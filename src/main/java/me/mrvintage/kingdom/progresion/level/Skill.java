package me.mrvintage.kingdom.progresion.level;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public interface Skill {

    void onLevelUp(ServerPlayerEntity player, int level);

    double getExpReward(LevelAction action, int level);

    double[] getProgression();

    Identifier getIdentifier();

    String getDisplayName();

    default Text levelUpText(int level) {
        return Text.of(this.getDisplayName() + " is now level " + level + "!");
    }
}
