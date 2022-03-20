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
        showExpGainVisual(levelBar, expReward);

        exp = getMaxExp() > exp ? exp + expReward : getMaxExp();
        while (exp >= getNextMilestone() && getMaxLevel() != level) {
            level++;
            player.sendMessage(Text.of(skill.getDisplayName() + " is now level " + level + "."), false);
            skill.onLevelUp(player, level);
        }

        System.out.println("Gained EXP: " + expReward +
                "; Level: " + level +
                "; Progress: " + (exp - getLastMilestone()) + "/" + (getNextMilestone() - getLastMilestone())
        );
    }

    private void showExpGainVisual(CommandBossBar levelBar, int expReward) {
        Util.getMainWorkerExecutor().submit(() -> {
            int currentExp = expReward;
            int levelGain = 0;

            while (currentExp > 0) {
                int currentLevel = level + levelGain;
                int currentMilestone = getMilestone(currentLevel);
                int lastMilestone = currentLevel != 0 ? getMilestone(currentLevel - 1) : 0;
                int max = currentMilestone - lastMilestone;
                int value = exp - lastMilestone;

                levelBar.setName(Text.of(skill.getDisplayName() + " " + currentLevel + " (" + value + "/" + max + ")"));
                levelBar.setMaxValue(max);
                levelBar.setValue(value);
                levelBar.setVisible(true);

                wait(1000);

                if(max <= currentExp) {
                    levelBar.setValue(max);
                    currentExp -= max;
                } else {
                    int target = value + currentExp;
                    levelBar.setValue(target);
                    currentExp = 0;
                }

                levelGain++;
                wait(1000);
            }

            levelBar.setVisible(false);
        });
    }

    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
