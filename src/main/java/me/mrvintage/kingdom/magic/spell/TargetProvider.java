package me.mrvintage.kingdom.magic.spell;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;

public interface TargetProvider {

    static TargetProvider createEntityTargetProvider(Entity entity) {
        return sources -> {
            return Arrays.asList(new SpellTarget<Entity>(entity, SpellTargetType.ENTITY));
        };
    }

    List<SpellTarget> getTargets(TargetProvider sources);
}
