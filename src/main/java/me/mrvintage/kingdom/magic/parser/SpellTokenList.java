package me.mrvintage.kingdom.magic.parser;

import java.util.ArrayList;

public class SpellTokenList {

    private ArrayList<SpellToken> spellTokens = new ArrayList<>();
    private int current = 0;

    public void add(SpellToken token) {
        this.spellTokens.add(token);
    }

    public SpellToken pop() {
        return getIndex(current++);
    }

    public SpellToken peek() {
        return getIndex(current);
    }

    public boolean peekType(SpellTokenType type) {
        if(isAtEnd()) return false;
        return getIndex(current).getTokenType() == type;
    }

    private SpellToken getIndex(int index) {
        if(index >= spellTokens.size()) return spellTokens.get(spellTokens.size() - 1);
        return spellTokens.get(index);
    }

    public boolean isAtEnd() {
        return current >= spellTokens.size();
    }
}
