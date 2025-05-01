package character;
import command.UseSkillCommand;
import skill.BossAttackSkill;
import skill.DefendSkill;
import skill.Skill;
import java.util.Random;

public class Boss extends Character {
    private Random random = new Random();

    public Boss(String name) {
        super(name, 150);
        addSkill(new BossAttackSkill());
        addSkill(new DefendSkill());
    }

    @Override
    public void takeTurn(Character opponent) {
        Skill s = skills.get(random.nextInt(skills.size()));
        new UseSkillCommand(s, this, opponent).execute();
    }
}
