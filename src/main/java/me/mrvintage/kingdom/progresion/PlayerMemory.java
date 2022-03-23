package me.mrvintage.kingdom.progresion;

import me.mrvintage.kingdom.progresion.level.LevelAction;
import me.mrvintage.kingdom.progresion.level.SkillInstance;
import me.mrvintage.kingdom.progresion.level.level_bar.LevelBarHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class PlayerMemory {

    private ServerPlayerEntity player;
    private HashMap<Identifier, SkillInstance<?>> skills = new HashMap<>();
    private LevelBarHandler levelbar;

    public PlayerMemory(ServerPlayerEntity player) {
        this.player = player;
        levelbar = new LevelBarHandler(player, new Identifier("levelbar", player.getUuidAsString()));
    }

    public boolean hasSkill(Identifier skill) {
        return skills.containsKey(skill);
    }

    public boolean meetsSkillRequirement(Identifier skill, int level) {
        return hasSkill(skill) && skills.get(skill).getLevel() >= level;
    }

    public void addSkill(SkillInstance<?> skill) {
        skills.put(skill.getIdentifier(), skill);
    }

    public void levelSkill(LevelAction action, int amount) {
        for(SkillInstance<?> skill : skills.values()) {
            skill.gainExpReward(player, action, levelbar, amount);
        }
    }

    public void addExpToSkill(Identifier skill, int amount) {
        skills.get(skill).gainExp(player, levelbar, amount);
    }
}
