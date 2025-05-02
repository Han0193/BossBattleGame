import java.util.Scanner;
import character.Character;
import character.Boss;

// Old entry point in the game's CLI version
// The new entry point is MainGUI.java

//public class Game {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        CharacterFactory characterFactory = new CharacterFactory();
//        GameManager gameManager = GameManager.getInstance();
//
//        System.out.println("Welcome to Boss Battle!\n");
//
//        while (true) {
//            System.out.println("1. Choose Warrior");
//            System.out.println("2. Choose Mage");
//            System.out.println("3. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline
//
//            if (choice == 3) {
//                System.out.println("Exiting game. Goodbye!");
//                break;
//            }
//
//            System.out.print("Enter your character's name: ");
//            String name = scanner.nextLine();
//
//            Character player = null;
//            if (choice == 1) {
//                player = characterFactory.createCharacter("warrior", name);
//            } else if (choice == 2) {
//                player = characterFactory.createCharacter("mage", name);
//            }
//
//            if (player != null) {
//                gameManager.player = player;
//                gameManager.boss = new Boss("Dark Lord");
//                gameManager.startGame();
//            } else {
//                System.out.println("Invalid selection. Try again.\n");
//            }
//        }
//        scanner.close();
//    }
//}
