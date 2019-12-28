import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);

    static void loginMenu() { // Login Menu index = -1
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("______ LOGIN MENU ______\nEnter command:");
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
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("______ MAIN MENU ______\nEnter command:");
            command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "play":
                    goPlay();
                    break;
                case "profile":
                    profileMenu();
                    break;
                case "shop":
                    shopMenu();
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



    static void profileMenu() { // Profile Menu index = -3
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- PROFILE MENU ---");
            System.out.println("You are logged in as: " + Account.getPlayingAccount().getUsername());
            System.out.println("Enter command:");
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "change":
                    System.out.println("Logging in with another account:");
                    Account.login(false);
                    break;
                case "change password":
                    Account.changePassword();
                    break;
                case "delete account":
                    Account.deleteAccount();
                    break;
                case "rename":
                    Account.renameAccount();
                    break;
                case "create account":
                    Account.createAccount(false);
                    break;
                case "show":
                    Account.showAccount();
                    break;
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-3);
                    break;
                default:
                    View.invalidCommand(-3);
                    break;
            }
        }
    }



    static void goPlay() { // Play Menu index: -4
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Play Menu ---");

            System.out.println("Choose your play type:");
            String playType = scanner.nextLine();
            switch (playType.toLowerCase()) {
                case "day":
                    collectionMenu(1);
                    break;
                case "water":
                    collectionMenu(2);
                    break;
                case "rail":
                    //
                    break;
                case "zombie":
                    collectionMenu(4);
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



    public static void shopMenu() { // Show Shop index: -10
        String command;
        boolean exitShop = false;
        while (!exitShop) {
            System.out.println("--- Shop ---\nEnter command:");
            command = scanner.nextLine();

            if (command.matches("[b,B]uy (.+)")) {
                Shop.readyToBuy(command);
                continue;
            }
            switch (command.toLowerCase()) {
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    exitShop = true;
                    break;
                case "show shop":
                    Shop.showShop(Account.getPlayingAccount());
                    break;
                case "show collection":
                    Shop.showCollection(Account.getPlayingAccount());
                    break;
                case "money":
                    Shop.showMoney(Account.getPlayingAccount());
                    break;
                case "pooool mikhaaaam":
                    Account.getPlayingAccount().setMoney(Account.getPlayingAccount().getMoney() + 1000);
                    break;
                case "help":
                    View.showHelp(-10);
                    break;
                default:
                    View.invalidCommand(-10);
                    break;
            }
        }
    }





    static void collectionMenu(int playTypeIndex) { // Collection Menu index: -5

        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Collection Menu ---\nEnter command:");
            command = scanner.nextLine();

            if (command.toLowerCase().matches("select (.+)")) {
                Collection.readyToSelect(command, playTypeIndex);
                continue;
            } else if (command.toLowerCase().matches("remove (.+)")) {
                Collection.readyToRemove(command, playTypeIndex);
                continue;
            }
            switch (command.toLowerCase()) {
                case "show hand":
                    Collection.showHand(playTypeIndex);
                    break;
                case "show collection":
                    Collection.showCollection(playTypeIndex);
                    break;
                case "play":
                    Play.goToPlayByPlayType(playTypeIndex);
                    break;
                case "exit": // Going back to Play Menu
                    View.goingBackTo(-4);
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-5);
                    break;
                default:
                    View.invalidCommand(-5);
                    break;
            }
        }
    }





    public static void dayMenu(int playTypeIndex) {  // DayAndWater Menu index : 11

        boolean whileTrue = true;
        String command;
        Pattern plantingPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        Pattern removePattern = Pattern.compile("[r,R]emove (?<row>\\d+),(?<column>\\d+)");

        while (whileTrue) {
            if (playTypeIndex == 1) {
                System.out.println("^-^-^-^- Day Menu -^-^-^-^\nEnter command:");
            } else if (playTypeIndex == 2) {
                System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
            }

            command = scanner.nextLine();
            Matcher plantMatcher = plantingPattern.matcher(command);
            Matcher removeMatcher = removePattern.matcher(command);

            if (command.matches("[s,S]elect (.+)")) {
                String cardName = Day.getCardName(command);
                Day.selectPlant(cardName);

            } else if (plantMatcher.matches()) {
                if (Day.selectedPlant == null) {
                    View.noPlantIsSelected();
                    return;
                }
                int row = Integer.parseInt(plantMatcher.group("row"));
                int column = Integer.parseInt(plantMatcher.group("column"));
                Day.plantSelectedPlant(row, column, Day.selectedPlant.getName());

            } else if (removeMatcher.matches()) {
                int row = Integer.parseInt(removeMatcher.group("row"));
                int column = Integer.parseInt(removeMatcher.group("column"));
                Day.removePlant(row, column);

            } else if (command.toLowerCase().equals("show hand")) {
                Day.showHandInDay();

            } else if (command.toLowerCase().equals("end turn")) {
                Collection.decreaseRespawnsInDeck();
                whileTrue = false;
                return;

            } else if (command.toLowerCase().equals("show lawn")) {
                //

            } else if (command.toLowerCase().equals("exit")) {
                boolean exit = View.areYouSureExitingPlay();
                if (exit) {
                    whileTrue = false;
                    Day.setWhileDayTurn(false);
                    View.goingBackTo(-5);
                    Collection.clearPlantsDeck();
                    Collection.clearZombiesDeck();
                }
            } else if (command.toLowerCase().equals("help")) {
                if (playTypeIndex == 1) {
                    View.showHelp(11);
                } else if (playTypeIndex == 2) {
                    View.showHelp(22);
                }

            } else if (command.toLowerCase().equals("sun mikhaaam")) {
                Day.setSun(Day.getSun() + 5);

            } else if (command.toLowerCase().equals("respawn mikhaaam")) {
                Collection.decreaseRespawnsInDeck();

            } else {
                if (playTypeIndex == 1) {
                    View.invalidCommand(11);
                } else if (playTypeIndex == 2) {
                    View.invalidCommand(22);
                }
            }
        }
    }






    public static void zombieMenu() { // Zombie Menu index: 44
        String command;
        boolean whileTrue = true;
        Pattern putPattern = Pattern.compile("[p,P]ut (?<zombieName>.+),(?<row>\\d+)");

        while(whileTrue) {
            System.out.println("^-^-^-^ Zombie Menu ^-^-^-^-\nEnter command:");
            command = scanner.nextLine();

            Matcher putMatcher = putPattern.matcher(command);

            if (putMatcher.matches()) {
                String zombieName = putMatcher.group("zombieName");
                int row = Integer.parseInt(putMatcher.group("row"));

            } else if (command.toLowerCase().equals("show hand")){
                //
            } else if (command.toLowerCase().equals("show lanes")) {
                //
            } else if (command.toLowerCase().equals("start")) {
                //
            } else if (command.toLowerCase().equals("end turn")) {
                //
            } else if (command.toLowerCase().equals("show lawn")) {
                //
            } else if (command.toLowerCase().equals("help")) {
                //
            } else if (command.toLowerCase().equals("exit")) {
                //
            } else {
                View.invalidCommand(44);
            }
        }
    }



}
