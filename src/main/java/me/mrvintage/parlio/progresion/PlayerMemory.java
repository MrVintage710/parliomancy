package me.mrvintage.parlio.progresion;

import me.mrvintage.parlio.progresion.level.LevelAction;
import me.mrvintage.parlio.progresion.level.SkillInstance;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class PlayerMemory {

    private ServerPlayerEntity player;
    private HashMap<Identifier, SkillInstance<?>> skills = new HashMap<>();
    private CommandBossBar levelbar;

    public PlayerMemory(ServerPlayerEntity player) {
        this.player = player;
        levelbar = player.server.getBossBarManager().add(new Identifier("levelbar", player.getUuidAsString()), Text.of(""));
        levelbar.setVisible(false);
        levelbar.addPlayer(player);
    }

    public void addSkill(SkillInstance<?> skill) {
        skills.put(skill.getIdentifier(), skill);
    }

    public void levelSkill(LevelAction action) {
        for(SkillInstance<?> skill : skills.values()) {
            skill.gainExpReward(player, action, levelbar);
        }
    }

    public void addExpToSkill(Identifier skill, int amount) {
        skills.get(skill).gainExp(player, amount, levelbar);
    }
}
