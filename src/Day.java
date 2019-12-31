import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day extends Play {

    private static boolean whileDayTurn = true;

    private static int wave = 0; // after play, initialize to zero.

    private static int sun = 2;

    private static int playerHealth = 1; // sajad edited this to debug

    private static int wavelessTurns = 0;

    public static int getSun() {
        return sun;
    }

    public static void setSun(int sun) {
        Day.sun = sun;
    }

    public static void increaseSun() {
        setSun(getSun() + 1);
    }


    public static boolean isWhileDayTurn() {
        return whileDayTurn;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWhileDayTurn(boolean whileDayTurn) {
        Day.whileDayTurn = whileDayTurn;
    }

    public static void setWave(int wave) {
        Day.wave = wave;
    }

    public static void dayAndWaterTurn(int playTypeIndex) {
        setWhileDayTurn(true);
        while (whileDayTurn) {
            if (checkFinished()) {
                return;
            }
            Shoot.shootTurn();
            if (checkFinished()) {
                return;
            }
            Zombie.zombiesTurn();
            if (checkFinished()) {
                return;
            }
            Plant.plantsTurn();
            checkWave();
            Menu.dayMenu(playTypeIndex);
        }
    }

    private static boolean checkFinished() {

        for (int i = 0; i < 6; i++)
            if (PlayGround.getSpecifiedUnit(i, 0).getZombies().size() > 0) {
                playerHealth -= PlayGround.getSpecifiedUnit(i, 0).getZombies().size();
                PlayGround.getSpecifiedUnit(i, 0).getZombies().clear();
                if (playerHealth <= 0) {
                    // you lost
                    return true;
                }
            }
        if (allZombiesAreDead()) {
            if (wave == 3) {
                //you won.
                return true;
            }
        }

        return false;
    }

    private static void checkWave() {
        if (allZombiesAreDead()) {
            wavelessTurns++;
            if (wave == 0 && wavelessTurns == 3) {
                waveGenerator();
                wavelessTurns = 0;
                wave++;
            } else if ((wave == 1 || wave == 2) && wavelessTurns == 7) {
                waveGenerator();
                wavelessTurns = 0;
                wave++;
            }
        }
    }

    private static void waveGenerator() {
        int numberOfZombiesInWave = (int) (Math.random() * 6) + 4;
        int zombiesNumber = Zombie.getZombies().size();
        for (int i = 0; i < numberOfZombiesInWave; i++) {
            int randomZombie = (int) (Math.random() * (zombiesNumber));
            int randomX = (int) (Math.random() * 5.68);
            Zombie zombie = Zombie.cloningZombie(Zombie.getZombies().get(randomZombie));
            PlayGround.getSpecifiedUnit(randomX, 19).addToZombies(zombie);
        }
    }

    private static boolean allZombiesAreDead() {
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 20; j++)
                if (PlayGround.getSpecifiedUnit(i, j).getZombies().size() > 0)
                    return false;
        return true;
    }

    public static String getCardName(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return command;
    }

    public static void selectPlant(String plantName) {
        Plant selectCandidate = null;
        if (!Collection.plantExistsInDeck(plantName)) {
            View.cardNotInDeck("plants", plantName);
            return;
        } else {
            selectCandidate = Collection.getPlantInDeck(plantName);
        }
        if (selectCandidate.getSunCost() > sun || selectCandidate.getRespawnTime() != 0) {
            View.notEnoughSunOrCharge();
        } else {
            selectedPlant = selectCandidate;
            View.cardSelected(plantName);
        }
    }


    public static void plantSelectedPlant(int row, int column, String plantName) {
        if (column % 2 != 0) {
            View.invalidCoordinates();
            return;
        }
        if (selectedPlant == null) {
            View.noPlantIsSelected();
            return;
        }
        if (PlayGround.getSpecifiedUnit(row, column) == null) {
            View.invalidCoordinates();
            return;
        }
        if (PlayGround.getSpecifiedUnit(row, column).getZombies().size() != 0) {
            View.unavailableCoordinates();
            return;
        }

        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        Plant plant = selectedPlant;
        Plant plant_0 = unit.getPlants()[0];
        Plant plant_1 = unit.getPlants()[1];
        boolean isWater = unit.getIsWater();
        boolean waterPlant = plant.isCanBePlantedInWater();

        if ((!waterPlant) && (!isWater)) {
            if (plant_0 == null) {
                plantIn0(unit, plant, row, column, plantName);
            } else {
                View.unitIsFilled(row, column, unit, 0);
            }
        } else if ((!waterPlant)) {
            if (plant_1 != null) {
                plantIn0(unit, plant, row, column, plantName);
            } else {
                View.landPlantInWater();
            }
        } else if (isWater) {
            if (plant.getName().equals("Lily Pad")) {
                if (plant_1 == null && plant_0 == null) {
                    plantIn1(unit, plant, row, column, plantName);
                } else if (plant_0 != null && plant_1 == null) {
                    View.unitIsFilled(row, column, unit, 0);
                } else if (plant_0 == null && plant_1 != null) {
                    View.alreadyALilyPadHere(row, column);
                } else if (plant_0 != null && plant_1 != null) {
                    View.unitIsFilled(row, column, unit, 0);
                }
            } else {
                if (plant_1 != null) {
                    View.alreadyALilyPadHere(row, column);
                } else if (plant_0 != null) {
                    View.unitIsFilled(row, column, unit, 0);
                } else {
                    plantIn0(unit, plant, row, column, plantName);
                }
            }
        } else {
            View.waterPlantInLand();
        }
    }


    public static void cheatPlantIn0 (Unit unit, Plant plant) {
        unit.setPlant0(plant);
    }

    private static void plantIn0(Unit unit, Plant plant, int row, int column, String plantName) {
        unit.setPlant0(plant);
        Collection.getPlantInDeck(plantName).setRespawnTime(Collection.getPlantInDeck(plantName)
                .getRespawnCoolDown());
        selectedPlant = null;
        sun -= Collection.getPlantInDeck(plantName).getSunCost();
        View.plantedInUnit(row, column, plantName);
    }

    private static void plantIn1(Unit unit, Plant plant, int row, int column, String plantName) {
        unit.setPlant1(plant);
        Collection.getPlantInDeck(plantName).setRespawnTime(Collection.getPlantInDeck(plantName)
                .getRespawnCoolDown());
        selectedPlant = null;
        sun -= Collection.getPlantInDeck(plantName).getSunCost();
        View.plantedInUnit(row, column, plantName);
    }

    public static void removePlant(int row, int column) {
        if (PlayGround.getSpecifiedUnit(row, column) == null) {
            View.invalidCoordinates();
            return;
        }
        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        boolean isWater = unit.getIsWater();
        String plantName;
        if (!isWater) {
            if (unit.getPlants()[0] != null) {
                plantName = unit.getPlants()[0].getName();
                unit.getPlants()[0] = null;
                View.plantRemoved(row, column, plantName);
            } else {
                View.unitIsEmpty(row, column);
            }
        } else { // is water:
            if (unit.getPlants()[0] != null) {
                plantName = unit.getPlants()[0].getName();
                unit.getPlants()[0] = null;
                View.plantRemoved(row, column, plantName);
            } else {
                if (unit.getPlants()[1] != null) {
                    plantName = unit.getPlants()[1].getName();
                    unit.getPlants()[1] = null;
                    View.plantRemoved(row, column, plantName);
                } else {
                    View.unitIsEmpty(row, column);
                }
            }
        }


    }

    public static void showHandInDay() {
        int i = 1;
        for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
            System.out.print(i + ". ");
            System.out.println(plantIterator.getName() + "  sun: " + plantIterator.getSunCost() + "  respawnTime: " +
                    plantIterator.getRespawnTime());
            i++;
        }
    }
}
