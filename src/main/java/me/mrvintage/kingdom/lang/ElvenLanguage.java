package me.mrvintage.kingdom.lang;

import me.mrvintage.kingdom.magic.parser.SpellTokenType;

enum ElvenWord implements LanguageWord<ElvenCharacter>{
    VOTENI(new String[]{"soulhome"}, SpellTokenType.NOUN, ElvenCharacter.VO, ElvenCharacter.TE, ElvenCharacter.NI),
    CUTEIN(new String[]{"travel", "visit"}, SpellTokenType.VERB, ElvenCharacter.CU, ElvenCharacter.TE, ElvenCharacter.I, ElvenCharacter.N),
    VU(new String[]{"to"}, SpellTokenType.PREPOSITION, ElvenCharacter.VU),
    LENEIL(new String[]{"tree"}, SpellTokenType.NOUN, ElvenCharacter.LE, ElvenCharacter.NE, ElvenCharacter.I, ElvenCharacter.L);

    ElvenCharacter[] characters;
    String[] translations;
    SpellTokenType tokenType;

    ElvenWord(String[] translations, SpellTokenType tokenType, ElvenCharacter... characters) {
        this.characters = characters;
        this.translations = translations;
        this.tokenType = tokenType;
    }

    @Override
    public ElvenCharacter[] getCharacters() {
        return characters;
    }

    @Override
    public String[] getDirectTranslations() {
        return translations;
    }

    @Override
    public SpellTokenType getTokenType() {
        return tokenType;
    }
}

enum ElvenCharacter implements LanguageCharacter {
    C("c", "\uFF00"),
    CA("ca", "\uFF00\uF807\uFF10"),
    CE("ce", "\uFF00\uF807\uFF11"),
    CI("ci", "\uFF00\uF807\uFF12"),
    CO("co", "\uFF00\uF807\uFF13"),
    CU("co", "\uFF00\uF807\uFF14"),
    L("l", "\uFF01"),
    LA("la", "\uFF01\uF807\uFF10"),
    LE("le", "\uFF01\uF807\uFF11"),
    LI("li", "\uFF01\uF807\uFF12"),
    LO("lo", "\uFF01\uF807\uFF13"),
    LU("lo", "\uFF01\uF807\uFF14"),
    M("m", "\uFF02"),
    MA("ma", "\uFF02\uF807\uFF10"),
    ME("me", "\uFF02\uF807\uFF11"),
    MI("mi", "\uFF02\uF807\uFF12"),
    MO("mo", "\uFF02\uF807\uFF13"),
    MU("mo", "\uFF02\uF807\uFF14"),
    N("n", "\uFF03"),
    NA("na", "\uFF03\uF807\uFF10"),
    NE("ne", "\uFF03\uF807\uFF11"),
    NI("ni", "\uFF03\uF807\uFF12"),
    NO("no", "\uFF03\uF807\uFF13"),
    NU("no", "\uFF03\uF807\uFF14"),
    P("p", "\uFF02"),
    PA("pa", "\uFF04\uF807\uFF10"),
    PE("pe", "\uFF04\uF807\uFF11"),
    PI("pi", "\uFF04\uF807\uFF12"),
    PO("po", "\uFF04\uF807\uFF13"),
    PU("po", "\uFF04\uF807\uFF14"),
    R("r", "\uFF05"),
    RA("ra", "\uFF05\uF807\uFF10"),
    RE("re", "\uFF05\uF807\uFF11"),
    RI("ri", "\uFF05\uF807\uFF12"),
    RO("ro", "\uFF05\uF807\uFF13"),
    RU("ro", "\uFF05\uF807\uFF14"),
    S("s", "\uFF06"),
    SA("sa", "\uFF06\uF807\uFF10"),
    SE("se", "\uFF06\uF807\uFF11"),
    SI("si", "\uFF06\uF807\uFF12"),
    SO("so", "\uFF06\uF807\uFF13"),
    SU("so", "\uFF06\uF807\uFF14"),
    T("t", "\uFF07"),
    TA("ta", "\uFF07\uF807\uFF10"),
    TE("te", "\uFF07\uF807\uFF11"),
    TI("ti", "\uFF07\uF807\uFF12"),
    TO("to", "\uFF07\uF807\uFF13"),
    TU("to", "\uFF07\uF807\uFF14"),
    V("v", "\uFF08"),
    VA("va", "\uFF08\uF807\uFF10"),
    VE("ve", "\uFF08\uF807\uFF11"),
    VI("vi", "\uFF08\uF807\uFF12"),
    VO("vo", "\uFF08\uF807\uFF13"),
    VU("vo", "\uFF08\uF807\uFF14"),
    Y("y", "\uFF09"),
    YA("ya", "\uFF09\uF807\uFF10"),
    YE("ye", "\uFF09\uF807\uFF11"),
    YI("yi", "\uFF09\uF807\uFF12"),
    YO("yo", "\uFF09\uF807\uFF13"),
    YU("yo", "\uFF09\uF807\uFF14"),
    A("a", "\uFF15\uF807\uFF10"),
    E("e", "\uFF15\uF807\uFF11"),
    I("i", "\uFF15\uF807\uFF12"),
    O("o", "\uFF15\uF807\uFF13"),
    U("o", "\uFF15\uF807\uFF14"),
    ;

    String englishCharacters, elvenCharacters;

    ElvenCharacter(String englishCharacters, String elvenCharacters) {
        this.englishCharacters = englishCharacters;
        this.elvenCharacters = elvenCharacters;
    }

    @Override
    public String getEnglishCharacters() {
        return englishCharacters;
    }

    @Override
    public String getLanguageCharacters() {
        return elvenCharacters;
    }
}
