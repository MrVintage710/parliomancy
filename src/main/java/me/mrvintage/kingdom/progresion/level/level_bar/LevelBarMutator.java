package me.mrvintage.kingdom.progresion.level.level_bar;

import net.minecraft.entity.boss.CommandBossBar;

import java.util.function.Supplier;

public interface LevelBarMutator {

    void mutateLevelBar(final CommandBossBar bar, Supplier<Double> priorityFunc);
}
