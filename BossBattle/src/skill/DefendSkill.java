package skill;
import character.Character;

public class DefendSkill implements Skill {
    @Override
    public void execute(Character user, Character target) {
        System.out.println(user.getName() + " takes a defensive stance. Damage will be reduced on the next hit.");
        user.setDefending(true);
    }
}