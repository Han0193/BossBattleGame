package skill;


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
