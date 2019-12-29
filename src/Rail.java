import java.util.ArrayList;

public class Rail extends Play {

    public static ArrayList<Plant> railPlants = new ArrayList<>();

    public static void railTurn() {


        Menu.railMenu();
    }

    public static int getCardNumber(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return Integer.parseInt(command);
    }

}
