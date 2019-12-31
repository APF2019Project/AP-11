import java.util.ArrayList;
import java.util.regex.Matcher;

public class Rail extends Play {

    public static ArrayList<Plant> railDeck = new ArrayList<>();
    private static int turn = 0;
    private static boolean addPlantInNextTurn = false;
    private static int lastAddedTurn = 1000;


    public static Plant generateRandomPlant() {
        Plant plant = null;
        int ranNum = (int) (Math.random() * 24);
        int i = 0;
        for (Plant plantIterator : Plant.getPlants()) {
            if (i < ranNum || plantIterator.isCanBePlantedInWater()) {
                i++;
                continue;
            }

            plant = new Plant(plantIterator);
            break;
        }
        return plant;
    }

    public static void addRandomPlantToRailDeck() {

        if (railDeck.size() < 10) {
            railDeck.add(generateRandomPlant());
        }
    }

    private static int generateTwoThreeFour() {
        return ((int) (Math.random() * 4.99));
    }

    public static void railTurn() {



        Menu.railMenu();
    }

    public static int getCardNumber(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return Integer.parseInt(command);
    }

}
