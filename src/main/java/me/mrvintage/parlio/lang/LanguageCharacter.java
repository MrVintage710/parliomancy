package me.mrvintage.parlio.lang;

public interface LanguageCharacter {
    String getEnglishCharacters();

    String getLanguageCharacters();

    default boolean match(String text) {
        return text.equals(this.getEnglishCharacters());
    }
}
