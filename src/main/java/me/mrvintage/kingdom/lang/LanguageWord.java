package me.mrvintage.kingdom.lang;

import me.mrvintage.kingdom.magic.parser.SpellTokenType;

public interface LanguageWord<T extends LanguageCharacter> {

    T[] getCharacters();

    String[] getDirectTranslations();

    SpellTokenType getTokenType();

    default boolean isDirectTranslation(String word) {
        for(String w : getDirectTranslations()) {
            if(w.equals(word)) return true;
        }
        return false;
    }

    default String inEnglish() {
        String word = "";
        for(T character : this.getCharacters()) {
            word += character.getEnglishCharacters();
        }
        return word;
    }

    default String inLanguage() {
        String word = "";
        for(T character : this.getCharacters()) {
            word += character.getLanguageCharacters();
        }
        return word;
    }
}
