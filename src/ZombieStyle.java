import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZombieStyle extends Play {

    private static int turns;
    private static int wave;
    private static int usedDock;
    private static int usedLadder;

    private static ArrayList<Plant> landPlants = new ArrayList<>();
    private static ArrayList<Plant> waterPlants = new ArrayList<>();
    private static ArrayList<Unit> landPlantingUnits = new ArrayList<>();
    private static ArrayList<Unit> waterUnitsIfWaterGround = new ArrayList<>();

    private static boolean whileZombieTurn = true;
    private static int playerWon = 0;

    public static void zombieStyleTurn(boolean waterGround) {
        startZombiePlay(waterGround);
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
            Menu.zombieMenu(waterGround, false);
        }
    }

    private static void startZombiePlay(boolean waterGround) {
        if (waterGround) {
            setWaterPlants();
        } else {
            setLandPlants();
        }
        setPlantingUnits(waterGround);
        randomPlanting(waterGround);
        whileZombieTurn = true;
    }

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        ZombieStyle.coins = coins;
    }

    private static int coins = 50;

    public static int getLowestZombieCost() {
        return lowestZombieCost;
    }

    public static void setLowestZombieCost(int lowestZombieCost) {
        ZombieStyle.lowestZombieCost = lowestZombieCost;
    }

    private static int lowestZombieCost;


    private static void setPlantingUnits(boolean waterGround) {
        if (!waterGround) {
            for (int i = 0; i <= 5; i++) {
                for (int j = 1; j <= 3; j++) {
                    landPlantingUnits.add(PlayGround.getSpecifiedUnit(i, j));
                }
            }
            Collections.shuffle(landPlantingUnits);
        } else {
            for (int i = 0; i <= 1; i++) {
                for (int j = 1; j <= 3; j++) {
                    landPlantingUnits.add(PlayGround.getSpecifiedUnit(i, j));
                }
            }
            for (int i = 4; i <= 5; i++) {
                for (int j = 1; j <= 3; j++) {
                    landPlantingUnits.add(PlayGround.getSpecifiedUnit(i, j));
                }
            }
            for (int i = 2; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    waterUnitsIfWaterGround.add(PlayGround.getSpecifiedUnit(i, j));
                }
            }
            Collections.shuffle(landPlantingUnits);
            Collections.shuffle(waterUnitsIfWaterGround);
        }
    }


    private static void randomPlanting(boolean waterGround) {
        if (!waterGround) {
            for (int i = 0; i < 18; i++) {
                landPlantingUnits.get(i).setPlant0(new Plant(landPlants.get(i)));
            }
        }
        else { // water ground:
            for (int i = 0; i <= 2; i++) { // planting Lily Pads and three land plants on them:
                waterUnitsIfWaterGround.get(i).setPlant1(new Plant(waterPlants.get(i)));
                waterUnitsIfWaterGround.get(i).setPlant0(new Plant(landPlants.get(i)));
            }
            waterUnitsIfWaterGround.get(3).setPlant0(new Plant(waterPlants.get(3)));
            waterUnitsIfWaterGround.get(4).setPlant0(new Plant(waterPlants.get(4)));
            for (int i = 0; i < 12; i++) {
                landPlantingUnits.get(i).setPlant0(landPlants.get(i + 3));
            }
        }
    }

    private static void doFinalThings(int playerWon) {
        //
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
                if (unit.getPlants()[0] != null)
                    thereIsNoPlantLeft = false;
            }
        if (thereIsNoPlantLeft) {
            // you won.
            return true;
        }
        return false;
    }


    public static void showHandInZombieStyle(Account zombiePlayer) {
        System.out.println("Your zombies deck:");
        int i = 1;
        for (Zombie zombieIterator : zombiePlayer.zombiesDeck) {
            System.out.print(i + ". ");
            System.out.println(zombieIterator.getName() + "    " + zombieIterator.getHealth());
            i++;
        }
    }

    private static void setLandPlants() {
        for (int i = 1; i <= 3; i++) {
            landPlants.add(new Plant(Plant.getPlant("Explode-o-nut")));
        }
        for (int i = 1; i <= 6; i++) {
            landPlants.add(new Plant(Plant.getPlant("Scaredy-shroom")));
        }
        landPlants.add(new Plant(Plant.getPlant("Snow Pea")));
        landPlants.add(new Plant(Plant.getPlant("Snow Pea")));
        landPlants.add(new Plant(Plant.getPlant("Cabbage-pult")));
        landPlants.add(new Plant(Plant.getPlant("Cabbage-pult")));
        landPlants.add(new Plant(Plant.getPlant("Threepeater")));
        landPlants.add(new Plant(Plant.getPlant("Gatling Pea")));
        for (int i = 1; i <= 3; i++) {
            landPlants.add(new Plant(Plant.getPlant("Potato Mine")));
        }
        Collections.shuffle(landPlants);
    }

    private static void setWaterPlants() {
        for (int i = 1; i <= 3; i++) {
            waterPlants.add(new Plant(Plant.getPlant("Lily Pad")));
        }
        waterPlants.add(new Plant(Plant.getPlant("Tangle Kelp")));
        waterPlants.add(new Plant(Plant.getPlant("Tangle Kelp")));
        for (int i = 1; i <= 3; i++) {
            landPlants.add(new Plant(Plant.getPlant("Explode-o-nut")));
        }
        for (int i = 1; i <= 6; i++) {
            landPlants.add(new Plant(Plant.getPlant("Scaredy-shroom")));
        }
        landPlants.add(new Plant(Plant.getPlant("Snow Pea")));
        landPlants.add(new Plant(Plant.getPlant("Snow Pea")));
        landPlants.add(new Plant(Plant.getPlant("Cabbage-pult")));
        landPlants.add(new Plant(Plant.getPlant("Cabbage-pult")));
        landPlants.add(new Plant(Plant.getPlant("Gatling Pea")));
        landPlants.add(new Plant(Plant.getPlant("Potato Mine")));
        landPlants.add(new Plant(Plant.getPlant("Cattail")));
    }

}
