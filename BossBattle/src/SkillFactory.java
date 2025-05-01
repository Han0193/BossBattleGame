import skill.AttackSkill;
import skill.BossAttackSkill;
import skill.DefendSkill;
import skill.HealSkill;
import skill.Skill;

public class SkillFactory {
    public Skill createSkill(String type) {
        switch (type.toLowerCase()) {
            case "attack":     return new AttackSkill();
            case "heal":       return new HealSkill();
            case "defend":     return new DefendSkill();
            case "bossattack": return new BossAttackSkill();
            default:           return null;
        }
    }
}
