package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpellGroup implements TargetProvider {

    private ArrayList<TargetProvider> targets = new ArrayList<>();

    public void add(TargetProvider provider) {
        targets.add(provider);
    }

    @Override
    public List<SpellTarget> getTargets(SpellTarget source, ServerWorld serverWorld) {
        List<SpellTarget> t = new ArrayList<>();

        for(TargetProvider provider : targets) {
            t = Stream.concat(t.stream(), provider.getTargets(source, serverWorld).stream()).collect(Collectors.toList());
        }

        return t;
    }
}
