package me.mrvintage.parlio.progresion.level;

import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class SkillInstance<T extends Skill> {

    private int level = 0;
    private int exp = 0;
    private T skill;

    public SkillInstance(T skill) {
        this.skill = skill;
    }

    public void gainExpReward(ServerPlayerEntity player, LevelAction action, CommandBossBar levelBar, int amountCrafted) {
        this.gainExp(player, levelBar, skill.getExpReward(action, level) * amountCrafted);
    }

    public void gainExp(ServerPlayerEntity player, CommandBossBar levelBar, int expReward) {

//        levelBar.setValue(exp - getLastMilestone());
//        levelBar.setMaxValue(getNextMilestone() - getLastMilestone());
//        levelBar.setName(Text.of(skill.getDisplayName() + " | " + level));
//        levelBar.setVisible(true);

        int adjustedEXP  = exp + expReward;
        adjustedEXP = adjustedEXP > getMaxExp() ? getMaxExp() : adjustedEXP;
        int adjustedLevel = level;
        while (adjustedEXP >= getNextMilestone() && getMaxLevel() != level) {
            adjustedLevel += 1;
            skill.onLevelUp(player, adjustedLevel);
            player.sendMessage(Text.of("You are now level " + level + " in " + skill.getDisplayName() + "."), false);
        }

        showExpGainVisual(levelBar, level, adjustedLevel, exp, adjustedEXP);

        exp = adjustedEXP;
        level = adjustedLevel;


//        Util.getMainWorkerExecutor().submit(() -> {
//            try {
//                Thread.sleep(2000);
//                levelBar.setValue(exp);
//                Thread.sleep(2000);
//                levelBar.setVisible(false);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }

    private void showExpGainVisual(CommandBossBar levelBar, int startingLevel, int endingLevel, int startingExp, int endingExp) {
        Util.getMainWorkerExecutor().submit(() -> {
            for(int i = startingLevel; i < endingLevel; i++) {
                int value = startingExp - getLastMilestone();
                int max = i != 0 ? getMilestone(i) - getMilestone(i - 1) : getMilestone(0);

                if(endingExp > )

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getMilestone(int level) {
        if(level >= getMaxLevel() && level < 0) return 0;
        return skill.getProgression()[level];
    }

    public int getNextMilestone() {
        if(getMaxLevel() == level) return 0;
        return skill.getProgression()[level];
    }

    public int getLastMilestone() {
        if(level == 0) return 0;
        return skill.getProgression()[level-1];
    }

    public Identifier getIdentifier() {
        return skill.getIdentifier();
    }

    public int getMaxLevel() {
        return skill.getProgression().length;
    }

    public int getMaxExp() {
        return skill.getProgression()[getMaxLevel() - 1];
    }
}
