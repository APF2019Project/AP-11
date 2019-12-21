import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Scanner;

public class Menu {

    static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("***LOGIN MENU***\nEnter command:");
            command = scanner.nextLine();
            switch (command) {
                case "Create account":
                    Account.createAccount(true);
                    break;
                case "Login":
                    Account.login(true);
                    break;
                case "Leaderboard":
                    Account.leaderboard();
                    break;
                case "Exit":
                    System.out.println("FINISHED");
                    whileTrue = false;
                    break;
                case "Help":
                    loginMenuHelp();
                    break;
                default:
                    System.out.println("invalid command in Login Menu\nTry again:");
                    break;
            }
        }
    }


    static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** MAIN MENU ***\nEnter command:");
        String command;
        command = scanner.nextLine();
        boolean whileTrue = true;
        while(whileTrue) {
            switch (command) {
                case "Play":
                    Play.Play();
                    break;
                case "Profile":
                    Account.profile();
                    break;
                case "Shop":
                    Shop.shop();
                    break;
                case "Exit":
                    System.out.println("going to login menu:");
                    whileTrue = false;
                    break;
                case "Help":
                    mainMenuHelp();
                    break;
                default:
                    System.out.println("invalid command in main menu.\nTry again:");
                    break;
            }
        }
    }

    static void loginMenuHelp() {
        System.out.println("*** Login Menu Help ***");
        System.out.println("Login Menu commands are:");
        System.out.println("Create account, Login, Leaderboard, Exit and Help");
    }

    static void mainMenuHelp() {
        System.out.println("*** Main Menu Help ***");
        System.out.println("Main Menu commands are:");
        System.out.println("Play, Profile, Shop, Exit and Help");
        mainMenu();
    }
}
