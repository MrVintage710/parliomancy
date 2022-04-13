package me.mrvintage.kingdom.magic.parser.adjective;

import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.SpellTargetType;
import me.mrvintage.kingdom.magic.spell.TargetFilter;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.stream.Collectors;

public class StoneyAdjectiveFilter implements TargetFilter {
    @Override
    public List<SpellTarget> filterTargets(List<SpellTarget> targets, ServerWorld world) {
        return targets.stream().filter(spellTarget -> {
            if (spellTarget.getType() == SpellTargetType.POSITION) {
                var bs = world.getBlockState((BlockPos) spellTarget.getTarget());
                if(bs.isIn(BlockTags.STONE_BRICKS) || bs.isIn(BlockTags.BASE_STONE_OVERWORLD) || bs.isIn(BlockTags.BASE_STONE_NETHER)) {
                    return true;
                }
            }

            return false;
        }).collect(Collectors.toList());
    }
}
