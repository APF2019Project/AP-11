import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZombieStyle extends Play {

    private static int turns = 0;
    private static int wave = 0;
    private static int usedDock = 0;
    private static int usedLadder = 0;
    private static int coins = 50;
    private static int lowestZombieCost;
    private static int numberOfLadders = 3;
    private static int numberOfDucks = 3;

    private static ArrayList<Plant> landPlants = new ArrayList<>();
    private static ArrayList<Plant> waterPlants = new ArrayList<>();
    private static ArrayList<Unit> landPlantingUnits = new ArrayList<>();
    private static ArrayList<Unit> waterUnitsIfWaterGround = new ArrayList<>();

    // rows zombies ArrayLists:
    public static ArrayList<Zombie> rowZombies_0 = new ArrayList<>();
    public static ArrayList<Zombie> rowZombies_1 = new ArrayList<>();
    public static ArrayList<Zombie> rowZombies_2 = new ArrayList<>();
    public static ArrayList<Zombie> rowZombies_3 = new ArrayList<>();
    public static ArrayList<Zombie> rowZombies_4 = new ArrayList<>();
    public static ArrayList<Zombie> rowZombies_5 = new ArrayList<>();



    private static boolean whileZombieTurn = true;
    private static int playerWon = 0;

    public static void zombieStyleTurn(boolean waterGround) {
        startZombiePlay(waterGround);
        while(whileZombieTurn) {
            if (waveFinished()) {
                if (gameFinished()) {
                    doFinalThings(playerWon);
                    return;
                }
            }
            Shoot.shootTurn();
            if (waveFinished()) {
                if (gameFinished()) {
                    doFinalThings(playerWon);
                    return;
                }
            }
            Zombie.zombiesTurn();

            if (waveFinished()) {
                if (gameFinished()) {
                    doFinalThings(playerWon);
                    return;
                }
            }
            Plant.plantsTurn();
            popZombiesToPlayground();
            Menu.zombieMenu(waterGround,false, waveFinished());
        }
    }

    public static void popZombiesToPlayground() {
        popFromZombieArr(rowZombies_0, 0);
        popFromZombieArr(rowZombies_1, 1);
        popFromZombieArr(rowZombies_2, 2);
        popFromZombieArr(rowZombies_3, 3);
        popFromZombieArr(rowZombies_4, 4);
        popFromZombieArr(rowZombies_5, 5);
    }

    private static void popFromZombieArr(ArrayList<Zombie> zombies, int x){
        if (zombies.size() != 0){
            Zombie poppedZ = zombies.get(0);
            zombies.remove(poppedZ);
            PlayGround.getSpecifiedUnit(x, 19).addToZombies(poppedZ);
        }
    }

    private static void startZombiePlay(boolean waterGround) {
        setEveryThingToDefaultStart();
        if (waterGround) {
            setWaterPlants();
        } else {
            setLandPlants();
        }
        setPlantingUnits(waterGround);
        randomPlanting(waterGround);
        whileZombieTurn = true;
    }

    public static void readyToGiveDuck(String zombieName, int row, int column) {
        if (numberOfDucks <= 0) {
            View.noDuckLeft();
            return;
        } else if (!PlayGround.validCoordinates(row, column)) {
            View.invalidCoordinates();
            return;
        } else if (!Unit.zombieExistsInUnit(row, column, zombieName)) {
            View.noZombieInUnit(zombieName, row, column);
            return;
        }
        Zombie zombie = null;
        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        for (Zombie zombieIterator : unit.getZombies()) {
            if (zombieIterator.getName().equals(zombieName))
                zombie = zombieIterator;
        }
        giveDuck(unit, zombie);
    }

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        ZombieStyle.coins = coins;
    }

    public static int getLowestZombieCost() {
        return lowestZombieCost;
    }

    public static void setLowestZombieCost(int lowestZombieCost) {
        ZombieStyle.lowestZombieCost = lowestZombieCost;
    }

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


    public static void putZombieInRow(String zombieName, int number, int row) {
        if (!Collection.zombieExistsInDeck(zombieName)) {
            View.invalidCardName();
            return;
        } else if (row < 0 || row > 5) {
            View.invalidCoordinates();
            return;
        } else if (coins < Zombie.getZombie(zombieName).getHealth()) {
            View.notEnoughMoney();
            return;
        }
        ArrayList<Zombie> arrayList = getZombieArrayListByRow(row);
        if (arrayList.size() >= 2) {
            View.alreadyTwoZombiesInRow(row);
        } else {
            arrayList.add(Zombie.cloningZombie(Zombie.getZombie(zombieName)));
            coins -= Zombie.getZombie(zombieName).getHealth();
            View.zombiePutInRow(zombieName, row);
        }
    }

    private static ArrayList<Zombie> getZombieArrayListByRow (int row) {
        switch (row) {
            case 0:
                return rowZombies_0;
            case 1:
                return rowZombies_1;
            case 2:
                return rowZombies_2;
            case 3:
                return rowZombies_3;
            case 4:
                return rowZombies_4;
            case 5:
                return rowZombies_5;
        }
        return null;
    }

    public static void giveLadder(String zombieName, int row, int column) {
        if (numberOfLadders <= 0) {
            View.noLaddersLeft();
            return;
        } else if (!PlayGround.validCoordinates(row, column)) {
            View.invalidCoordinates();
            return;
        } else if (!Unit.zombieExistsInUnit(row, column, zombieName)) {
            View.noZombieInUnit(zombieName, row, column);
            return;
        }
        for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(row, column).getZombies()) {
            if (zombieIterator.getName().equals(zombieName)) {

                zombieIterator.setHaveLadder(true);
            }
        }
    }

    public static void increaseCoin(int n){
        coins += n;
    }

    public static void giveDuck(Unit unit, Zombie zombie){
        zombie.setHaveDuck(true);
        int dest = -1;
        if (unit.getX() == 1){
            dest = 2;
        }
        if (unit.getX() == 4) {
            dest = 3;
        }
        if (dest == -1){
            return;
        }
        unit.getZombies().remove(zombie);
        PlayGround.getSpecifiedUnit(dest, unit.getY()).getZombies().add(zombie);
    }

    private static void doFinalThings(int playerWon) {
        Account.numOfKilledZombiesHandlingInAccount(true);
        setEveryThingToDefaultStart();
        if (playerWon == 1){
            View.youWon();
        }
        else if (playerWon == -1){
            View.youLost();
        }
        whileZombieTurn = false;

        // kian jan code it please
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
            playerWon = -1;
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
            playerWon = +1;
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


    public static void showLanes() {
        System.out.println("Show Lanes:");
        System.out.print("row 0:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_0) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
        }
        System.out.println();
        System.out.print("row 1:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_1) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
        }
        System.out.println();
        System.out.print("row 2:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_2) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
        }
        System.out.println();
        System.out.print("row 3:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_3) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
        }
        System.out.println();
        System.out.print("row 4:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_0) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
        }
        System.out.println();
        System.out.print("row 5:");
        for (Zombie zombieIterator : ZombieStyle.rowZombies_5) {
            System.out.print(" ");
            System.out.print(zombieIterator.getName());
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

    public static void setEveryThingToDefaultStart() {
        turns = 0;
        wave = 0;
        usedDock = 0;
        usedLadder = 0;
        coins = 50;
        numberOfLadders = 3;
        numberOfDucks = 3;

        landPlants.clear();
        waterPlants.clear();
        landPlantingUnits.clear();
        waterUnitsIfWaterGround.clear();

        rowZombies_0.clear();
        rowZombies_1.clear();
        rowZombies_2.clear();
        rowZombies_3.clear();
        rowZombies_4.clear();
        rowZombies_5.clear();

        whileZombieTurn = true;
        playerWon = 0;
    }

    public static void setWhileZombieTurn(boolean whileZombieTurn) {
        ZombieStyle.whileZombieTurn = whileZombieTurn;
    }


    public static boolean waveFinished() {
        return !haveZombieInPlayGround();
    }

    public static boolean gameFinished() {
//        if (haveZombieInColumn0()) {
//            playerWon = 1;
//            return true;
//        }
        if (!havePlantInPlayGround()) {
            playerWon = 1;
            return true;
        }
        if (!haveZombieInPlayGround()) {
            if (coins < lowestZombieCost) {
                playerWon = -1;
                return true;
            }
        }
        return false;
    }

    private static boolean havePlantInPlayGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                if (PlayGround.getSpecifiedUnit(i, j).getPlants()[0] != null)
                    return true;
            }
        }
        return false;
    }

    private static boolean haveZombieInPlayGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                if (PlayGround.getSpecifiedUnit(i, j).getZombies().size() > 0)
                    return true;
            }
        }
        return false;
    }

    private static boolean haveZombieInColumn0() {
        for (int i = 0; i <= 5; i++) {
            if (PlayGround.getSpecifiedUnit(i,0).getZombies().size() > 0)
                return true;
        }
        return false;
    }


}
