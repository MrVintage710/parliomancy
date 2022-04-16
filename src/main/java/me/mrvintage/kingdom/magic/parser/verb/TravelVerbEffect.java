package me.mrvintage.kingdom.magic.parser.verb;

import me.mrvintage.kingdom.magic.spell.*;
import me.mrvintage.kingdom.progresion.ProgressionManager;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

import static me.mrvintage.kingdom.magic.parser.preposition.SpellPreposition.*;
import static me.mrvintage.kingdom.magic.spell.SpellTargetType.*;

public class TravelVerbEffect implements SpellEffect {
    @Override
    public int getCost(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        if(target.is(POSITION)) return -1;

        if(prepositionList.size() == 1) {
            if(prepositionList.checkIndex(0, TO, POSITION)) {
                var position = (SpellPositionTarget) prepositionList.getGroup(0).first().getTarget();
                if(target.is(ENTITY)) {
                    if(target.getTarget() instanceof ServerPlayerEntity) {
                        var player = (ServerPlayerEntity) target.getTarget();
                        var playerMemory = ProgressionManager.getMemory(player);

                        if(playerMemory.getSoulhome() == position.getWorld()) return 0;
                    }

                    var entity = (Entity) target.getTarget();
                    return (int) entity.getPos().distanceTo(position.getPos());
                }
                //Add other target types
            } else if(prepositionList.checkIndex(0, TO, ENTITY)) {
                var entityDestination = (Entity) prepositionList.getGroup(0).first().getTarget();
                if(target.is(ENTITY)) {
                    var entityAffected = (Entity) target.getTarget();

                    return (int) entityDestination.getPos().distanceTo(entityAffected.getPos());
                }
            }
        }

        return -1;
    }

    @Override
    public boolean execute(SpellTarget target, SpellTarget source, SpellPrepositionList prepositionList, Optional<SpellEffectModifier> modifier) {
        if(prepositionList.size() == 1) {
            if(prepositionList.checkIndex(0, TO, POSITION)) {
                var position = (SpellPositionTarget) prepositionList.getGroup(0).first().getTarget();
                if(target.is(ENTITY)) {
                    if(target.getTarget() instanceof ServerPlayerEntity) {
                        var player = (ServerPlayerEntity) target.getTarget();
                        player.teleport(
                                position.getWorld(),
                                position.getX(),
                                position.getY(),
                                position.getZ(),
                                player.getYaw(),
                                player.getPitch());
                    } else {
                        var entity = (Entity) target.getTarget();
                        if(entity.getWorld() != position.getWorld()) return false;
                        entity.teleport(position.getX(), position.getY(), position.getZ());
                    }
                }
            }
        }

        return true;
    }
}
