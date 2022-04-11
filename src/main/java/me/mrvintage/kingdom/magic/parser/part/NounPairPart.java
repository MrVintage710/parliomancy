package me.mrvintage.kingdom.magic.parser.part;

import me.mrvintage.kingdom.magic.parser.SpellParser;
import me.mrvintage.kingdom.magic.parser.token.SpellToken;
import me.mrvintage.kingdom.magic.parser.token.SpellTokenType;
import me.mrvintage.kingdom.magic.spell.SpellTarget;
import me.mrvintage.kingdom.magic.spell.TargetProvider;

import java.util.List;
import java.util.Optional;

public class NounPairPart implements TargetProvider {

    private SpellToken noun;
    private Optional<SpellToken> adjective = Optional.empty();

    public NounPairPart(SpellToken noun) {
        this.noun = noun;
    }

    public void setAdjective(SpellToken adjective) {
        this.adjective = Optional.of(adjective);
    }

    @Override
    public List<SpellTarget> getTargets(TargetProvider sources) {
        return null;
    }
}
