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

    public static void cardRemovedFromDeck (String cardName) {
        System.out.println(cardName + " removed from deck.");
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
        System.out.println("show hand, show collection, select[name], remove[name], play and help.");
    }

    private static void indexError() {
        System.out.println("$$$ index Error!!! (View Class) $$$");
    }

}
