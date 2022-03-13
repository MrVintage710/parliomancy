package me.mrvintage.parlio.progresion.level;

public class SkillInstance<T extends LevelableSkill> {

    private int level = 1;
    private int exp = 0;
    private T skill;

    public SkillInstance(T skill) {
        this.skill = skill;
    }
}
