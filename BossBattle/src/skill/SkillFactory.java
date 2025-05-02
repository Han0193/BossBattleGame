package skill;


public class SkillFactory {
    public Skill createSkill(String type) {
        switch (type.toLowerCase()) {
            case "attack":     return new AttackSkill(20);
            case "heal":       return new HealSkill();
            case "defend":     return new DefendSkill();
            case "bossattack": return new BossAttackSkill();
            default:           return null;
        }
    }

    public Skill createSkill(String type, int damage) {
        switch (type.toLowerCase()) {
            case "attack":     return new AttackSkill(damage);
            case "heal":       return new HealSkill();
            case "defend":     return new DefendSkill();
            case "bossattack": return new BossAttackSkill();
            default:           return null;
        }
    }
}
