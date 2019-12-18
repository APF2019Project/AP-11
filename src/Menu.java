import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Scanner;

public class Menu {

    static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("***LOGIN MENU***\nEnter command:");
        command = scanner.nextLine();

        switch (command) {
            case "Create account":
                Account.createAccount();
                break;
            case "Login":
                Account.login();
                break;
            case "Leaderboard":
                Account.leaderboard();
                break;
            case "Exit":
                System.out.println("FINISHED");
                break;
            default:
                System.out.println("invalid command in Login Menu");
                loginMenu();
                break;
        }
    }

    static void mainMenu() {

        

    }
}
