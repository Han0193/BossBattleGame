package command;
import character.Character;

import skill.Skill;

public class UseSkillCommand implements Command {
    private Skill skill;
    private Character user;
    private Character target;

    public UseSkillCommand(Skill skill, Character user, Character target) {
        this.skill = skill;
        this.user = user;
        this.target = target;
    }

    @Override
    public void execute() {
        skill.execute(user, target);

    }
}