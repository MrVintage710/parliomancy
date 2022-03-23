package me.mrvintage.kingdom.lang;

public interface LanguageMap {
    boolean isInDictionary(String word);

    String fromEnglish(String word);

    String toEnglish(String word);

    String match(String chars);
}
