package net.fabricmc.example;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//Commands for the mod
		CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {

			LiteralCommandNode<ServerCommandSource> mainNode = CommandManager
					.literal("parlio")
					.build();

			LiteralCommandNode<ServerCommandSource> castNode = CommandManager
					.literal("cast")
					.build();

			LiteralCommandNode<ServerCommandSource> lineNode = CommandManager
					.literal("line")
					.build();

			LiteralCommandNode<ServerCommandSource> coneNode = CommandManager
					.literal("cone")
					.build();

			dispatcher.getRoot().addChild(mainNode);
			mainNode.addChild(castNode);
			castNode.addChild(lineNode);
			castNode.addChild(coneNode);
		}));

		LOGGER.info("Hello Fabric world!");
	}
}
