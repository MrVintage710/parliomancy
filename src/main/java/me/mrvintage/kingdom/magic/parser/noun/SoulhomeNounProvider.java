package me.mrvintage.kingdom.magic.parser.noun;

import me.mrvintage.kingdom.magic.spell.SpellPositionTarget;
import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.SpellTargetType;
import me.mrvintage.kingdom.magic.spell.TargetProvider;
import me.mrvintage.kingdom.progresion.PlayerMemory;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class SoulhomeNounProvider implements TargetProvider {
    @Override
    public List<SpellTarget> getTargets(SpellTarget source, ServerWorld serverWorld) {
        ArrayList<SpellTarget> targets = new ArrayList<>();

        if(source.getType() == SpellTargetType.ENTITY && source.getTarget() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) source.getTarget();
            PlayerMemory memory = ProgressionManager.getMemory(player);

            targets.add(new SpellTarget(new SpellPositionTarget(memory.getSoulhome(), new Vec3d(0, 121, 0)), SpellTargetType.POSITION));
        }

        return targets;
    }
}
