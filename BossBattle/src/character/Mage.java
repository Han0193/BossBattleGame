package character;

import command.UseSkillCommand;
import skill.AttackSkill;
import skill.DefendSkill;
import skill.HealSkill;

public class Mage extends Character {
    public Mage(String name) {
        super(name, 80);
        addSkill(new AttackSkill());
        addSkill(new HealSkill());
//        addSkill(new DefendSkill());
    }

    @Override
    public void takeTurn(Character opponent) {
        new UseSkillCommand(skills.get(1), this, this).execute(); // heals in demo
    }
}