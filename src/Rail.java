import java.util.ArrayList;
import java.util.regex.Matcher;

public class Rail extends Play {

    public static ArrayList<Plant> railDeck = new ArrayList<>();
    private static int turn = 0;
    private static boolean addPlantInNextTurn = false;
    private static int lastAddedTurn = 1000;
    private static int nextAddingPlantTurn = 0;


    public static void railTurn() {
        turn++;
        checkAddingPlantToDeck();
        Menu.railMenu();
    }


    public static Plant generateRandomPlant() {
        Plant plant = null;
        int randomPlantIndex = (int) (Math.random() * 24);
        plant = new Plant(Plant.getPlants().get(randomPlantIndex));
        return plant;
    }

    private static Plant getNonWaterPlant() {
        Plant plant = generateRandomPlant();
        if (!plant.isCanBePlantedInWater())
            return plant;
        else
            return getNonWaterPlant();
    }

    public static void addRandomPlantToRailDeck() {
        if (railDeck.size() < 10)
            railDeck.add(getNonWaterPlant());
    }

    private static int generateTwoThreeFour() {
        return 2 + ((int) (Math.random() * 2.99));
    }

    private static void checkAddingPlantToDeck() {
        if (turn == nextAddingPlantTurn) {
            addRandomPlantToRailDeck();
            nextAddingPlantTurn = turn + generateTwoThreeFour();
        }
    }


    public static void setDefaultRailDeck() {
        railDeck.addAll(Account.getPlayingAccount().getPlantsCollection());
    }

    public static int getCardNumber(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return Integer.parseInt(command);
    }

    public static void selectCard(int cardNumber) {
        int indexInDeck = cardNumber - 1;
        if (indexInDeck > railDeck.size() - 1) {
            View.invalidCardNumber();
            return;
        }
        selectedPlant = railDeck.get(indexInDeck);
        View.cardSelected(Integer.toString(cardNumber));
    }


    public static void plantIn0 (Unit unit, Plant plant, int row, int column, String plantName) {
        unit.setPlant0(plant);
        selectedPlant = null;
        View.plantedInUnit(row, column, plantName);
    }

}
