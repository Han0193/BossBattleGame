package character;

import command.UseSkillCommand;
import skill.AttackSkill;
import skill.DefendSkill;
import skill.HealSkill;
import skill.SkillFactory;


public class Warrior extends Character {
    SkillFactory factory = new SkillFactory();
    public Warrior(String name) {
        super(name, 100);
        addSkill(factory.createSkill("attack"));
        addSkill(factory.createSkill("heal"));
        addSkill(factory.createSkill("defend"));

    }

    @Override
    public void takeTurn(Character opponent) {
        // logic placeholder
        new UseSkillCommand(skills.get(0), this, opponent).execute();
    }
}