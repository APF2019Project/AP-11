import java.util.ArrayList;
import java.util.regex.Matcher;

public class Rail extends Play {

    public static ArrayList<Plant> railDeck = new ArrayList<>();
    private static int turn = 0;
    private static int nextAddingZombieTurn = 1;
    private static int nextAddingPlantTurn = 1;


    private static boolean whileRailTurn = true;

    public static void setWhileRailTurn(boolean whileRailTurn) {
        Rail.whileRailTurn = whileRailTurn;
    }

    public static void railTurn() {
        startTheRailPlay();
        while(whileRailTurn) {
            turn++;
            System.out.println("$$$$ turn: " + turn);
            if (railCheckLoose()) {
                doFinalThingsRail();
                break;
            }
            Shoot.shootTurn();
            if (railCheckLoose()) {
                doFinalThingsRail();
                break;
            }
            Zombie.zombiesTurn();
            if (railCheckLoose()) {
                doFinalThingsRail();
                break;
            }
            Plant.plantsTurn();
            checkAddingPlantToDeck();
            addZombie();
            Menu.railMenu();
        }
    }

    private static void addZombie() {
        if (turn == nextAddingZombieTurn) {
            addRandomZombie(getRandomZombie());
            nextAddingZombieTurn = turn + generateTwoThreeFour() + 1;
        }
    }

    private static Zombie getRandomZombie() {
        Zombie zombie = null;
        int randomZombieNumber = (int) (Math.random() * 15.9);
        zombie = Zombie.cloningZombie(Zombie.getZombies().get(randomZombieNumber));
        if(!zombie.isWaterProof())
            return zombie;
        else
            return getRandomZombie();
    }

    private static Unit getRandomUnitColumn19() {
        int randomRow = (int) (Math.random() * 5.99);
        return PlayGround.getSpecifiedUnit(randomRow, 19);
    }

    private static void addRandomZombie(Zombie zombie) {
        getRandomUnitColumn19().addToZombies(getRandomZombie());
    }

    private static void startTheRailPlay() {
        Rail.setDefaultRailDeck();
        setWhileRailTurn(true);
    }

    public static boolean railCheckLoose() {
        for (int i = 0; i < 6; i++) {
            if (PlayGround.getSpecifiedUnit(i,0).getZombies().size() > 0)
                return true;
        }
        return false;
    }




    public static Plant generateRandomPlant() {
        Plant plant = null;
        int randomPlantIndex = (int) (Math.random() * 24);
        plant = new Plant(Plant.getPlants().get(randomPlantIndex));
        return plant;
    }

    public static Plant getNonWaterPlant() {
        Plant plant = generateRandomPlant();
        if (!plant.isCanBePlantedInWater() || !plantExitsInRailDeck(plant.getName()))
            return plant;
        else
            return getNonWaterPlant();
    }

    private static boolean plantExitsInRailDeck(String plantName) {
        for (Plant plantIterator : railDeck) {
            if (plantIterator.getName().equals(plantName))
                return true;
        }
        return false;
    }

    public static void addRandomPlantToRailDeck() {
        if (railDeck.size() < 10)
            railDeck.add(getNonWaterPlant());
    }

    public static int generateTwoThreeFour() {
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

    public static void clearRailDeck() {
        railDeck.clear();
    }

    public static void doFinalThingsRail() {
        System.out.println("You Lost..!!");
        System.out.println("Zombies are EATING you.....");
        Collection.clearDecksSetCollections();
        clearRailDeck();
        View.goingBackTo(-2); // Going back to Login Menu
        Menu.mainMenu();
    }

}
