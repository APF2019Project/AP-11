import java.util.ArrayList;

public class View {

    public static void invalidCommand(int index) {
        switch (index) {
            case -1:  // Login Menu
                System.out.println("Invalid command in Login Menu, Try again:");
                break;
            case -2:  // Main Menu
                System.out.println("Invalid command in Main Menu, Try again:");
                break;
            case -10:  // Shop Menu
                System.out.println("Invalid command in Shop Menu, Try again:");
                break;
            case -3:  // Profile Menu
                System.out.println("Invalid command in Profile Menu, Try again:");
                break;
            case -4:  // Play Menu
                System.out.println("Invalid command in Play Menu, Try again:");
                break;
            case -5:  // Collection Menu
                System.out.println("Invalid command in Collection Menu, Try again:");
                break;
            case 11:  // DayAndWater Menu
                System.out.println("Invalid command in Day or Water Menu, Try again:");
                break;
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
            default:
                indexError();
                break;
        }
    }

    //The destination Menu index is passed
    public static void goingBackTo(int index) {
        switch (index) {
            case -1:  // Login Menu
                System.out.println("Going back to --> Login Menu:");
                break;
            case -2:  // Main Menu
                System.out.println("Going back to --> Main Menu:");
                break;
            case -3:  //  Profile Menu
                System.out.println("Going back to --> Profile Menu:");
                break;
            case -10:  // Shop Menu
                System.out.println("Going back to --> Shop Menu");
                break;
            case -100: // Exit The Program (Exit command in Login Menu)
                System.out.println("___^^___ END GAME ___^^___");
                break;
            case -4:  // Play Menu
                System.out.println("Going back to --> Play Menu:");
                break;
            case -5:  // Collection Menu
                System.out.println("Going back to --> Collection Menu:");
                break;
            default:
                indexError();
                break;
        }
    }

    public static void invalidCardName() {
        System.out.println("Invalid card name");
    }

    public static void plantPurchased(String cardName) {
        System.out.println(cardName + " added to your plants collection.");
    }

    public static void zombiePurchased(String cardName) {
        System.out.println(cardName + " added to your zombies collection.");
    }

    public static void notEnoughMoney() {
        System.out.println("Not enough money!");
    }

    public static void cardRemovedFromDeck(String cardName) {
        System.out.println(cardName + " removed from deck.");
    }

    public static void notEnoughSpacePlantDeck() {
        System.out.println("You already have 7 plants in your deck.");
    }

    public static void cardAlreadyExistsInDeck(String deckType, String cardName) {
        System.out.println(cardName + " already Exists in your " + deckType + " deck.");
    }

    public static void cardNotInCollection(String collectionType, String cardName) {
        System.out.println(cardName + " is not in your " + collectionType + " collection.");
    }

    public static void cardAddedToDeck(String deckType, String cardName) {
        System.out.println(cardName + " added to your " + deckType + " deck.");
    }

    public static void cardAlreadyExistsInCollection(String collectionType, String cardName) {
        System.out.println(cardName + " is already in your " + collectionType + " collection.");
    }


    public static void showPlantsDeck() {
        System.out.println("Your plants deck:");
        int i = 1;
        for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
            System.out.print(i + ". ");
            System.out.println(plantIterator.getName());
            i++;
        }
    }

    public static void showZombiesDeck() {
        System.out.println("Your zombies deck:");
        int i = 1;
        for (Zombie zombieIterator : Account.getPlayingAccount().zombiesDeck) {
            System.out.print(i + ". ");
            System.out.println(zombieIterator.getName());
            i++;
        }
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
    }

    public static void printNumberedZombieArrayList(ArrayList<Zombie> arrayList) {
        int i = 1;
        for (Zombie zombieIterator : arrayList) {
            System.out.print(i + ". ");
            System.out.println(zombieIterator.getName());
            i++;
        }
    }

    public static void cardNotInDeck(String deckType, String cardName) {
        System.out.println(cardName + " is not in your " + deckType + " deck.");
    }

    public static void cardSelected(String cardName) {
        System.out.println(cardName + " is selected.");
    }

    public static void plantedInUnit(int row, int column, String plantName) {
        System.out.println(plantName + " planted in unit " + row + "," + column);
    }

    public static void printCollectionInShop(ArrayList<Plant> plantsShop, ArrayList<Zombie> zombiesShop) {
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
    }

    public static void unitIsFilled(int row, int column, Unit unit) {
        System.out.println("Unit " + row + "," + column + " is already filled with a " + unit.getPlants()[0].getName());
    }

    public static void waterPlantInLand() {
        System.out.println("This plant should be planted in water.");
    }

    public static void landPlantInWater() {
        System.out.println("This plant should be planted in land or on a Lily Pad.");
    }

    public static void landPlantOnLilyPad() {
        System.out.println("You can only plant land plants on a Lily Pad");
    }

    public static void notEnoughSunOrCharge() {
        System.out.println("You don't have enough sun or this plant is not charged.");
    }

    public static void noPlantIsSelected() {
        System.out.println("No plant is selected.");
    }

    private static void loginMenuHelp() {
        System.out.println("-- Login Menu Help --");
        System.out.println("Login Menu commands are:");
        System.out.println("Create account, Login, Leaderboard, Exit and Help");
    }

    private static void mainMenuHelp() {
        System.out.println("-- Main Menu Help --");
        System.out.println("Main Menu commands are:");
        System.out.println("Play, Profile, Shop, Exit and Help");
    }

    private static void shopMenuHelp() {
        System.out.println("Shop Menu commands are:\nshow shop, show collection, money, buy [card name], help");
    }

    private static void profileMenuHelp() {
        System.out.println("-- Profile Menu Help --");
        System.out.println("Profile Menu commands are:");
        System.out.println("Change (logging in with another account)");
        System.out.println("Delete account, Change password, Rename (change your username)");
        System.out.println("Create account (and login with that), Show (show your current logged in account)");
        System.out.println("Exit and Help");
    }

    private static void playMenuHelp() {
        System.out.println("-- Play Menu Help --");
        System.out.println("Play Menu commands are:");
        System.out.println("Day, Water, Rail, Zombie, PvP, Exit and Help");
    }

    private static void collectionMenuHelp() {
        System.out.println("-- Collection Menu Help --");
        System.out.println("Collection Menu commands are:");
        System.out.println("show hand, show collection, select[name], remove[name], play, help\nand exit (going back" +
                "to Play Menu)");
    }

    private static void DayAndWaterMenuHelp() {
        System.out.println("-- Day or Water help --");
        System.out.println("commands are:");
        System.out.println("select [plant name], plant #,# (plant selected plant)");
        System.out.println("remove #,# (remove plant in unit #,#), show hand, show lawn");
        System.out.println("ent turn, exit and help");
    }

    private static void indexError() {
        System.out.println("$$$ index Error!!! (View Class) $$$");
    }

}
