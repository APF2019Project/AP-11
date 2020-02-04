import java.awt.event.WindowStateListener;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientMenus {

    public static int aGameHasFinished = 0;

    static void loginMenu() throws IOException { // Login Menu index = -1

        String command;
        ArrayList<String> instructions = new ArrayList<>();
        setLoginMenuHelp(instructions);
        View.printLoginMenuHeader();
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {

            if (!headerPrinted) {
                View.printLoginMenuHeader();
                headerPrinted = true;
            }

            View.enterCommand();
            command = View.input();

            switch (command.toLowerCase()) {
                case "create account":
                case "caa": // shortcut
                    clientTestAccount.createAccount(true);
                    headerPrinted = false;
                    break;
                case "login":
                    aGameHasFinished = 0;
                    clientTestAccount.login(true);
                    headerPrinted = false;
                    break;
                case "leaderboard":
                    clientTestAccount.leaderboard();
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-100);
                    whileTrue = false;
                    break;
                case "help":
                    View.printLoginMenuHeader();
                    View.printNumberedStringArrayList(instructions);
                    headerPrinted = true;
                    break;
                case "ca":
                    Account.cheatAccount();
                    break;
                case "ca2":
                    Account.cheatAccount2();
                    break;
                case "ca3":
                    Account.cheatAccount3();
                    break;
                default:
                    View.invalidCommand(-1);
                    break;
            }
        }
    }


    static void mainMenu() throws IOException { // Main Menu index = -2
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        ArrayList<String> instructions = new ArrayList<>();
        setMainMenuHelp(instructions);
        View.printMainMenuHeader();
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                View.printMainMenuHeader();
            }
            View.enterCommand();

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
                    View.printMainMenuHeader();
                    View.printNumberedStringArrayList(instructions);
                    headerPrinted = true;
                    break;
                default:
                    View.invalidCommand(-2);
                    break;
            }
        }
    }


    static void profileMenu() throws IOException { // Profile Menu index = -3
        System.out.println();
        if (aGameHasFinished == 1) {
            return;
        }

        boolean whileTrue = true;

        ArrayList<String> instructions = new ArrayList<>();
        setProfileMenuHelp(instructions);
        System.out.println("--- PROFILE MENU ---");
        View.printNumberedStringArrayList(instructions);

        boolean headerPrinted = true;
        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                System.out.println("--- PROFILE MENU ---");
                System.out.println("You are logged in as: " + clientTestAccount.clientUsername);
            }
            System.out.println("Enter command:");

            String command = View.input();
            switch (command.toLowerCase()) {
                case "change account":
                    System.out.println("Logging in with another account:");
                    clientTestAccount.login(false);
                    break;
                case "change password":
                    clientTestAccount.changePassword();
                    headerPrinted = false;
                    break;
                case "delete account":
                    clientTestAccount.deleteAccount();
                    headerPrinted = false;
                    break;
                case "change username":
                    clientTestAccount.renameAccount();
                    headerPrinted = false;
                    break;
                case "create account":
                    clientTestAccount.createAccount(false);
                    headerPrinted = false;
                    break;
                case "show account":
                    clientTestAccount.showPlayingUsername();
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("--- PROFILE MENU ---");
                    System.out.println("You are logged in as: " + clientTestAccount.clientUsername);
                    View.printNumberedStringArrayList(instructions);
//                    View.showHelp(-3);
                    break;
                default:
                    View.invalidCommand(-3);
                    break;
            }
        }
    }


    private static int exitFromPVPcollection1 = 0;
    private static int exitFromPVPcollection2 = 0;

    static void goPlay() { // Play Menu index: -4
        if (aGameHasFinished == 1) {
            return;
        }

        ArrayList<String> instructions = new ArrayList<>();
        setGoPlayMenuHelp(instructions);
        System.out.println("--- Play Menu ---");
        System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
        System.out.println("Choose your play type:");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        boolean whileTrue = true;
        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                System.out.println("--- Play Menu ---");
                System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
                System.out.println("Choose your play type:");
            }
            System.out.println("Enter command:");

            String playType = View.input();
            switch (playType.toLowerCase()) {
                case "day":
                    collectionMenu(1, false);
                    headerPrinted = false;
                    break;
                case "water":
                    collectionMenu(2, false);
                    headerPrinted = false;
                    break;
                case "rail":
                    Play.goToPlayByPlayType(3);
                    headerPrinted = false;
                    break;
                case "zombie":
                    collectionMenu(4, false);
                    headerPrinted = false;
                    break;
                case "pvp":
                    // in this line: main account = plant player
                    collectionMenu(1, true);
                    if (exitFromPVPcollection1 == 1) {
                        headerPrinted = false;
                        break;
                    }


                    // switch accounts: main <-- second
                    Account.switchAccount();

                    // in this line, main account = zombie player
                    collectionMenu(4, true);
                    if (exitFromPVPcollection2 == 1) {
                        headerPrinted = false;
                        break;
                    }

                    Play.goToPlayByPlayType(5);
                    headerPrinted = false;
                    break;
                case "exit":
                    View.goingBackTo(-2);
                    whileTrue = false;
                    break;
                case "help":
                    System.out.println("--- Play Menu ---");
                    View.printNumberedStringArrayList(instructions);
                    headerPrinted = true;
//                    View.showHelp(-4);
                    break;
                default:
                    View.invalidCommand(-4);
                    break;
            }
        }
    }


    public static void shopMenu() { // Shop Menu index: -10
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        boolean exitShop = false;

        ArrayList<String> instructions = new ArrayList<>();
        setShopMenuHelp(instructions);
        System.out.println("--- Shop Menu ---");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (!exitShop) {
            if (aGameHasFinished == 1) {
                return;
            }

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
                    Shop.showShop(Account.getMainPlayingAccount());
                    headerPrinted = false;
                    break;
                case "show collection":
                    Shop.showCollection(Account.getMainPlayingAccount());
                    headerPrinted = false;
                    break;
                case "show money":
                    Shop.showMoney(Account.getMainPlayingAccount());
                    headerPrinted = false;
                    break;
                case "pooool mikhaaaam":
                    Account.getMainPlayingAccount().setMoney(Account.getMainPlayingAccount().getMoney() + 1000);
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


    static void collectionMenu(int playTypeIndex, boolean pvp) { // Collection Menu index: -5
        exitFromPVPcollection1 = 0;
        exitFromPVPcollection2 = 0;
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        boolean whileTrue = true;
        ArrayList<String> instructions = new ArrayList<>();
        boolean headerPrinted = false;
        if (!pvp) {
            System.out.println("--- Collection Menu ---");
            System.out.println("Your play type: " + Play.getPlayType(playTypeIndex));
            setCollectionMenuHelp(instructions);
            View.printNumberedStringArrayList(instructions);
            headerPrinted = true;
        } else {
            System.out.println("--- Collection Menu ---");
            System.out.println("Your play type: " + Play.getPlayType(5));
            System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
            if (playTypeIndex == 1)
                System.out.println("You are: Plant player");
            if (playTypeIndex == 4)
                System.out.println("You are: Zombie Player");
            collectionMenuPvPHelp(instructions);
            View.printNumberedStringArrayList(instructions);
            headerPrinted = true;
        }

        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                System.out.println("--- Collection Menu ---");
                if (!pvp)
                    System.out.println("Your play type: " + Play.getPlayType(playTypeIndex));
                else {
                    System.out.println("Your play type: " + Play.getPlayType(5));
                    if (playTypeIndex == 1)
                        System.out.println("You are: Plant player");
                    if (playTypeIndex == 4)
                        System.out.println("You are: Zombie Player");
                }
            }
            System.out.println("Enter command:");

            command = View.input();
            if (command.toLowerCase().matches("select (.+)")) {
                Collection.readyToSelect(command, playTypeIndex);
                headerPrinted = true;
                continue;
            } else if (command.toLowerCase().matches("remove (.+)")) {
                Collection.readyToRemove(command, playTypeIndex);
                headerPrinted = true;
                continue;
            }
            if (pvp && command.toLowerCase().equals("play")) {
                View.invalidCommand(-5);
                headerPrinted = true;
                continue;
            }
            if (pvp && command.toLowerCase().equals("done") && playTypeIndex == 1) {
                System.out.println("You chose your plants. Going to login zombie player:");
                Account.loginForPvPZombiePlayer(Account.getSecondPlayingAccount());
                return;
            }
            if (pvp && command.toLowerCase().equals("done") && playTypeIndex == 4) {
                System.out.println("You chose your zombies. going to play:");
                return;
            }
            switch (command.toLowerCase()) {
                case "show hand":
                    Collection.showHand(playTypeIndex);
                    headerPrinted = true;
                    break;
                case "show collection":
                    Collection.showCollection(playTypeIndex);
                    headerPrinted = true;
                    break;
                case "play":
                    Play.goToPlayByPlayType(playTypeIndex);
                    headerPrinted = false;
                    break;
                case "exit":
                    if (pvp) {
                        if (playTypeIndex == 1) {
                            exitFromPVPcollection1 = 1;
                            Collection.clearZombiesDeck();
                            Collection.clearPlantsDeck();
                            View.goingBackTo(-4); // Going back to Play Menu
                            return;
                        }
                        if (playTypeIndex == 4) {
                            // I wanted to make these two different, but I didn't.
                            exitFromPVPcollection2 = 1;
                            Collection.clearZombiesDeck();
                            Collection.clearPlantsDeck();

                            Account.switchAccount();
                            // in this line: main playing account = plant player

                            Collection.clearZombiesDeck();
                            Collection.clearPlantsDeck();
                            View.goingBackTo(-4); // Going back to Play Menu
                            return;
                        }
                    }
                    if (!pvp) {
                        View.goingBackTo(-4); // Going back to Play Menu
                        Collection.clearZombiesDeck();
                        Collection.clearPlantsDeck();
                    }
                    whileTrue = false;
                    break;
                case "help":
                    if (!pvp) {
                        System.out.println("--- Collection Menu ---");
                        View.printNumberedStringArrayList(instructions);
                    } else {
                        System.out.println("--- Collection Menu ---");
                        if (playTypeIndex == 1)
                            System.out.println("You are: Plant player");
                        if (playTypeIndex == 4)
                            System.out.println("You are: Zombie Player");
                        collectionMenuPvPHelp(instructions);
                        View.printNumberedStringArrayList(instructions);
                        headerPrinted = true;
                    }
                    break;
                default:
                    View.invalidCommand(-5);
                    break;
            }
        }
    }


    public static void dayMenu(int playTypeIndex, boolean pvp) {  // DayAndWater Menu index : 11
        if (aGameHasFinished == 1) {
            return;
        }

        boolean whileTrue = true;
        String command;
        Pattern plantingPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        Pattern removePattern = Pattern.compile("[r,R]emove (?<row>\\d+),(?<column>\\d+)");

        ArrayList<String> instructions = new ArrayList<>();
        if (pvp) {
            dayMenuPvPHelp(instructions);
        } else {
            setDayMenuHelp(instructions);
        }
        if (playTypeIndex == 1) {
            System.out.println("^-^-^-^- Day Menu -^-^-^-^");
        } else if (playTypeIndex == 2) {
            System.out.println("-^-^-^-^ Water Menu ^-^-^-^-");
        }
        boolean accountPrinted = true;
        if (pvp) {
            System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
            System.out.println("You are: Plant Player");
        }
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }
            if (!headerPrinted) {
                if (playTypeIndex == 1) {
                    System.out.println("^-^-^-^- Day Menu -^-^-^-^");
                } else if (playTypeIndex == 2) {
                    System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
                }
            }
            if (pvp && !accountPrinted) {
                System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
                System.out.println("You are: Plant Player");
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

                } else {
                    int row = Integer.parseInt(plantMatcher.group("row"));
                    int column = Integer.parseInt(plantMatcher.group("column"));
                    Day.plantSelectedPlant(row, column, Day.selectedPlant.getName(), false, pvp);
                }

            } else if (removeMatcher.matches()) {
                int row = Integer.parseInt(removeMatcher.group("row"));
                int column = Integer.parseInt(removeMatcher.group("column"));
                Day.removePlant(row, column);

            } else if (command.toLowerCase().equals("show hand")) {
                Day.showHandInDay();
                headerPrinted = false;
                accountPrinted = false;

            } else if (!pvp && command.toLowerCase().equals("end turn")) {
                whileTrue = false;
                headerPrinted = false;
                accountPrinted = false;
                return;

            } else if (pvp && command.toLowerCase().equals("ready")) {
                whileTrue = false;
                headerPrinted = false;
                accountPrinted = false;
                return;

            } else if (command.toLowerCase().equals("show lawn")) {
                PlayGround.showLawn();
                headerPrinted = false;
                accountPrinted = false;

            } else if (command.toLowerCase().equals("exit")) {
                boolean exit = false;
                if (!pvp) {
                    exit = View.areYouSureExitingPlay();
                } else {
                    System.out.println("Exiting at this moment will delete this game history.\nAre you sure you want to exit?");
                    exit = View.yesOrNo();
                }
                if (!pvp) {
                    if (exit) {
                        whileTrue = false;
                        Day.setWhileDayTurn(false);
                        View.goingBackTo(-5);
                        Collection.clearDecksSetCollections();
                    }
                } else {
                    if (exit) {
                        View.goingBackTo(1); // Going to login Menu
                        aGameHasFinished = 1;
                        return;
                    }
                }
                headerPrinted = false;
                accountPrinted = false;

            } else if (command.toLowerCase().equals("help")) {
                if (playTypeIndex == 1) {
                    System.out.println("^-^-^-^- Day Menu -^-^-^-^");
                } else if (playTypeIndex == 2) {
                    System.out.println("^-^-^-^- Water Menu ^-^-^-^-");
                }
                View.printNumberedStringArrayList(instructions);

            } else if (command.toLowerCase().equals("sun mikhaaam")) {
                Day.setSun(Day.getSun() + 50);

            } else if (command.toLowerCase().equals("respawn mikhaaam")) {
                Collection.decreaseRespawnsInDeck();
                Collection.decreaseRespawnsInDeck();
                Collection.decreaseRespawnsInDeck();
                Collection.decreaseRespawnsInDeck();
                Collection.decreaseRespawnsInDeck();

            } else if (command.toLowerCase().equals("ppg")) {
                PlayGround.printPlayGround();

            } else if (command.toLowerCase().equals("show sun")) {
                System.out.println(Day.getSun());

            } else {
                if (playTypeIndex == 1) {
                    View.invalidCommand(11);
                } else if (playTypeIndex == 2) {
                    View.invalidCommand(22);
                }
            }
        }
    }


    public static void zombieMenu(boolean waterGround, boolean pvp, boolean canPut) { // Zombie Menu index: 44
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        boolean whileTrue = true;
        Pattern putPattern = Pattern.compile("[p,P]ut (?<zombieName>.+),(?<number>\\d+),(?<row>\\d+)");
        Pattern giveLadderPattern = Pattern.compile("[g,G]ive ladder to (?<zombieName>.+) in unit (?<row>\\d+),(?<column>\\d+)");
        Pattern giveDuckPattern = Pattern.compile("[G,g]ive duck to (?<zombieName>.+) in unit (?<row>\\d+),(?<column>\\d+)");

        ArrayList<String> instructions = new ArrayList<>();
        if (pvp) {
            zombieMenuPvPHelp(instructions);
        } else {
            setZombieMenuHelp(instructions);
        }
        System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
        boolean accountPrinted = true;
        if (pvp) {
            System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
            System.out.println("You are: Zombie player");
        }
        View.printNumberedStringArrayList(instructions);

        boolean headerPrinted = true;

        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
            }
            if (pvp && !accountPrinted) {
                System.out.println("You are logged in as: " + Account.getMainPlayingAccount().getUsername());
                System.out.println("You are: Zombie player");
            }
            System.out.println("Enter command:");

            command = View.input();
            Matcher putMatcher = putPattern.matcher(command);
            Matcher ladderMatcher = giveLadderPattern.matcher(command);
            Matcher duckMatcher = giveDuckPattern.matcher(command);

            if (canPut && putMatcher.matches()) {
                String zombieName = putMatcher.group("zombieName");
                int number = Integer.parseInt(putMatcher.group("number"));
                int row = Integer.parseInt(putMatcher.group("row"));
                ZombieStyle.putZombieInRow(zombieName, number, row);

            } else if (!canPut && putMatcher.matches()) {
                System.out.println("You cant generate new wave until all of your play ground zombies die.");

            } else if (command.toLowerCase().equals("show hand")) {
                ZombieStyle.showHandInZombieStyle(Account.getMainPlayingAccount());
                headerPrinted = false;
                accountPrinted = false;

            } else if (command.toLowerCase().equals("show lanes")) {
                ZombieStyle.showLanes();
                headerPrinted = false;
                accountPrinted = false;

            } else if (command.toLowerCase().equals("start")) {
                ZombieStyle.popZombiesToPlayground();

            } else if (!pvp && command.toLowerCase().equals("end turn")) {
                whileTrue = false;
                headerPrinted = false;
                accountPrinted = false;
                return;

            } else if (pvp && command.toLowerCase().equals("ready")) {
                whileTrue = false;
                headerPrinted = false;
                accountPrinted = false;
                return;

            } else if (command.toLowerCase().equals("show lawn")) {
                PlayGround.showLawn();
                headerPrinted = false;
                accountPrinted = false;

            } else if (waterGround && ladderMatcher.matches()) {
                String zombieName = ladderMatcher.group("zombieName");
                int row = Integer.parseInt(ladderMatcher.group("row"));
                int column = Integer.parseInt(ladderMatcher.group("column"));
                ZombieStyle.giveLadder(zombieName, row, column);

            } else if (waterGround && duckMatcher.matches()) {
                String zombieName = duckMatcher.group("zombieName");
                int row = Integer.parseInt(duckMatcher.group("row"));
                int column = Integer.parseInt(duckMatcher.group("column"));
                ZombieStyle.readyToGiveDuck(zombieName, row, column);

            } else if (command.toLowerCase().equals("help")) {
                System.out.println("^-^-^-^ Zombie Menu ^-^-^-^");
                View.printNumberedStringArrayList(instructions);

            } else if (command.toLowerCase().equals("exit")) {
                boolean exit = View.areYouSureExitingPlay();
                if (exit) {
                    whileTrue = false;
                    if (pvp) {
                        PvP.setIsPvPTrue(false);
                    } else {
                        ZombieStyle.setWhileZombieTurn(false);
                    }
                    View.goingBackTo(-5);
                    Collection.clearDecksSetCollections();
                }
                headerPrinted = false;

            } else if (command.toLowerCase().equals("ppg")) {
                PlayGround.printPlayGround();

            } else {
                View.invalidCommand(44);

            }
        }
    }


    public static void railMenu() { // Rain Menu index: 33
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        boolean whileTrue = true;
        Pattern plantingPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        Pattern removePattern = Pattern.compile("[r,R]emove (?<row>\\d+),(?<column>\\d+)");

        ArrayList<String> instructions = new ArrayList<>();
        setRailMenuHelp(instructions);
        System.out.println("^-^-^-^ Rail Menu ^-^-^-^");
        View.printNumberedStringArrayList(instructions);
        boolean headerPrinted = true;

        while (whileTrue) {
            if (aGameHasFinished == 1) {
                return;
            }

            if (!headerPrinted) {
                System.out.println("^-^-^-^ Rail Menu ^-^-^-^");
            }
            System.out.println("Enter command");
            command = View.input();
            Matcher plantMatcher = plantingPattern.matcher(command);
            Matcher removeMatcher = removePattern.matcher(command);

            if (command.matches("[s,S]elect (\\d+)")) {
                int cardNumber = Rail.getCardNumber(command);
                Rail.selectCard(cardNumber);

            } else if (plantMatcher.matches()) {
                if (Day.selectedPlant == null) {
                    View.noPlantIsSelected();
                } else {
                    int row = Integer.parseInt(plantMatcher.group("row"));
                    int column = Integer.parseInt(plantMatcher.group("column"));
                    Day.plantSelectedPlant(row, column, Rail.selectedPlant.getName(), true, false);
                }

            } else if (removeMatcher.matches()) {
                int row = Integer.parseInt(removeMatcher.group("row"));
                int column = Integer.parseInt(removeMatcher.group("column"));
                Day.removePlant(row, column);

            } else if (command.toLowerCase().equals("list")) {
                View.printNumberedPlantArrayList(Rail.railDeck);

            } else if (command.toLowerCase().equals("record")) {
                System.out.print("Killed zombies:");
                System.out.println(Account.getMainPlayingAccount().getKilledZombies());

            } else if (command.toLowerCase().equals("show lawn")) {
                PlayGround.showLawn();
                headerPrinted = false;

            } else if (command.toLowerCase().equals("end turn")) {
                whileTrue = false;

            } else if (command.toLowerCase().equals("exit")) {
                boolean exit = View.areYouSureExitingPlay();
                if (exit) {
                    whileTrue = false;
                    Rail.setWhileRailTurn(false);
                    View.goingBackTo(-5);
                    Collection.clearDecksSetCollections();
                    Rail.clearRailDeck();
                }
                headerPrinted = false;

            } else if (command.toLowerCase().equals("help")) {
                System.out.println("^-^-^-^ Rail Menu ^-^-^-^");
                View.printNumberedStringArrayList(instructions);
                headerPrinted = true;
            } else if (command.toLowerCase().equals("ppg")) {
                PlayGround.printPlayGround();
            } else {
                View.invalidCommand(33);
            }

        }


    }


    public static void pvpMenu() {
        if (aGameHasFinished == 1) {
            return;
        }

        String command;
        boolean whileTrue = true;
        System.out.println("^-^-^-^ PvP Menu ^-^-^-^");
        ArrayList<String> instructions = new ArrayList<>();
        // temporary:
        while (whileTrue) {
            Account.loginForPvPZombiePlayer(Account.getSecondPlayingAccount());
        }


    }


    private static void setLoginMenuHelp(ArrayList<String> instructions) {
        instructions.add("create account");
        instructions.add("login");
        instructions.add("leaderboard");
        instructions.add("exit");
        instructions.add("help");
    }

    private static void setMainMenuHelp(ArrayList<String> instructions) {
        instructions.add("play");
        instructions.add("profile");
        instructions.add("shop");
        instructions.add("exit");
        instructions.add("help");
    }

    private static void setProfileMenuHelp(ArrayList<String> instructions) {
        instructions.add("change account");
        instructions.add("change password");
        instructions.add("change username");
        instructions.add("delete account");
        instructions.add("create account");
        instructions.add("show account");
        instructions.add("exit");
        instructions.add("help");
    }

    private static void setGoPlayMenuHelp(ArrayList<String> instructions) {
        instructions.add("day");
        instructions.add("water");
        instructions.add("rail");
        instructions.add("zombie");
        instructions.add("pvp");
        instructions.add("exit");
        instructions.add("help");
    }

    private static void setShopMenuHelp(ArrayList<String> instructions) {
        instructions.add("show shop");
        instructions.add("show collection");
        instructions.add("show money");
        instructions.add("buy [card name]");
        instructions.add("help");
        instructions.add("exit");
    }

    private static void setCollectionMenuHelp(ArrayList<String> instructions) {
        instructions.add("select [card name]");
        instructions.add("remove [card name]");
        instructions.add("show collection");
        instructions.add("show hand");
        instructions.add("play");
        instructions.add("exit");
        instructions.add("help");
    }

    private static void collectionMenuPvPHelp(ArrayList<String> instructions) {
        instructions.clear();
        instructions.add("select [card name]");
        instructions.add("remove [card name]");
        instructions.add("show collection");
        instructions.add("show hand");
        instructions.add("done");
    }

    private static void setDayMenuHelp(ArrayList<String> instructions) {
        instructions.add("select [card name]");
        instructions.add("plant [row],[column]");
        instructions.add("remove [row],[column]");
        instructions.add("show hand");
        instructions.add("show lawn");
        instructions.add("end turn");
        instructions.add("help");
        instructions.add("exit");
    }

    private static void setZombieMenuHelp(ArrayList<String> instructions) {
        instructions.add("put [zombie name],[row]");
        instructions.add("start");
        instructions.add("show hand");
        instructions.add("show lanes");
        instructions.add("show lawn");
        instructions.add("end turn");
        instructions.add("help");
        instructions.add("exit");
    }

    private static void dayMenuPvPHelp(ArrayList<String> instructions) {
        instructions.clear();
        instructions.add("select [card name]");
        instructions.add("plant [row],[column]");
        instructions.add("remove [row],[column]");
        instructions.add("show hand");
        instructions.add("show lawn");
        instructions.add("ready");
        instructions.add("help");
        instructions.add("exit");
    }

    private static void zombieMenuPvPHelp(ArrayList<String> instructions) {
        instructions.clear();
        instructions.add("put [zombie name],[row]");
        instructions.add("start");
        instructions.add("show hand");
        instructions.add("show lanes");
        instructions.add("show lawn");
        instructions.add("end turn");
        instructions.add("help");
        instructions.add("exit");
    }

    private static void setRailMenuHelp(ArrayList<String> instructions) {
        instructions.add("list");
        instructions.add("select [card number]");
        instructions.add("record");
        instructions.add("plant [row],[column]");
        instructions.add("remove [row],[column]");
        instructions.add("show lawn");
        instructions.add("end turn");
    }

}
