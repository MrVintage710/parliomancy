package me.mrvintage.kingdom.magic.parser.noun;

import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.TargetProvider;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public enum SpellNoun implements TargetProvider {
    SELF(new SelfNounProvider(), "me", "myself", "i"),
    SOULHOME(new SoulhomeNounProvider(), "soulhome");

    private TargetProvider provider;
    private String[] matches;

    SpellNoun(TargetProvider provider, String... matches) {
        this.provider = provider;
        this.matches = matches;
    }

    @Override
    public List<SpellTarget> getTargets(SpellTarget source, ServerWorld serverWorld) {
        return provider.getTargets(source, serverWorld);
    }

    public String[] getMatches() {
        return matches;
    }
}
