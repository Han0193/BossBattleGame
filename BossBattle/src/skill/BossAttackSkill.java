package skill;

import character.Character;
import java.util.Random;

public class BossAttackSkill implements Skill {
    private Random random = new Random();
    private static final double INSTANT_KILL_RATE = 0.01;
    private static final int MISS_CHANCE = 30;
    private static final int CRIT_CHANCE = 20;
    private static final int BASE_DAMAGE = 20;

    @Override
    public void execute(Character user, Character target) {
        System.out.println(user.getName() + " attacks " + target.getName() + "!");
        if (random.nextDouble() < INSTANT_KILL_RATE) {
            System.out.println(user.getName() + " uses instant kill!");
            target.receiveDamage(target.getHealth());
            return;
        }
        if (random.nextInt(100) < MISS_CHANCE) {
            System.out.println(user.getName() + " misses!");
            return;
        }
        int damage = BASE_DAMAGE;
        if (random.nextInt(100) < CRIT_CHANCE) {
            damage *= 2;
            System.out.println("Critical hit!");
        }
        target.receiveDamage(damage);
    }
}
