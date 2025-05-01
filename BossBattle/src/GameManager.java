import character.Character;
import command.UseSkillCommand;
import skill.Skill;

import java.util.Scanner;

public class GameManager {
    private static GameManager instance;
    public Character player;
    public Character boss;
    private Scanner scanner = new Scanner(System.in);

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        System.out.println("\nA wild " + boss.getName() + " appears!\n");
        while (!isGameOver()) {
            nextTurn();
        }
        System.out.println("\nGame Over.");
        if (player.isAlive()) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lose.");
        }
    }

    public void nextTurn() {
        System.out.println("\n-- Your Turn --");
        displayHealth();
        displaySkills();
        System.out.print("Choose skill (0/1/2): ");
        int choice = scanner.nextInt();
        Skill chosen = player.getSkills().get(choice);
        executePlayerSkill(chosen);

        if (!boss.isAlive()) return;

        System.out.println("\n-- Boss Turn --");
        displayHealth();
        executeBossTurn();
    }

    // New method for GUI integration
    public void executePlayerSkill(Skill skill) {
        new UseSkillCommand(skill, player, boss).execute();
    }

    // New method for GUI integration
    public void executeBossTurn() {
        boss.takeTurn(player);
    }

    private void displaySkills() {
        int i = 0;
        for (Skill s : player.getSkills()) {
            System.out.println(i + ". " + s.getClass().getSimpleName());
            i++;
        }
    }

    private void displayHealth() {
        System.out.println("[Player: " + player.getName() + " | HP: " + player.getHealth() + "]");
        System.out.println("[Boss:   " + boss.getName() + " | HP: " + boss.getHealth() + "]");
    }

    public boolean isGameOver() {
        return !player.isAlive() || !boss.isAlive();
    }
}