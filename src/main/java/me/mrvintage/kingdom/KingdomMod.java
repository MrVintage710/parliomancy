package me.mrvintage.kingdom;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import me.mrvintage.kingdom.event.OnPlayerChatCallback;
import me.mrvintage.kingdom.event.OnSpellcastAttemptCallback;
import me.mrvintage.kingdom.event.OnPlayerConnectCallback;
import me.mrvintage.kingdom.lang.LanguageManager;
import me.mrvintage.kingdom.magic.MagicManager;
import me.mrvintage.kingdom.magic.parser.SpellParser;
import me.mrvintage.kingdom.progresion.PlayerMemory;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KingdomMod implements ModInitializer {

    public static final String MODID = "kingdom";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        initCommands();
        MagicManager.init();
        SpellParser.init();
        OnPlayerConnectCallback.EVENT.register(ProgressionManager::onPlayerConnect);
        OnPlayerChatCallback.EVENT.register(LanguageManager::onPlayerChat);

//        OnPlayerChatCompleteCallback.EVENT.register(((player, languageMap, message) -> {
//            System.out.println(message);
//        }));
    }

    private void initCommands() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {

            LiteralCommandNode<ServerCommandSource> mainNode = CommandManager
                    .literal("kingdom")
                    .build();

            LiteralCommandNode<ServerCommandSource> memory = CommandManager
                    .literal("memory")
                    .build();

            LiteralCommandNode<ServerCommandSource> travelToSoulhome = CommandManager
                    .literal("tavel_to_soulhome")
                    .executes(context -> {
                        ServerPlayerEntity player = context.getSource().getPlayer();
                        PlayerMemory mem = ProgressionManager.getMemory(player);
                        player.teleport(mem.getSoulhome(), 0, 121, 0, 0, 0);
                        return 0;
                    })
                    .build();

            LiteralCommandNode<ServerCommandSource> award_recipe = CommandManager
                    .literal("award_recipe")
                    .build();

            ArgumentCommandNode<ServerCommandSource, Identifier> recipe = CommandManager
                    .argument("recipe", IdentifierArgumentType.identifier())
                    .executes(new Command<ServerCommandSource>() {
                        @Override
                        public int run(CommandContext<ServerCommandSource> commandContext) throws CommandSyntaxException {
                            ProgressionManager.awardRecipe(commandContext.getSource().getPlayer(), commandContext.getArgument("recipe", Identifier.class));
                            return 0;
                        }
                    })
                    .build();

            LiteralCommandNode<ServerCommandSource> remove_memory = CommandManager
                    .literal("remove")
                    .executes((ctx) -> {
                        ProgressionManager.removePlayerMemory(ctx.getSource().getPlayer());
                        return 0;
                    })
                    .build();

            LiteralCommandNode<ServerCommandSource> skill = CommandManager
                    .literal("skill")
                    .build();

            ArgumentCommandNode<ServerCommandSource, String> skill_name = CommandManager
                    .argument("skill_name", StringArgumentType.string())
                    .build();

            LiteralCommandNode<ServerCommandSource> skill_add = CommandManager
                    .literal("add")
                    .build();

            ArgumentCommandNode<ServerCommandSource, Integer> skill_amount = CommandManager
                    .argument("amount", IntegerArgumentType.integer(0))
                    .executes(ctx -> {
                        ServerPlayerEntity player = ctx.getSource().getPlayer();
                        PlayerMemory mem = ProgressionManager.getMemory(player);
                        mem.addExpToSkill(new Identifier(KingdomMod.MODID, ctx.getArgument("skill_name", String.class)), ctx.getArgument("amount", Integer.class));
                        return 0;
                    })
                    .build();

            dispatcher.getRoot().addChild(mainNode);

            mainNode.addChild(award_recipe);
            mainNode.addChild(memory);

            memory.addChild(remove_memory);
            memory.addChild(skill);
            memory.addChild(travelToSoulhome);

            skill.addChild(skill_name);

            skill_name.addChild(skill_add);
            skill_add.addChild(skill_amount);

            award_recipe.addChild(recipe);
        }));
    }

    private void attackBlockCallback() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            BlockState state = world.getBlockState(pos);
            return ActionResult.FAIL;
        });
    }

    private void onJoinCallback() {

    }
}
