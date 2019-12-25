import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static void Day() { // Play type index: 1
        
        boolean whileTrue = true;
        String command;
        Pattern plantPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        while (whileTrue) {
            System.out.println("--- Day Play Menu ---\nEnter command:");
            command = scanner.nextLine();
            Matcher plantMatcher = plantPattern.matcher(command);

            if (command.toLowerCase().matches("select (.+)")) {
                String cardName = getCardName(command);
                selectPlant(cardName);
            } else if (plantMatcher.matches()) {
                int row = Integer.parseInt(plantMatcher.group("row"));
                int column = Integer.parseInt(plantMatcher.group("column"));
                plantSelectedPlant(row, column);
            }

        }


    }

    static void Water() { // Play type index: 2


    }

    static void Rail() { // Play type index: 3
        //
    }

    static void Zombie() { // Play type index: 4


    }

    static void PvP() { // Play type index: 5
        //
    }

    private static String getCardName(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return command;
    }

    private static void selectPlant(String plantName) {
        if (!Collection.plantExistsInDeck(plantName)) {
            View.cardNotInDeck("plants", plantName);
        } else {
            selectedPlant = Plant.getPlant(plantName);
            View.cardSelected(plantName);
        }
    }

    private static void plantSelectedPlant(int row, int column) {

    }

}
