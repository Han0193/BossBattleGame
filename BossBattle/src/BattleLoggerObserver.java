import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class BattleLoggerObserver {
    private GameGUI gui;
    private PrintStream originalOut;
    private Set<String> seenMessages = new HashSet<>();

    public BattleLoggerObserver(GameGUI gui) {
        this.gui = gui;
    }

    public void redirectConsoleOutput() {
        // Save the original output stream
        originalOut = System.out;
        seenMessages.clear(); // Clear the set of seen messages

        // Create a new output stream
        PrintStream printStream = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                // Only log messages we haven't seen before
                if (!seenMessages.contains(x)) {
                    seenMessages.add(x);
                    gui.appendToBattleLog(x);
                }
                originalOut.println(x);
            }

            @Override
            public void print(String x) {
                // Only append non-empty strings we haven't seen before
                if (!x.isEmpty() && !seenMessages.contains(x)) {
                    seenMessages.add(x);
                    gui.appendToBattleLog(x);
                }
                originalOut.print(x);
            }
        };

        // Redirect system output
        System.setOut(printStream);
    }

    public void restoreConsoleOutput() {
        // Restore the original output stream
        System.setOut(originalOut);
    }
}