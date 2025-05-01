package character;

import command.UseSkillCommand;
import skill.AttackSkill;
import skill.DefendSkill;
import skill.HealSkill;
import skill.SkillFactory;

public class Mage extends Character {

    SkillFactory factory = new SkillFactory();
    public Mage(String name) {
        super(name, 80);
        addSkill(factory.createSkill("attack"));
        addSkill(factory.createSkill("heal"));
        addSkill(factory.createSkill("defend"));

    }

    @Override
    public void takeTurn(Character opponent) {
        new UseSkillCommand(skills.get(1), this, this).execute(); // heals in demo
    }
}