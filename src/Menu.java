import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    static void loginMenu() { // Login Menu index = -1

        String command;
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("create account");
        instructions.add("login");
        instructions.add("leaderboard");
        instructions.add("exit");
        instructions.add("help");
        System.out.println("_____________ LOGIN MENU _____________");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {

            if (!headerPrinted) {
                System.out.println("_____________ LOGIN MENU _____________");
                headerPrinted = true;
            }
            System.out.println("Enter command:");

            command = View.input();
            switch (command.toLowerCase()) {
                case "create account":
                    Account.createAccount(true);
                    headerPrinted = false;
                    break;
                case "login":
                    Account.login(true);
                    headerPrinted = false;
                    break;
                case "leaderboard":
                    Account.leaderboard();
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-100);
                    whileTrue = false;
                    break;
                case "help":
//                    View.showHelp(-1);
                    System.out.println("_____________ LOGIN MENU _____________");
                    View.printNumberedStringArrayList(instructions);
                    break;
                default:
                    View.invalidCommand(-1);
                    break;
            }
        }
    }


    static void mainMenu() { // Main Menu index = -2
        String command;

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("play");
        instructions.add("profile");
        instructions.add("shop");
        instructions.add("exit");
        instructions.add("help");
        System.out.println("______ MAIN MENU ______");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("______ MAIN MENU ______");
            }
            System.out.println("Enter command:");

            command = View.input();
            switch (command.toLowerCase()) {
                case "play":
                    goPlay();
                    headerPrinted = false;
                    break;
                case "profile":
                    profileMenu();
                    headerPrinted = false;
                    break;
                case "shop":
                    shopMenu();
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-1);
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("______ MAIN MENU ______");
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-2);
                    break;
                default:
                    View.invalidCommand(-2);
                    break;
            }
        }
    }


    static void profileMenu() { // Profile Menu index = -3
        boolean whileTrue = true;

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("change account");
        instructions.add("change password");
        instructions.add("change username");
        instructions.add("delete account");
        instructions.add("create account");
        instructions.add("show account");
        instructions.add("exit");
        instructions.add("help");
        System.out.println("--- PROFILE MENU ---");
        View.printNumberedStringArrayList(instructions);

        boolean headerPrinted = true;
        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("--- PROFILE MENU ---");
                System.out.println("You are logged in as: " + Account.getPlayingAccount().getUsername());
            }
            System.out.println("Enter command:");

            String command = View.input();
            switch (command.toLowerCase()) {
                case "change account":
                    System.out.println("Logging in with another account:");
                    Account.login(false);
                    break;
                case "change password":
                    Account.changePassword();
                    headerPrinted = false;
                    break;
                case "delete account":
                    Account.deleteAccount();
                    headerPrinted = false;
                    break;
                case "change username":
                    Account.renameAccount();
                    headerPrinted = false;
                    break;
                case "create account":
                    Account.createAccount(false);
                    headerPrinted = false;
                    break;
                case "show account":
                    Account.showAccount();
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("--- PROFILE MENU ---");
                    System.out.println("You are logged in as: " + Account.getPlayingAccount().getUsername());
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-3);
                    break;
                default:
                    View.invalidCommand(-3);
                    break;
            }
        }
    }


    static void goPlay() { // Play Menu index: -4

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("day");
        instructions.add("water");
        instructions.add("rail");
        instructions.add("zombie");
        instructions.add("pvp");
        instructions.add("exit");
        instructions.add("help");
        System.out.println("--- Play Menu ---");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("--- Play Menu ---");
                System.out.println("Choose your play type:");
            }
            System.out.println("Enter command:");

            String playType = View.input();
            switch (playType.toLowerCase()) {
                case "day":
                    collectionMenu(1);
                    headerPrinted = false;
                    break;
                case "water":
                    collectionMenu(2);
                    headerPrinted = false;
                    break;
                case "rail":
                    //
                    headerPrinted = false;
                    break;
                case "zombie":
                    collectionMenu(4);
                    headerPrinted = false;
                    break;
                case "pvp":
                    //
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-2);
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("--- Play Menu ---");
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-4);
                    break;
                default:
                    View.invalidCommand(-4);
                    break;
            }
        }
    }


    public static void shopMenu() { // Shop Menu index: -10
        String command;
        boolean exitShop = false;

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("show shop");
        instructions.add("show collection");
        instructions.add("show money");
        instructions.add("buy [card name]");
        instructions.add("help");
        instructions.add("exit");
        System.out.println("--- Shop Menu ---");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (!exitShop) {
            if (!headerPrinted) {
                System.out.println("--- Shop Menu ---");
                View.printNumberedStringArrayList(instructions);
            }
            System.out.println("Enter command:");

            command = View.input();
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
                    headerPrinted = false;
                    break;
                case "show collection":
                    Shop.showCollection(Account.getPlayingAccount());
                    headerPrinted = false;
                    break;
                case "show money":
                    Shop.showMoney(Account.getPlayingAccount());
                    headerPrinted = false;
                    break;
                case "pooool mikhaaaam":
                    Account.getPlayingAccount().setMoney(Account.getPlayingAccount().getMoney() + 1000);
                    headerPrinted = false;
                    break;
                case "help":
                    System.out.println("--- Shop Menu ---");
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-10);
                    break;
                default:
                    View.invalidCommand(-10);
                    break;
            }
        }
    }


    static void collectionMenu(int playTypeIndex) { // Collection Menu index: -5
        String command;
        boolean whileTrue = true;

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("select [card name]");
        instructions.add("remove [card name]");
        instructions.add("show collection");
        instructions.add("show hand");
        instructions.add("play");
        instructions.add("exit");
        instructions.add("help");
        System.out.println("--- Collection Menu ---");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("--- Collection Menu ---");
            }
            System.out.println("Enter command:");

            command = View.input();
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
                    headerPrinted = false;
                    break;
                case "show collection":
                    Collection.showCollection(playTypeIndex);
                    headerPrinted = false;
                    break;
                case "play":
                    Play.goToPlayByPlayType(playTypeIndex);
                    headerPrinted = false;
                    break;
                case "exit": // Going back to Play Menu
                    View.goingBackTo(-4);
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("--- Collection Menu ---");
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-5);
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

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("select [card name]");
        instructions.add("plant [row],[column]");
        instructions.add("remove [row],[column]");
        instructions.add("show hand");
        instructions.add("show lawn");
        instructions.add("end turn");
        instructions.add("help");
        instructions.add("exit");
        if (playTypeIndex == 1) {
            System.out.println("^-^-^-^- Day Menu -^-^-^-^");
        } else if (playTypeIndex == 2) {
            System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
        }
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (!headerPrinted) {
                if (playTypeIndex == 1) {
                    System.out.println("^-^-^-^- Day Menu -^-^-^-^");
                } else if (playTypeIndex == 2) {
                    System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
                }
            }
            System.out.println("Enter command:");

            command = View.input();
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
                headerPrinted = false;

            } else if (command.toLowerCase().equals("end turn")) {
                Collection.decreaseRespawnsInDeck();
                whileTrue = false;
                return;

            } else if (command.toLowerCase().equals("show lawn")) {
                PlayGround.showLawn();
                headerPrinted = false;


            } else if (command.toLowerCase().equals("exit")) {
                boolean exit = View.areYouSureExitingPlay();
                if (exit) {
                    whileTrue = false;
                    Day.setWhileDayTurn(false);
                    View.goingBackTo(-5);
                    Collection.clearPlantsDeck();
                    Collection.clearZombiesDeck();
                }
                headerPrinted = false;
            } else if (command.toLowerCase().equals("help")) {
                if (playTypeIndex == 1) {
                    System.out.println("^-^-^-^- Day Menu -^-^-^-^");
//                    View.showHelp(11);
                } else if (playTypeIndex == 2) {
                    System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
//                    View.showHelp(22);
                }
                View.printNumberedStringArrayList(instructions);

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

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("put [zombie name],[row]");
        instructions.add("start");
        instructions.add("show hand");
        instructions.add("show lanes");
        instructions.add("show lawn");
        instructions.add("end turn");
        instructions.add("help");
        instructions.add("exit");
        System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
            }
            System.out.println("Enter command:");

            command = View.input();
            Matcher putMatcher = putPattern.matcher(command);

            if (putMatcher.matches()) {
                String zombieName = putMatcher.group("zombieName");
                int row = Integer.parseInt(putMatcher.group("row"));

            } else if (command.toLowerCase().equals("show hand")) {

                headerPrinted = false;
            } else if (command.toLowerCase().equals("show lanes")) {

                headerPrinted = false;
            } else if (command.toLowerCase().equals("start")) {

                headerPrinted = false;
            } else if (command.toLowerCase().equals("end turn")) {
                ZombieStyle.zombieStyleTurn();
                headerPrinted = false;

            } else if (command.toLowerCase().equals("show lawn")) {
                PlayGround.showLawn();
                headerPrinted = false;
            } else if (command.toLowerCase().equals("help")) {
                System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
                View.printNumberedStringArrayList(instructions);

            } else if (command.toLowerCase().equals("exit")) {
                whileTrue = false;
                View.goingBackTo(-5);
            } else {
                View.invalidCommand(44);
            }
        }
    }


    public static void railMenu() {
        String command;
        boolean whileTrue = true;
        Pattern plantingPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        Pattern removePattern = Pattern.compile("[r,R]emove (?<row>\\d+),(?<column>\\d+)");

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("list");
        instructions.add("select [card number]");
        instructions.add("record");
        instructions.add("plant [row],[column]");
        instructions.add("remove [row],[column]");
        instructions.add("show lawn");
        instructions.add("end turn");
        System.out.println("^-^-^-^ Rail Menu ^-^-^-^");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (!headerPrinted) {
                System.out.println("^-^-^-^ Rail Menu ^-^-^-^");
            }
            System.out.println("Enter command");
            command = View.input();
            Matcher plantMatcher = plantingPattern.matcher(command);
            Matcher removeMatcher = removePattern.matcher(command);

            if (command.matches("[s,S]elect (\\d+)")) {
                int cardNumber = Rail.getCardNumber(command);
                //
                //
            } else if (plantMatcher.matches()) {
                if (Day.selectedPlant == null) {
                    View.noPlantIsSelected();
                    return;
                }
                int row = Integer.parseInt(plantMatcher.group("row"));
                int column = Integer.parseInt(plantMatcher.group("column"));
                Day.plantSelectedPlant(row, column, Rail.selectedPlant.getName());

            } else if (removeMatcher.matches()) {
                int row = Integer.parseInt(removeMatcher.group("row"));
                int column = Integer.parseInt(removeMatcher.group("column"));
                Day.removePlant(row, column);

            } else if (command.toLowerCase().equals("list")) {


            } else if (command.toLowerCase().equals("record")) {


            } else if (command.toLowerCase().equals("show lawn")) {


            } else if (command.toLowerCase().equals("end turn")) {


            } else {
                
            }

        }


    }


}
