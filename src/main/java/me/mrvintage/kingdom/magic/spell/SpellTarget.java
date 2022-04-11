package me.mrvintage.kingdom.magic.spell;

public class SpellTarget<T> {

    private T target;
    private SpellTargetType type;

    public SpellTarget(T target, SpellTargetType type) {
        this.target = target;
        this.type = type;
    }
}
