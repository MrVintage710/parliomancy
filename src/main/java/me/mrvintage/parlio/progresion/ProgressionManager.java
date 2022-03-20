package me.mrvintage.parlio.progresion;

import me.mrvintage.parlio.progresion.level.LevelAction;
import me.mrvintage.parlio.progresion.level.SkillInstance;
import me.mrvintage.parlio.progresion.level.skills.WoodcraftSkill;
import net.minecraft.network.packet.s2c.play.UnlockRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeBookOptions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.*;

public class ProgressionManager {

    private static HashMap<UUID, PlayerMemory> player_memories = new HashMap<>();

    public static boolean hasBeenInitialized(ServerPlayerEntity player) {
        return player_memories.containsKey(player.getUuid());
    }

    public static void initPlayer(ServerPlayerEntity player) {
        if(!hasBeenInitialized(player)) {
            PlayerMemory mem = new PlayerMemory(player);
            mem.addSkill(new SkillInstance<>(new WoodcraftSkill()));
            player_memories.put(player.getUuid(), mem);
            System.out.println("Made Memory!");
        }
    }

    public static void removePlayerMemory(ServerPlayerEntity player) {
        player_memories.remove(player.getUuid());
    }

    public static void envokeLevelAction(ServerPlayerEntity player, LevelAction action, int amount) {
        player_memories.get(player.getUuid()).levelSkill(action, amount);
    }

    public static void awardRecipeNoPacket(ServerPlayerEntity player, Identifier... identifiers) {
        ProgressionManager.awardRecipeNoPacket(player, Arrays.asList(identifiers));
    }

    public static void awardRecipeNoPacket(ServerPlayerEntity player, List<Identifier> recipes) {
        for(Identifier id : recipes) {
            var optionalRecipe = player.getServer().getRecipeManager().get(id);
            if(optionalRecipe.isPresent()) {
                var recipe = optionalRecipe.get();
                player.getRecipeBook().add(recipe);
            }
        }
    }

    public static void awardRecipe(ServerPlayerEntity player, List<Identifier> recipes) {
        ArrayList<Identifier> list = new ArrayList<>();

        for(Identifier id : recipes) {
            var optionalRecipe = player.getServer().getRecipeManager().get(id);
            if(optionalRecipe.isPresent()) {
                var recipe = optionalRecipe.get();
                player.getRecipeBook().add(recipe);
                list.add(id);
            }
        }

        player.networkHandler.sendPacket(new UnlockRecipesS2CPacket(
                UnlockRecipesS2CPacket.Action.ADD,
                list,
                Collections.emptyList(),
                new RecipeBookOptions()
        ));
    }

    public static void awardRecipe(ServerPlayerEntity player, Identifier... identifiers) {
        ProgressionManager.awardRecipe(player, Arrays.asList(identifiers));
    }

    public static PlayerMemory getMemory(ServerPlayerEntity player) {
        return player_memories.get(player.getUuid());
    }
}