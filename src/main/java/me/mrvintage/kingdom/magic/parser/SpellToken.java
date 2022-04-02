package me.mrvintage.kingdom.magic.parser;

public class SpellToken {

    private SpellTokenType tokenType;
    private String value;

    public SpellToken(SpellTokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public SpellTokenType getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String... strings) {
        for(String word : strings) {
            if(word.equals(value)) return true;
        }

        return false;
    }
}
