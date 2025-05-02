import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import character.Character;
import character.Boss;
import skill.Skill;
import command.UseSkillCommand;

public class GameGUI extends JFrame {
    private static GameGUI instance;

    private JPanel mainPanel;
    private JPanel characterSelectionPanel;
    private JPanel battlePanel;

    private JTextField nameField;
    private JButton warriorButton;
    private JButton mageButton;

    private JLabel playerStatusLabel;
    private JLabel bossStatusLabel;
    private JPanel skillButtonsPanel;
    private JTextArea battleLogArea;

    private CharacterFactory characterFactory;
    private GameManager gameManager;
    private Character player;
    private Character boss;


    public static GameGUI getInstance() {
        if (instance == null) {
            instance = new GameGUI();
        }
        return instance;
    } // For singleton

    private GameGUI() {
        // Initialize components
        characterFactory = new CharacterFactory();
        gameManager = GameManager.getInstance();

        // Set up the frame
        setTitle("Boss Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create the main panel with CardLayout to switch between screens
        mainPanel = new JPanel(new CardLayout());

        // Create and setup character selection panel
        createCharacterSelectionPanel();

        // Create and setup battle panel
        createBattlePanel();

        // Add panels to the main panel
        mainPanel.add(characterSelectionPanel, "selection");
        mainPanel.add(battlePanel, "battle");

        // Add the main panel to the frame
        add(mainPanel);

        // Show the character selection panel first
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "selection");
    }

    private void createCharacterSelectionPanel() {
        characterSelectionPanel = new JPanel();
        characterSelectionPanel.setLayout(new BoxLayout(characterSelectionPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to Boss Battle!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel nameLabel = new JLabel("Enter your character's name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField(20);
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel chooseClassLabel = new JLabel("Choose your class:");
        chooseClassLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        warriorButton = new JButton("Warrior");
        mageButton = new JButton("Mage");

        warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCharacter("warrior");
            }
        });

        mageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCharacter("mage");
            }
        });

        buttonPanel.add(warriorButton);
        buttonPanel.add(mageButton);

        // Add components to the panel with some spacing
        characterSelectionPanel.add(Box.createVerticalStrut(30));
        characterSelectionPanel.add(titleLabel);
        characterSelectionPanel.add(Box.createVerticalStrut(30));
        characterSelectionPanel.add(nameLabel);
        characterSelectionPanel.add(Box.createVerticalStrut(10));
        characterSelectionPanel.add(nameField);
        characterSelectionPanel.add(Box.createVerticalStrut(20));
        characterSelectionPanel.add(chooseClassLabel);
        characterSelectionPanel.add(Box.createVerticalStrut(10));
        characterSelectionPanel.add(buttonPanel);
    }

    private void createBattlePanel() {
        battlePanel = new JPanel(new BorderLayout());

        // Status panel at the top
        JPanel statusPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        playerStatusLabel = new JLabel("Player: No Character Selected");
        bossStatusLabel = new JLabel("Boss: Not Started");
        statusPanel.add(playerStatusLabel);
        statusPanel.add(bossStatusLabel);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Battle log in the center
        battleLogArea = new JTextArea();
        battleLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(battleLogArea);

        // Skill buttons at the bottom
        skillButtonsPanel = new JPanel();

        // Add components to the panel
        battlePanel.add(statusPanel, BorderLayout.NORTH);
        battlePanel.add(scrollPane, BorderLayout.CENTER);
        battlePanel.add(skillButtonsPanel, BorderLayout.SOUTH);
    }

    private void createCharacter(String type) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a character name.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create character
        player = characterFactory.createCharacter(type, name);
        boss = new Boss("Dark Lord");

        // Update game manager
        gameManager.player = player;
        gameManager.boss = boss;

        // Update UI
        updateStatusLabels();
        setupSkillButtons();

        // Show battle log message
        appendToBattleLog("A wild " + boss.getName() + " appears!\n");

        // Switch to battle panel
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "battle");
    }

    private void updateStatusLabels() {
        playerStatusLabel.setText("Player: " + player.getName() + " | HP: " + player.getHealth());
        bossStatusLabel.setText("Boss: " + boss.getName() + " | HP: " + boss.getHealth());
    }

    private void setupSkillButtons() {
        skillButtonsPanel.removeAll();

        for (int i = 0; i < player.getSkills().size(); i++) {
            final int skillIndex = i;
            Skill skill = player.getSkills().get(i);

            // Get the class name and remove the "Skill" suffix
            String skillName = skill.getClass().getSimpleName();
            if (skillName.endsWith("Skill")) {
                skillName = skillName.substring(0, skillName.length() - 5);
            }

            JButton skillButton = new JButton(skillName);
            skillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    useSkill(skillIndex);
                }
            });

            skillButtonsPanel.add(skillButton);
        }

        skillButtonsPanel.revalidate();
        skillButtonsPanel.repaint();
    }

    private void useSkill(int skillIndex) {
        if (gameManager.isGameOver()) {
            return;
        }

        // Player turn
        appendToBattleLog("\n-- Your Turn --");
        appendToBattleLog("[Player: " + player.getName() + " | HP: " + player.getHealth() + "]");
        appendToBattleLog("[Boss:   " + boss.getName() + " | HP: " + boss.getHealth() + "]");

        Skill chosenSkill = player.getSkills().get(skillIndex);

        // Create a single observer for the whole turn
        BattleLoggerObserver observer = new BattleLoggerObserver(this);
        observer.redirectConsoleOutput();

        // Execute player skill
        System.out.println(player.getName() + " uses " + chosenSkill.getClass().getSimpleName().replace("Skill", "") + "!");
        chosenSkill.execute(player, boss);

        updateStatusLabels();

        // Check if game is over after player's turn
        if (gameManager.isGameOver()) {
            observer.restoreConsoleOutput();
            handleGameOver();
            return;
        }

        // Boss turn
        System.out.println("\n-- Boss Turn --");
        boss.takeTurn(player);

        // Restore console output after both turns are complete
        observer.restoreConsoleOutput();

        updateStatusLabels();

        // Check if game is over after boss's turn
        if (gameManager.isGameOver()) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        appendToBattleLog("\nGame Over.");
        if (player.isAlive()) {
            appendToBattleLog("You Win!");
        } else {
            appendToBattleLog("You Lose.");
        }

        // Disable skill buttons
        for (Component component : skillButtonsPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setEnabled(false);
            }
        }

        // Add play again button
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        skillButtonsPanel.add(playAgainButton);
        skillButtonsPanel.revalidate();
        skillButtonsPanel.repaint();
    }

    private void resetGame() {
        // Switch back to character selection
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "selection");

        // Clear battle log
        battleLogArea.setText("");
        nameField.setText("");
    }

    public void appendToBattleLog(String message) {
        battleLogArea.append(message + "\n");
        // Scroll to the bottom
        battleLogArea.setCaretPosition(battleLogArea.getDocument().getLength());
    }
 
}