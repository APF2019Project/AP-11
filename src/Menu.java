import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Scanner;

public class Menu {

    static void loginMenu() { // Login Menu index = -1
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("____ LOGIN MENU ____\nEnter command:");
            command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "create account":
                    Account.createAccount(true);
                    break;
                case "login":
                    Account.login(true);
                    break;
                case "leaderboard":
                    Account.leaderboard();
                    break;
                case "exit":
                    View.goingBackTo(-100);
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-1);
                    break;
                default:
                    View.invalidCommand(-1);
                    break;
            }
        }
    }


    static void mainMenu() { // Main Menu index = -2
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("____ MAIN MENU ____\nEnter command:");
            command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "play":
                    Play.goPlay();
                    break;
                case "profile":
                    Account.profileMenu();
                    break;
                case "shop":
                    Shop.shop();
                    break;
                case "exit":
                    View.goingBackTo(-1);
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-2);
                    break;
                default:
                    View.invalidCommand(-2);
                    break;
            }
        }
    }
}
