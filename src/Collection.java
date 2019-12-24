import java.util.Scanner;

public class Collection {

    static void collectionMenu(int playTypeIndex) { // Collection Menu index: -5

        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Collection Menu ---\nEnter command:");
            command = scanner.nextLine();

            if (command.toLowerCase().matches("select (.+)")) {
                readyToSelect(command, playTypeIndex);
                continue;
            } else if (command.toLowerCase().matches("remove (.+)")) {
                readyToRemove(command, playTypeIndex);
                continue;
            }
            switch (command.toLowerCase()) {
                case "show hand":
                    showHand(playTypeIndex);
                    break;
                case "show collection":
                    Shop.showCollection(Account.getPlayingAccount());
                    break;
                case "play":
                    goToPlayByPlayType(playTypeIndex);
                    break;
                case "exit": // Going back to Play Menu
                    View.goingBackTo(-4);
                    whileTrue = false;
                    break;
                case "help":
                    //
                    break;
                default:
                    //
                    break;
            }
        }
    }

    private static void readyToSelect(String command, int playTypeIndex) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        selectCard(command, playTypeIndex);
    }

    private static void readyToRemove(String command, int playTypeIndex) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        removeCard(command, playTypeIndex);
    }

    private static void selectCard(String cardName, int playCardIndex) {
        //
    }

    private static void removeCard(String cardName, int playTypeIndex) {
        //
    }

    private static void showHand(int playTypeIndex) {
        //
    }

    public static void goToPlayByPlayType(int playTypeIndex) {
        switch (playTypeIndex) {
            case 1:
                //
                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
        }

    }


}
