package me.mrvintage.kingdom.lang;

import me.mrvintage.kingdom.magic.parser.token.SpellTokenType;
import me.mrvintage.kingdom.util.DepthMap;

import java.util.Optional;

public class LanguageMap<T extends LanguageWord> {

    private DepthMap<String, T> toLanguageMap = new DepthMap<>();
    private DepthMap<T, String> toEnglishMap = new DepthMap<>();

    public LanguageMap(Class<T> clazz) {
        if(clazz.isEnum()) {
            for(T word : clazz.getEnumConstants()) {
                for(String translation : word.getDirectTranslations()) {
                    toLanguageMap.put(translation, word);
                    toEnglishMap.put(word, translation);
                }
            }
        }
    }

    public boolean isInLanguageDictionary(T word){
        return toEnglishMap.contains(word);
    }

    public boolean isInEnglishDictionary(String word) {
        return toLanguageMap.contains(word);
    }

    public Optional<String> fromEnglishToLatinLanguage(String word) {
        if(isInEnglishDictionary(word)) return Optional.of(toLanguageMap.first(word).inEnglish());
        return Optional.empty();
    }

    public Optional<String> fromEnglishToLanguage(String word) {
        if(isInEnglishDictionary(word)) return Optional.of(toLanguageMap.first(word).inLanguage());
        return Optional.empty();
    }

    public Optional<SpellTokenType> getTokenType(String word) {
        if(isInEnglishDictionary(word)) return Optional.of(toLanguageMap.first(word).getTokenType());
        return Optional.empty();
    }

    public Optional<String> toEnglish(T word) {
        if(isInLanguageDictionary(word)) toEnglishMap.first(word);
        return Optional.empty();
    }
}
