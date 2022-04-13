package me.mrvintage.kingdom.magic.parser.preposition;

public enum SpellPreposition {
    TO("to");

    private String match;

    SpellPreposition(String match) {
        this.match = match;
    }

    public String getMatch() {
        return match;
    }
}
