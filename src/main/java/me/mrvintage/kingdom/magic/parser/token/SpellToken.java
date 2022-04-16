package me.mrvintage.kingdom.magic.parser.token;

import me.mrvintage.kingdom.lang.LanguageMap;

public class SpellToken {

    private SpellTokenType tokenType;
    private LanguageMap language;
    private String value;

    public SpellToken(SpellTokenType tokenType, LanguageMap language, String value) {
        this.tokenType = tokenType;
        this.value = value;
        this.language = language;
    }

    public SpellTokenType getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }

    public LanguageMap getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return tokenType.toString() + " | " + value;
    }
}
