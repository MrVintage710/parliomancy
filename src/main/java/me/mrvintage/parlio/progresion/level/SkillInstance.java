package me.mrvintage.parlio.progresion.level;

import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class SkillInstance<T extends Skill> {

    private int level = 0;
    private double exp = 0;
    private T skill;

    public SkillInstance(T skill) {
        this.skill = skill;
    }

    public void gainExpReward(ServerPlayerEntity player, LevelAction action, CommandBossBar levelBar, int amountCrafted) {
        this.gainExp(player, levelBar, skill.getExpReward(action, level) * amountCrafted);
    }

    public void gainExp(ServerPlayerEntity player, CommandBossBar levelBar, double expReward) {
        showExpGainVisual(levelBar, expReward, level, exp);

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

    private void showExpGainVisual(CommandBossBar levelBar, double expReward, int startLevel, double startExp) {
        Util.getMainWorkerExecutor().submit(() -> {
            double reward = expReward;
            double currentExp = startExp;
            int currentLevel = startLevel;

            while (reward > 0) {
                double currentMilestone = getMilestone(currentLevel);
                double lastMilestone = currentLevel != 0 ? getMilestone(currentLevel - 1) : 0;
                double max = currentMilestone - lastMilestone;
                double value = currentExp - lastMilestone;

                levelBar.setName(Text.of(skill.getDisplayName() + " " + currentLevel + " (" + value + "/" + max + ")"));
                levelBar.setMaxValue((int) max);
                levelBar.setValue((int) value);
                levelBar.setVisible(true);

                wait(1000);

                if(max <= reward) {
                    reward -= max;
                    currentExp += max;
                    levelBar.setValue((int) max);
                    levelBar.setName(Text.of(skill.getDisplayName() + " " + currentLevel + " (" + max + "/" + max + ")"));
                } else {
                    double target = value + reward;
                    levelBar.setValue((int) target);
                    levelBar.setName(Text.of(skill.getDisplayName() + " " + currentLevel + " (" + target + "/" + max + ")"));
                    reward = 0;
                }

                currentLevel++;
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

    public double getMilestone(int level) {
        if(level >= getMaxLevel() && level < 0) return 0;
        return skill.getProgression()[level];
    }

    public double getNextMilestone() {
        if(getMaxLevel() == level) return 0;
        return skill.getProgression()[level];
    }

    public double getLastMilestone() {
        if(level == 0) return 0;
        return skill.getProgression()[level-1];
    }

    public Identifier getIdentifier() {
        return skill.getIdentifier();
    }

    public int getMaxLevel() {
        return skill.getProgression().length;
    }

    public double getMaxExp() {
        return skill.getProgression()[getMaxLevel() - 1];
    }
}
