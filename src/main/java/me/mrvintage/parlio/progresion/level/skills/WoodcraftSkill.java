package me.mrvintage.parlio.progresion.level.skills;

import me.mrvintage.parlio.ParlioMod;
import me.mrvintage.parlio.progresion.ProgressionManager;
import me.mrvintage.parlio.progresion.level.LevelAction;
import me.mrvintage.parlio.progresion.level.Skill;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class WoodcraftSkill implements Skill {
    @Override
    public void onLevelUp(ServerPlayerEntity player, int level) {
        switch (level) {
            case 1 -> {
                ProgressionManager.awardRecipe(player, new Identifier("minecraft:acacia_stairs"));
            }
        }
    }

    @Override
    public int getExpReward(LevelAction action, int level) {
        switch (action) {
            case CRAFT_STICK -> {return 1;}
        }
        return 0;
    }

    @Override
    public int[] getProgression() {
        return new int[]{3, 10, 100, 250};
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(ParlioMod.MODID, "woodcraft");
    }

    @Override
    public String getDisplayName() {
        return "Woodcraft";
    }
}