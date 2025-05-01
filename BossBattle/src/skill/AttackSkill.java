package skill;
import character.Character;


public class AttackSkill implements Skill {
    @Override
    public void execute(Character user, Character target) {
        System.out.println(user.getName() + " attacks " + target.getName() + "!");
        target.receiveDamage(20);

    }
}

