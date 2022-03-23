package me.mrvintage.kingdom.progresion.level.level_bar;

import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LevelBarHandler {
    private CommandBossBar levelbar;
    private Queue<Runnable> actionQueue = new ConcurrentLinkedQueue<>();
    private boolean running = false;

    public LevelBarHandler(ServerPlayerEntity playerEntity, Identifier identifier) {
        levelbar = playerEntity.server.getBossBarManager().add(identifier, Text.of(""));
        levelbar.setVisible(false);
        levelbar.addPlayer(playerEntity);
    }

    public void mutate(final LevelBarMutator mutator) {
        Runnable runnable = () -> {
            mutator.mutateLevelBar(levelbar, this::calcPriority);
            this.runNext();
        };
        actionQueue.add(runnable);
        if(!running) runNext();
    }

    public double calcPriority() {
        double count = actionQueue.size() + 1;
        return 1 / count;
    }

    public void runNext() {
        if(!actionQueue.isEmpty()) {
            running = true;
            Util.getMainWorkerExecutor().submit(actionQueue.remove());
        } else {
            running = false;
        }
    }
}
