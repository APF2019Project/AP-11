import java.util.Scanner;

public class Play {

    static void Play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Play ****");
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
                System.out.println("going  back to main menu:");
                Menu.mainMenu();
                break;
            case "Help":
                playMenuHelp();
            default:
                System.out.println("invalid play type in Play menu.\nTry again:");
                Play();
        }
    }

    static void Day() {

    }

    static void Water() {

    }

    static void Rail() {

    }

    static void Zombie() {

    }

    static void PvP() {

    }

    static void playMenuHelp() {
        System.out.println("*** Play Menu Help ***");
        System.out.println("Play Menu commands are:");
        System.out.println("Day, Water, Rail, Zombie, PvP, Exit and Help");
        Play();
    }
}
