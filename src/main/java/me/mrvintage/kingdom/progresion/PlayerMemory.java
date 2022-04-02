package me.mrvintage.kingdom.progresion;

import me.mrvintage.kingdom.KingdomMod;
import me.mrvintage.kingdom.progresion.level.LevelAction;
import me.mrvintage.kingdom.progresion.level.Skill;
import me.mrvintage.kingdom.progresion.level.SkillInstance;
import me.mrvintage.kingdom.progresion.level.level_bar.LevelBarHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import org.lwjgl.system.CallbackI;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;
import xyz.nucleoid.fantasy.util.VoidChunkGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

public class PlayerMemory {

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
                .setGameRule(GameRules.DO_TRADER_SPAWNING, false)
                .setGameRule(GameRules.RANDOM_TICK_SPEED, 0)
                .setGenerator(new VoidChunkGenerator(server.getRegistryManager().get(Registry.BIOME_KEY)))
                .setDimensionType(DimensionType.OVERWORLD_CAVES_REGISTRY_KEY);

        worldHandle = Optional.of(fantasy.openTemporaryWorld(worldConfig));
        regenSoulhomeLanding();
        //worldHandle = Optional.of(fantasy.getOrOpenPersistentWorld(this.getSoulWorldID(), worldConfig));
        //player.teleport(worldHandle.get().asWorld(), 0, 0, 0, 0, 0);
    }

    public void regenSoulhomeLanding() {
        if(worldHandle.isPresent()) {
            RuntimeWorldHandle rwh = worldHandle.get();
            rwh.asWorld().setBlockState(new BlockPos(0, 120, 0), Blocks.BEDROCK.getDefaultState());
            rwh.asWorld().setBlockState(new BlockPos(1, 120, 0), Blocks.BEDROCK.getDefaultState());
            rwh.asWorld().setBlockState(new BlockPos(-1, 120, 0), Blocks.BEDROCK.getDefaultState());
            rwh.asWorld().setBlockState(new BlockPos(0, 120, 1), Blocks.BEDROCK.getDefaultState());
            rwh.asWorld().setBlockState(new BlockPos(0, 120, -1), Blocks.BEDROCK.getDefaultState());
        }
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
