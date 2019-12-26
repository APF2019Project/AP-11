import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Play Type Indexes:
// Day: 1
// Water: 2
// Rail: 3
// Zombie: 4
// PvP: 5

public class Play {

    public static Scanner scanner = new Scanner(System.in);

    public static Plant selectedPlant = null;

    static void goPlay() { // Play Menu index: -4
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Play Menu ---");

            System.out.println("Choose your play type:");
            String playType = scanner.nextLine();
            switch (playType.toLowerCase()) {
                case "day":
                    Collection.collectionMenu(1);
                    break;
                case "water":
                    Collection.collectionMenu(2);
                    break;
                case "rail":
                    //
                    break;
                case "zombie":
                    Collection.collectionMenu(4);
                    break;
                case "pvp":
                    //
                    break;
                case "exit":
                    View.goingBackTo(-2);
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-4);
                    break;
                default:
                    View.invalidCommand(-4);
                    break;
            }
        }
    }

}
