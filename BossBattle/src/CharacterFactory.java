import character.Mage;
import character.Warrior;
import character.Character;
import character.HealthMonitor;

public class CharacterFactory {
    public Character createCharacter(String type, String name) {
        Character c;
        switch (type.toLowerCase()) {
            case "warrior": c = new Warrior(name); break;
            case "mage":    c = new Mage(name);    break;
            default:        return null;
        }
        c.addObserver(new HealthMonitor());
        return c;
    }
}
