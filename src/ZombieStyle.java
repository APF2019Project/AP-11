import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZombieStyle extends Play {

    private static int turns;
    private static int wave;
    private static int usedDock;
    private static int usedLadder;
    private static int coins = 50;
    private static int lowestZombieCost;
    private static ArrayList<Plant> sevenPlants = new ArrayList<>();

    private static boolean whileZombieTurn = true;
    private static int playerWon = 0;

    private static ArrayList<Unit> plantingUnits = new ArrayList<>();

    private static void setSevenPlants() {
        sevenPlants.addAll(Account.getPlayingAccount().getPlantsCollection());
    }

    public static void zombieStyleTurn() {
        startZombiePlay();
        while(whileZombieTurn) {
            if (checkFinished()) {
                doFinalThings(playerWon);
                return;
            }
            Shoot.shootTurn();
            if (checkFinished()) {
                doFinalThings(playerWon);
                return;
            }
            Zombie.zombiesTurn();
            if (checkFinished()) {
                doFinalThings(playerWon);
                return;
            }
            Plant.plantsTurn();
            Menu.zombieMenu();
        }
    }

    private static void startZombiePlay() {
        setSevenPlants();
        generateRandomUnitsToPlant();
        randomPlanting();
        whileZombieTurn = true;
    }

    private static void doFinalThings(int playerWon) {
        //
    }

    private static boolean unitExistsInPlantingUnits(Unit unit) {
        for (Unit unitIterator : plantingUnits) {
            if (unitIterator.equals(unit))
                return true;
        }
        return false;
    }

    private static void generateRandomUnitsToPlant() {
        while (plantingUnits.size() < 7) {
            int randomColumn = Rail.generateTwoThreeFour() - 1;
            int randomRow = (int) (Math.random() * 5.99);
            if (!unitExistsInPlantingUnits(PlayGround.getSpecifiedUnit(randomRow, randomColumn)))
                plantingUnits.add(PlayGround.getSpecifiedUnit(randomRow, randomColumn));
        }
    }

    private static void randomPlanting() {
        for (int i = 0; i <= 6; i++) {
            plantingUnits.get(i).setPlant0(sevenPlants.get(i));
        }
    }



    private static void increaseCoins(int plantInitializedHealth) {
        coins += 10 * plantInitializedHealth;
    }

    private static boolean isWaterUpThere(int x, int y) {
        return PlayGround.getSpecifiedUnit(x - 1, y - 1).isWater();
    }

    private static boolean isWaterDownThere(int x, int y) {
        return PlayGround.getSpecifiedUnit(x + 1, y - 1).isWater();
    }

    private static boolean checkFinished() {
        if (coins < lowestZombieCost) {
            // you lost.
            return true;
        }
        boolean thereIsNoPlantLeft = true;
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 4; j++) {
                Unit unit = PlayGround.getSpecifiedUnit(i, j);
                if (unit.getPlants()[0] != null || unit.getPlants()[1] != null)
                    thereIsNoPlantLeft = false;
            }
        if (thereIsNoPlantLeft) {
            // you won.
            return true;
        }
        return false;
    }


}
