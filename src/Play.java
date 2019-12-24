import java.util.Scanner;

public class Play {

    static void goPlay() { // Play Menu index: -4
        Scanner scanner = new Scanner(System.in);
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Play Menu ---");
            System.out.println("What kind of playground do you want?");
            String playType = scanner.nextLine();
            switch (playType) {
                case "Day":
                    Day();
                    break;
                case "Water":
                    Water();
                    break;
                case "Rail":
                    Rail();
                    break;
                case "Zombie":
                    Zombie();
                    break;
                case "PvP":
                    PvP();
                    break;
                case "Exit":
                    View.goingBackTo(-2);
                    whileTrue = false;
                    break;
                case "Help":
                    View.showHelp(-4);
                    break;
                default:
                    View.invalidCommand(-4);
                    break;
            }
        }
    }

    static void Day() { // Play type index: 1

        Collection.collectionMenu(1);

    }

    static void Water() { // Play type index: 2

    }

    static void Rail() { // Play type index: 3

    }

    static void Zombie() { // Play type index: 4

    }

    static void PvP() { // Play type index: 5

    }


}
