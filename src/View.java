import javax.swing.event.InternalFrameEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class View {

    public static Scanner scanner = new Scanner(System.in);


    public static String input() {
        String command = scanner.nextLine();
        return command;
    }

    public static void startHeader() {
        System.out.println("T   A V   L     I");
        System.out.println(" T   A V L       I");
        System.out.println("  T   A VL         I");
        System.out.println("   T   ALV       Z  I");
        System.out.println("P   T  LA V      Z   I");
        System.out.println("P    TL  A V  s  Z  B I");
        System.out.println("P    LT  A  V s  Z M B I");
        System.out.println("P   L  TA    Vs  Z O M B I");
        System.out.println("P  L   AT    Vs  Z  O   B I ");
        System.out.println("P L   A T    Vs  Z  O   B I E ");
        System.out.println("P L  AN T S  Vs  Z  O   B I E S");
        System.out.println("P L A N T S  Vs  Z O   B I E S");
        System.out.println("P L A N T S  Vs  Z O M B I E S");
        System.out.println("     A    T         O M      E");
        System.out.println("P  L  A  N  T  S     O  M      E ");
        System.out.println("                 Vs   O  M       E");
        System.out.println("                    Z  O  M  B  I  E  S");
        System.out.println();
    }

    public static void printNumberedStringArrayList(ArrayList<String> arrayList) {
        int i = 1;
        for (String string : arrayList) {
            System.out.print(i + ". ");
            System.out.println(string);
            i++;
        }
    }

    public static void invalidCommand(int index) {
        switch (index) {
            case -1:  // Login Menu
                System.out.println("Invalid command in Login Menu, Try again:\n");
                break;
            case -2:  // Main Menu
                System.out.println("Invalid command in Main Menu, Try again:\n");
                break;
            case -10:  // Shop Menu
                System.out.println("Invalid command in Shop Menu, Try again:\n");
                break;
            case -3:  // Profile Menu
                System.out.println("Invalid command in Profile Menu, Try again:\n");
                break;
            case -4:  // Play Menu
                System.out.println("Invalid command in Play Menu, Try again:\n");
                break;
            case -5:  // Collection Menu
                System.out.println("Invalid command in Collection Menu, Try again:\n");
                break;
            case 11:  // DayAndWater Menu
                System.out.println("Invalid command in Day Menu, Try again:\n");
                break;
            case 22:
                System.out.println("Invalid command in Water Menu, Try again:\n");
                break;
            case 44:
                System.out.println("Invalid command in Zombie Menu, Try again:\n");
            default:
                indexError();
                break;
        }
    }

    public static void showHelp(int index) {
        switch (index) {
            case -1:  // Login Menu
                loginMenuHelp();
                break;
            case -2:  // Main Menu
                mainMenuHelp();
                break;
            case -3:  // Profile Menu
                profileMenuHelp();
                break;
            case -10:  //  Shop Menu
                shopMenuHelp();
                break;
            case -4:  // Play Menu
                playMenuHelp();
                break;
            case -5:  // Collection Menu
                collectionMenuHelp();
                break;
            case 11:  // DayAndWater Menu
                DayAndWaterMenuHelp();
                break;
            case 22:
                waterMenuHelp();
            default:
                indexError();
                break;
        }
    }

    //The destination Menu index is passed
    public static void goingBackTo(int index) {
        switch (index) {
            case -1:  // Login Menu
                System.out.println("Going back to --> Login Menu:\n");
                break;
            case -2:  // Main Menu
                System.out.println("Going back to --> Main Menu:\n");
                break;
            case -3:  //  Profile Menu
                System.out.println("Going back to --> Profile Menu:\n");
                break;
            case -10:  // Shop Menu
                System.out.println("Going back to --> Shop Menu\n");
                break;
            case -100: // Exit The Program (Exit command in Login Menu)
                System.out.println("___^^___ END GAME ___^^___");
                break;
            case -4:  // Play Menu
                System.out.println("Going back to --> Play Menu:\n");
                break;
            case -5:  // Collection Menu
                System.out.println("Going back to --> Collection Menu:\n");
                break;
            default:
                indexError();
                break;
        }
    }

    public static void invalidCardName() {
        System.out.println("Invalid card name");
        System.out.println();
    }

    public static void plantPurchased(String cardName) {
        System.out.println(cardName + " added to your plants collection.");
        System.out.println();
    }

    public static void zombiePurchased(String cardName) {
        System.out.println(cardName + " added to your zombies collection.");
        System.out.println();
    }

    public static void notEnoughMoney() {
        System.out.println("Not enough money!");
        System.out.println();
    }

    public static void cardRemovedFromDeck(String cardName) {
        System.out.println(cardName + " removed from deck.");
        System.out.println();
    }

    public static void notEnoughSpacePlantDeck() {
        System.out.println("You already have 7 plants in your deck.");
        System.out.println();
    }

    public static void cardAlreadyExistsInDeck(String deckType, String cardName) {
        System.out.println(cardName + " already Exists in your " + deckType + " deck.");
        System.out.println();
    }

    public static void cardNotInCollection(String collectionType, String cardName) {
        System.out.println(cardName + " is not in your " + collectionType + " collection.");
        System.out.println();
    }

    public static void cardAddedToDeck(String deckType, String cardName) {
        System.out.println(cardName + " added to your " + deckType + " deck.");
        System.out.println();
    }

    public static void cardAlreadyExistsInCollection(String collectionType, String cardName) {
        System.out.println(cardName + " is already in your " + collectionType + " collection.");
        System.out.println();
    }


    public static void showPlantsDeck() {
        if(Account.getPlayingAccount().plantsDeck.size() == 0) {
            System.out.println("Your plants deck is empty, select plants from your collection.");
            System.out.println();
            return;
        }
        System.out.println("Your plants deck:");
        int i = 1;
        for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
            System.out.print(i + ". ");
            System.out.println(plantIterator.getName());
            i++;
        }
        System.out.println();
    }

    public static void showZombiesDeck() {
        System.out.println("Your zombies deck:");
        int i = 1;
        for (Zombie zombieIterator : Account.getPlayingAccount().zombiesDeck) {
            System.out.print(i + ". ");
            System.out.println(zombieIterator.getName());
            i++;
        }
        System.out.println();
    }

    public static void printCardName(String cardName) {
        System.out.println(cardName);
    }

    public static void printNumberedPlantArrayList(ArrayList<Plant> arrayList) {
        int i = 1;
        for (Plant plantIterator : arrayList) {
            System.out.print(i + ". ");
            System.out.println(plantIterator.getName());
            i++;
        }
        System.out.println();
    }

    public static void printNumberedZombieArrayList(ArrayList<Zombie> arrayList) {
        int i = 1;
        for (Zombie zombieIterator : arrayList) {
            System.out.print(i + ". ");
            System.out.println(zombieIterator.getName());
            i++;
        }
        System.out.println();
    }

    public static void cardNotInDeck(String deckType, String cardName) {
        System.out.println(cardName + " is not in your " + deckType + " deck.");
        System.out.println();
    }

    public static void cardSelected(String cardName) {
        System.out.println(cardName + " is selected.");
        System.out.println();
    }

    public static void plantedInUnit(int row, int column, String plantName) {
        System.out.println(plantName + " planted in unit " + row + "," + column);
        System.out.println();
    }

    public static void printShopWithPrices(ArrayList<Plant> plantsShop, ArrayList<Zombie> zombiesShop) {
        System.out.println("Plants:" + "            " + "Price:" + "        " + "Zombies:" + "                " + "Price:");
        int i = 0;
        int j = 0;
        String spaces;
        int spacesNumber;
        int spaceNumbers2;
        while (i < plantsShop.size() || j < zombiesShop.size()) {
            if (i < plantsShop.size()) {
                spacesNumber = 20 - (plantsShop.get(i).getName().length());
                System.out.print(i + 1 + ". ");
                System.out.print(plantsShop.get(i).getName());
                if (i + 1 > 9) {
                    spacesNumber--;
                }
                for (int k = 1; k <= spacesNumber; k++) {
                    System.out.print(" ");
                }
                System.out.print(plantsShop.get(i).getPrice());
                spaceNumbers2 = 10 - Integer.toString(plantsShop.get(i).getPrice()).length();
                for (int m = 1; m <= spaceNumbers2; m++) {
                    System.out.print(" ");
                }
            } else {
                for (int k = 1; k <= 25; k++) {
                    System.out.print(" ");
                }
            }
            if (j < zombiesShop.size()) {
                System.out.print(j + 1 + ". ");
                System.out.print(zombiesShop.get(i).getName());
                spacesNumber = 25 - zombiesShop.get(i).getName().length();
                for (int k = 1; k <= spacesNumber; k++) {
                    System.out.print(" ");
                }
                System.out.println(zombiesShop.get(i).getPrice());

            } else
                System.out.println();
            i++;
            j++;
        }
        System.out.println();
    }



    public static void printCollections(ArrayList<Plant> plantsShop, ArrayList<Zombie> zombiesShop) {
        System.out.println("Plants:" + "                " + "Zombies:");
        int i = 0;
        int j = 0;
        String spaces;
        int spacesNumber;
        int spaceNumbers2;
        while (i < plantsShop.size() || j < zombiesShop.size()) {
            if (i < plantsShop.size()) {
                spacesNumber = 20 - (plantsShop.get(i).getName().length());
                System.out.print(i + 1 + ". ");
                System.out.print(plantsShop.get(i).getName());
                if (i + 1 > 9) {
                    spacesNumber--;
                }
                for (int k = 1; k <= spacesNumber; k++) {
                    System.out.print(" ");
                }
            } else {
                for (int k = 1; k <= 25; k++) {
                    System.out.print(" ");
                }
            }
            if (j < zombiesShop.size()) {
                System.out.print(j + 1 + ". ");
                System.out.println(zombiesShop.get(i).getName());
            } else
                System.out.println();
            i++;
            j++;
        }
        System.out.println();
    }


    public static void showLawnZombies(ArrayList<Zombie> arrayList, int row, int column) {
        int spacesNumber;
        int spacesNumber2;
        for (Zombie zombieIterator : arrayList) {
            spacesNumber = 25 - zombieIterator.getName().length();
            System.out.print(zombieIterator.getName());
            for (int i = 1; i <= spacesNumber; i++) {
                System.out.print(" ");
            }
            System.out.print(row + "," + column);
            spacesNumber2 = 8 - Integer.toString(row).length() - Integer.toString(column).length() - 1;
            for (int i = 1; i <= spacesNumber2; i++) {
                System.out.print(" ");
            }
            System.out.print(zombieIterator.getHealth());
            for (int i = 1; i <= 10 - Integer.toString(zombieIterator.getHealth()).length(); i++){
                System.out.print(" ");
            }
            System.out.println("shield: " + zombieIterator.getShieldStrength());
        }
    }

    public static void showLawnPlants(Plant plant, int row, int column) {
        int spacesNumber;
        int spacesNumber2;
        spacesNumber = 20 - plant.getName().length();
        System.out.print(plant.getName());
        for (int i = 1; i <= spacesNumber; i++) {
            System.out.print(" ");
        }
        System.out.print(row + "," + column);
        spacesNumber2 = 8 - Integer.toString(row).length() - Integer.toString(column).length() - 1;
        for (int i = 1; i <= spacesNumber2; i++) {
            System.out.print(" ");
        }
        System.out.println(plant.getHealth());

    }

    public static void showLawnShoots() {
        System.out.println("All the shoots in the play ground are: ");
        Unit[][] playGround = PlayGround.getUnits();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                for (Shoot shootIterator : playGround[i][j].getShoots()) {
                    System.out.println("There is a shoot in: " + i + "," + j + " with direction: " + shootIterator.getDirection()
                            + " with speed: " + shootIterator.getSpeed() + " with affective time: " + shootIterator.getEffectiveTime());
                }
            }
        }
    }


    public static void unitIsFilled(int row, int column, Unit unit, int plantIndex) {
        System.out.println("Unit " + row + "," + column + " is already filled with a " + unit.getPlants()[plantIndex].getName());
        System.out.println();
    }

    public static void alreadyALilyPadHere(int row, int column) {
        System.out.println("There is already a Lily Pad in unit " + row + "," + column);
    }

    public static void waterPlantInLand() {
        System.out.println("This plant should be planted in water.");
        System.out.println();
    }

    public static void landPlantInWater() {
        System.out.println("This plant should be planted in land or on a Lily Pad.");
        System.out.println();
    }

    public static void landPlantOnLilyPad() {
        System.out.println("You can only plant land plants on a Lily Pad");
        System.out.println();
    }

    public static void notEnoughSunOrCharge() {
        System.out.println("You don't have enough sun or this plant is not charged.");
        System.out.println();
    }

    public static void noPlantIsSelected() {
        System.out.println("No plant is selected.");
        System.out.println();
    }

    public static boolean areYouSureExitingPlay() {
        System.out.println("Are you sure you want to exit the play?");
        String sure = scanner.nextLine();
        if (sure.toLowerCase().equals("yes")) {
            return true;
        } else if (sure.toLowerCase().equals("no")) {
            return false;
        } else {
            System.out.println("Invalid answer. Try exiting again:");
            System.out.println();
        }
        return false;
    }

    public static void invalidCoordinates() {
        System.out.println("Invalid coordinates!");
        System.out.println();
    }

    public static void plantRemoved(int row, int column, String plantName) {
        System.out.println("The " + plantName + " in the unit " + row + "," + column + " has been removed.");
        System.out.println();
    }

    public static void unitIsEmpty(int row, int column) {
        System.out.println("The unit " + row + "," + column + " is already empty.");
        System.out.println();
    }

    public static void youLost() {
        System.out.println("YOU LOST..!!!!");
        System.out.println("ZOMBIES are EATING you......");
    }

    public static void youWon() {
        System.out.println("YOU WON..!!!");
        System.out.println("You DESTROYED all of the FUCKIN Zombies!");
    }

    public static void invalidCardNumber() {
        System.out.println("Invalid card number. Try again:");
    }

    public static int choosePlayGroundInZombieStyle() {
        System.out.println("choose your play ground (land or water):");
        String playGround = scanner.nextLine();
        if (playGround.toLowerCase().equals("land")) {
            return 1;
        } else if (playGround.toLowerCase().equals("water")) {
            return 2;
        } else {
            System.out.println("Invalid answer, going back to Collection Menu:");
            System.out.println();
            return 3;
        }
    }
    private static void loginMenuHelp() {
        System.out.println("-- Login Menu Help --");
        System.out.println("Login Menu commands are:");
        System.out.println("Create account, Login, Leaderboard, Exit and Help");
        System.out.println();
    }

    private static void mainMenuHelp() {
        System.out.println("-- Main Menu Help --");
        System.out.println("Main Menu commands are:");
        System.out.println("Play, Profile, Shop, Exit and Help");
        System.out.println();
    }

    private static void shopMenuHelp() {
        System.out.println("Shop Menu commands are:\nshow shop, show collection, money, buy [card name], help");
        System.out.println();
    }

    private static void profileMenuHelp() {
        System.out.println("-- Profile Menu Help --");
        System.out.println("Profile Menu commands are:");
        System.out.println("Change (logging in with another account)");
        System.out.println("Delete account, Change password, Rename (change your username)");
        System.out.println("Create account (and login with that), Show (show your current logged in account)");
        System.out.println("Exit and Help");
        System.out.println();
    }

    private static void playMenuHelp() {
        System.out.println("-- Play Menu Help --");
        System.out.println("Play Menu commands are:");
        System.out.println("Day, Water, Rail, Zombie, PvP, Exit and Help");
        System.out.println();
    }

    private static void collectionMenuHelp() {
        System.out.println("-- Collection Menu Help --");
        System.out.println("Collection Menu commands are:");
        System.out.println("show hand, show collection, select[name], remove[name], play, help\nand exit (going back" +
                "to Play Menu)");
        System.out.println();
    }

    private static void DayAndWaterMenuHelp() {
        System.out.println("-- Day Menu help --");
        System.out.println("commands are:");
        System.out.println("select [plant name], plant #,# (plant selected plant)");
        System.out.println("remove #,# (remove plant in unit #,#), show hand, show lawn");
        System.out.println("ent turn, exit and help");
        System.out.println();
    }

    private static void waterMenuHelp() {
        System.out.println("-- Water Menu help --");
        System.out.println("commands are:");
        System.out.println("select [plant name], plant #,# (plant selected plant)");
        System.out.println("remove #,# (remove plant in unit #,#), show hand, show lawn");
        System.out.println("end turn, exit and help");
        System.out.println();
    }

    private static void indexError() {
        System.out.println("$$$ index Error!!! (View Class) $$$");
        System.out.println();
    }

    public static void unavailableCoordinates() {
        System.out.println("unavailable coordinates!");
        System.out.println();
    }

    public static void printWinnerOfTheGame(Account account) {
        System.out.println(account.getUsername() + " is the winner");
    }
}
