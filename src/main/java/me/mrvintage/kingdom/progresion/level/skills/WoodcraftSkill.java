package me.mrvintage.kingdom.progresion.level.skills;

import me.mrvintage.kingdom.KingdomMod;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import me.mrvintage.kingdom.progresion.level.LevelAction;
import me.mrvintage.kingdom.progresion.level.Skill;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WoodcraftSkill implements Skill {
    @Override
    public void onLevelUp(ServerPlayerEntity player, int level) {
        switch (level) {
            case 1 -> levelOneRewards(player);
            case 2 -> levelTwoRewards(player);
        }
    }

    @Override
    public double getExpReward(LevelAction action, int level) {
        switch (action) {
            case CRAFT_SIMPLE_WOOD_ITEM -> {return level >= 3 ? 0 : 0.25;}
            case CRAFT_WOODEN_PLANK_ITEM -> {return level >= 6 ? 0 : 1;}
            case PLACE_WOODEN_PLANKS -> {return level >= 6 ? 0 : 1;}
            case BREAK_LOG -> {return level >= 6 ? 0 : 1;}
        }
        return 0;
    }

    @Override
    public double[] getProgression() {
        return new double[]{4, 14, 64, 164, 314, 514, 764, 1064};
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(KingdomMod.MODID, "woodcraft");
    }

    @Override
    public String getDisplayName() {
        return "Woodcraft";
    }

    private void levelOneRewards(ServerPlayerEntity player) {
        player.sendMessage(this.levelUpText(1), false);
        player.sendMessage(Text.of("Now you can craft and place overworld planks!"), false);

        ProgressionManager.awardRecipe(player,
                new Identifier("minecraft:acacia_planks"),
                new Identifier("minecraft:spruce_planks"),
                new Identifier("minecraft:birch_planks"),
                new Identifier("minecraft:oak_planks"),
                new Identifier("minecraft:dark_oak_planks"),
                new Identifier("minecraft:jungle_planks")
        );
    }

    private void levelTwoRewards(ServerPlayerEntity player) {
        player.sendMessage(this.levelUpText(2), false);
        player.sendMessage(Text.of("Now you can craft wooden tools!"), false);

        ProgressionManager.awardRecipe(player,
                new Identifier("minecraft:wooden_axe"),
                new Identifier("minecraft:wooden_hoe"),
                new Identifier("minecraft:wooden_pickaxe"),
                new Identifier("minecraft:wooden_shovel"),
                new Identifier("minecraft:wooden_sword")
        );
    }
}
