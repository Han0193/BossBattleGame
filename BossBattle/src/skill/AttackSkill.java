package skill;
import character.Character;


public class AttackSkill implements Skill {
    int damage;
    public AttackSkill (int damage) {
        this.damage = damage;
    }
    @Override
    public void execute(Character user, Character target) {
        System.out.println(user.getName() + " attacks " + target.getName() + "!");
        target.receiveDamage(this.damage);

    }
}

