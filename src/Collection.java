import java.util.Scanner;

public class Collection {

    static void collectionMenu() {

        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while(whileTrue) {
            System.out.println("--- Collection Menu ---\nEnter command:");
            command = scanner.nextLine();

            if (command.toLowerCase().matches("select (.+)")) {
                readyToSelect(command);
                continue;
            } else if (command.toLowerCase().matches("remove (.+)")) {
                readyToRemove(command);
                continue;
            }
            switch (command.toLowerCase()) {
                case "show hand":
                    //
                    break;
                case "show collection":
                    //
                    break;
                case "play":
                    //
                    break;
                case "exit":
                    //
                    break;
                default:
                    //
                    break;
            }
        }
    }

    private static void readyToSelect(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        selectCard(command);
    }

    private static void readyToRemove(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        removeCard(command);
    }

    private static void selectCard(String cardName) {
        //
    }

    private static void removeCard(String cardName) {
        //
    }






}
