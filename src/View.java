public class View {

    public static void invalidCommand(int index) {
        if (index == -10) { // Shop Menu
            System.out.println("Invalid command in Shop Menu, Try again:");
        } else if (index == -1) {
            //
        }
    }

    public static void showHelp(int index) {
        if (index == -10) {// Shop Menu
            System.out.println("Shop Menu commands are:\nshow shop, show collection, money, buy [card name], help");
        } else if (index == -1) {
            //
        }
    }

    //The destination Menu index is passed
    public static void goingBackTo(int index) {
        if (index == -10) {
            System.out.println();
        }
    }
}
