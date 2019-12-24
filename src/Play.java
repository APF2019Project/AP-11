import java.util.Scanner;

public class Play {

    static void goPlay() { // Play Menu index: -4
        Scanner scanner = new Scanner(System.in);
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Play Menu ---");
            System.out.println("What kind of playground do you want?");
            String playType = scanner.nextLine();
            switch (playType.toLowerCase()) {
                case "day":
                    Day();
                    break;
                case "water":
                    Water();
                    break;
                case "rail":
                    Rail();
                    break;
                case "zombie":
                    Zombie();
                    break;
                case "pvp":
                    PvP();
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

        Collection.collectionMenu(1);

    }

    static void Water() { // Play type index: 2

        Collection.collectionMenu(2);
    }

    static void Rail() { // Play type index: 3
        //
    }

    static void Zombie() { // Play type index: 4

        Collection.collectionMenu(4);
    }

    static void PvP() { // Play type index: 5
        //
    }


}
