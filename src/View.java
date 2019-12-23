public class View {

    public static void invalidCommand(int index) {
        if (index == -1) { // Login Menu
            System.out.println();

        } else if (index == -2) { // Main Menu
            System.out.println();

        } else if (index == -10) { // Shop Menu
            System.out.println("Invalid command in Shop Menu, Try again:");

        } else if (index == -3) { //
            System.out.println();
        }
    }

    public static void showHelp(int index) {
        if (index == -1) { // Shop Menu
            System.out.println();
        } else if (index == -10) {
            System.out.println("Shop Menu commands are:\nshow shop, show collection, money, buy [card name], help");
        }
    }

    //The destination Menu index is passed
    public static void goingBackTo(int index) {
        if (index == -1) { // Login Menu
            System.out.println();
        } else if (index == -2) { // Main Menu
            System.out.println("Going back to --> Main Menu:");
        } else if (index == -10) { // Shop Menu
            System.out.println("Going back to --> Shop Menu");
        }
    }
}
