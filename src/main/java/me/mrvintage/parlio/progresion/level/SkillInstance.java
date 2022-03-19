package me.mrvintage.parlio.progresion.level;

import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.ServerTask;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.thread.TaskExecutor;

public class SkillInstance<T extends Skill> {

    private int level = 0;
    private int exp = 0;
    private T skill;

    public SkillInstance(T skill) {
        this.skill = skill;
    }

    public void gainExpReward(ServerPlayerEntity player, LevelAction action, CommandBossBar levelBar) {
        this.gainExp(player, skill.getExpReward(action), levelBar);
    }

    public void gainExp(ServerPlayerEntity player, int amount, CommandBossBar levelBar) {
        levelBar.setValue(exp - getLastMilestone());
        levelBar.setMaxValue(getNextMilestone() - getLastMilestone());
        levelBar.setName(Text.of(skill.getDisplayName() + " | " + level));
        levelBar.setVisible(true);

        exp += amount;

        Util.getMainWorkerExecutor().submit(() -> {
            try {
                Thread.sleep(2000);
                levelBar.setValue(exp);
                Thread.sleep(2000);
                levelBar.setVisible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        while (exp >= getNextMilestone()) {
            level += 1;
            skill.onLevelUp(player, level);
            player.sendMessage(Text.of("You are now level " + level + " in " + skill.getDisplayName() + "."), false);
        }
    }

    public int getNextMilestone() {
        return skill.getProgression()[level];
    }

    public int getLastMilestone() {
        if(level == 0) return 0;
        return skill.getProgression()[level-1];
    }

    public Identifier getIdentifier() {
        return skill.getIdentifier();
    }
}
