package me.mrvintage.kingdom.progresion;

import me.mrvintage.kingdom.KingdomMod;
import me.mrvintage.kingdom.progresion.level.LevelAction;
import me.mrvintage.kingdom.progresion.level.Skill;
import me.mrvintage.kingdom.progresion.level.SkillInstance;
import me.mrvintage.kingdom.progresion.level.level_bar.LevelBarHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.dimension.DimensionType;
import org.lwjgl.system.CallbackI;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;

import java.util.HashMap;
import java.util.Optional;

public class PlayerMemory {

    private static final RuntimeWorldConfig WORLD_CONFIG = new RuntimeWorldConfig()
            .setDifficulty(Difficulty.EASY)
            .setGameRule(GameRules.DO_MOB_SPAWNING, false)
            .setDimensionType(DimensionType.OVERWORLD_REGISTRY_KEY);

    private ServerPlayerEntity player;
    private HashMap<Identifier, SkillInstance<?>> skills = new HashMap<>();
    private LevelBarHandler levelbar;
    private Optional<RuntimeWorldHandle> worldHandle = Optional.empty();

    public PlayerMemory(ServerPlayerEntity player) {
        this.player = player;
        levelbar = new LevelBarHandler(player, new Identifier("levelbar", player.getUuidAsString()));
    }

    public void onPlayerConnect(MinecraftServer server) {
        Fantasy fantasy = Fantasy.get(server);

        RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
                .setDifficulty(Difficulty.EASY)
                .setGameRule(GameRules.DO_MOB_SPAWNING, false)
                .setGenerator(server.getOverworld().getChunkManager().getChunkGenerator())
                .setDimensionType(DimensionType.OVERWORLD_REGISTRY_KEY);

        worldHandle = Optional.of(fantasy.getOrOpenPersistentWorld(this.getSoulWorldID(), worldConfig));
        //player.teleport(worldHandle.get().asWorld(), 0, 0, 0, 0, 0);
    }

    public ServerWorld getSoulhome() {
        return worldHandle.get().asWorld();
    }

    public boolean hasSkill(Identifier skill) {
        return skills.containsKey(skill);
    }

    public boolean meetsSkillRequirement(Identifier skill, int level) {
        return hasSkill(skill) && skills.get(skill).getLevel() >= level;
    }

    public void addSkill(Skill... skills) {
        for(Skill skill : skills) {
            this.skills.put(skill.getIdentifier(), new SkillInstance<>(skill));
        }
    }

    public void levelSkill(LevelAction action, int amount) {
        for(SkillInstance<?> skill : skills.values()) {
            skill.gainExpReward(player, action, levelbar, amount);
        }
    }

    public void addExpToSkill(Identifier skill, int amount) {
        skills.get(skill).gainExp(player, levelbar, amount);
    }

    public Identifier getSoulWorldID() {
        return new Identifier(KingdomMod.MODID, player.getUuidAsString());
    }
}
