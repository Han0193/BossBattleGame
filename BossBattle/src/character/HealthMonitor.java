package character;

public class HealthMonitor implements HealthObserver {
    @Override
    public void onHealthChange(Character character) {
        System.out.println(character.getName() + " health is now: " + character.getHealth());
    }
}
