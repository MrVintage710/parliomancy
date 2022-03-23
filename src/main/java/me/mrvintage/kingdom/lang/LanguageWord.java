package me.mrvintage.kingdom.lang;

public interface LanguageWord<T extends LanguageCharacter> {

    T[] getCharacters();

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
