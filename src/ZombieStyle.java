import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZombieStyle extends Play {

        private static int coins = 50;


        public static void zombieTurn() {


            zombieMenu();
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
