package skill;
import character.Character;

public class HealSkill implements  Skill{

    @Override
    public void execute(Character user, Character target) {
        // Check if the target is alive
        System.out.println(user.getName() + " heals " + target.getName() + "!");
        user.receiveDamage(-40);
    }
    }

