package me.mrvintage.kingdom.magic.spell;

public class SpellTargetList {

    private SpellTarget[] targets;

    public SpellTargetList(SpellTarget... targets) {
        this.targets = targets;
    }

    public boolean isAllType(SpellTargetType type) {
        for(SpellTarget target : targets) {
            if(target.getType() != type) return false;
        }

        return true;
    }

    public boolean containsType(SpellTargetType type) {
        for(SpellTarget target : targets) {
            if(target.getType() == type) return true;
        }

        return false;
    }

    public SpellTarget first() {
        if(targets.length == 0) return null;
        return targets[0];
    }
}
