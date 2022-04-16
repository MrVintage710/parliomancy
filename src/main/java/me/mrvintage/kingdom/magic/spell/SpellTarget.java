package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;

import java.util.Arrays;
import java.util.List;

public class SpellTarget<T> {

    private T target;
    private SpellTargetType type;

    public SpellTarget(T target, SpellTargetType type) {
        this.target = target;
        this.type = type;
    }

    public T getTarget() {
        return target;
    }

    public SpellTargetType getType() {
        return type;
    }

    public TargetProvider toProvider() {
        return (source, serverWorld) -> Arrays.asList(this);
    }

    public boolean is(SpellTargetType type) {
        return this.type == type;
    }
}
