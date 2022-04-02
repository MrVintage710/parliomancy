package me.mrvintage.kingdom.magic.parser;

import java.util.ArrayList;

public class SpellTokenList {

    private ArrayList<SpellToken> spellTokens = new ArrayList<>();
    private int current = 0;

    public void add(SpellToken token) {
        this.spellTokens.add(token);
    }

    public SpellToken pop() {
        return spellTokens.get(current++);
    }

    public SpellToken peek() {
        return spellTokens.get(current);
    }

    public boolean peekType(SpellTokenType type) {
        if(isAtEnd()) return false;
        return spellTokens.get(current).getTokenType() == type;
    }

    public boolean isAtEnd() {
        return current >= spellTokens.size();
    }
}
