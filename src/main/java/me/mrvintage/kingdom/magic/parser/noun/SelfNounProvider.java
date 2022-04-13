package me.mrvintage.kingdom.magic.parser.noun;

import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.SpellTargetType;
import me.mrvintage.kingdom.magic.spell.TargetProvider;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class SelfNounProvider implements TargetProvider {
    @Override
    public List<SpellTarget> getTargets(SpellTarget source, ServerWorld serverWorld) {
        ArrayList<SpellTarget> targets = new ArrayList<>();

        if(source.getType() == SpellTargetType.ENTITY) {
            targets.add(source);
        }

        return targets;
    }
}
