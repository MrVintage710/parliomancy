package me.mrvintage.kingdom.magic.spell;

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

    public boolean is(SpellTargetType type) {
        return this.type == type;
    }
}
