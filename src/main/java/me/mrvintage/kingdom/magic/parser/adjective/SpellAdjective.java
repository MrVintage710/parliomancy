package me.mrvintage.kingdom.magic.parser.adjective;

import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.SpellTargetType;
import me.mrvintage.kingdom.magic.spell.TargetFilter;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum SpellAdjective implements TargetFilter {
    ROCKY(new StoneyAdjectiveFilter(), "rocky");

    private TargetFilter filter;
    private String[] matches;

    SpellAdjective(TargetFilter filter, String... matches) {
        this.filter = filter;
        this.matches = matches;
    }

    public TargetFilter getFilter() {
        return filter;
    }

    public String[] getMatches() {
        return matches;
    }

    @Override
    public List<SpellTarget> filterTargets(List<SpellTarget> targets, ServerWorld world) {
        return filter.filterTargets(targets, world);
    }
}
