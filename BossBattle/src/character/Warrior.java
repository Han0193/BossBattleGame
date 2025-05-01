package character;

import command.UseSkillCommand;
import skill.AttackSkill;
import skill.DefendSkill;
import skill.HealSkill;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 100);
        addSkill(new AttackSkill());
        addSkill(new HealSkill());
        addSkill(new DefendSkill());

    }

    @Override
    public void takeTurn(Character opponent) {
        // logic placeholder
        new UseSkillCommand(skills.get(0), this, opponent).execute();
    }
}