package character;

import skill.Skill;
import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected String name;
    protected int health;
    protected boolean defending = false;
    protected List<Skill> skills = new ArrayList<>();
    private List<HealthObserver> observers = new ArrayList<>();

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addObserver(HealthObserver o) {
        observers.add(o);
    }

    public void removeObserver(HealthObserver o) {
        observers.remove(o);
    }

    protected void notifyHealthChange() {
        for (HealthObserver o : observers) {
            o.onHealthChange(this);
        }
    }

    public void receiveDamage(int dmg) {
        if (defending) {
            dmg /= 2;
            defending = false;
        }
        health -= dmg;
        if (health < 0) health = 0;
        notifyHealthChange();
    }

    public void heal(int amount) {
        health += amount;
        notifyHealthChange();
    }

    public void setDefending(boolean d) {
        defending = d;
    }

    public boolean isDefending() {
        return defending;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract void takeTurn(Character opponent);
}
