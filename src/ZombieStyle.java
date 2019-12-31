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
    private static Plant[] plants = new Plant[7];


    public static void zombieStyleTurn() {


        Menu.zombieMenu();
    }

    private static void initializePlants() {
      //  plants[0] =
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
