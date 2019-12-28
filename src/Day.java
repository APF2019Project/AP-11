import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day extends Play {

    private static boolean whileDayTurn = true;

    private static int wave = 0; // after play, initialize to zero.

    private static int sun = 2;

    public static int getSun() {
        return sun;
    }

    public static void setSun(int sun) {
        Day.sun = sun;
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
            Menu.dayMenu(playTypeIndex);
        }
    }

    private static boolean checkFinished() {
        // based on wave and allZombiesAreDead checks finish
        // wave++;
        // waveGenerator;
        //
        return false;
    }

    private static void waveGenerator() {
        //
    }

    private static boolean allZombiesAreDead() {
        return false;
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
        if (row % 2 != 0) {
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
        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        Plant plant = selectedPlant;
        boolean isWater = unit.getIsWater();
        boolean waterPlant = plant.isCanBePlantedInWater();
        if (unit.getPlants()[0] == null) {
            plantIn0(isWater, waterPlant, unit, plant, row, column, plantName);
        } else if (unit.getPlants()[1] == null) {
            plantIn1(isWater, waterPlant, unit, plant, row, column, plantName);
        } else {
            View.unitIsFilled(row, column, unit);
        }
    }

    private static void plantIn0 (boolean isWater, boolean waterPlant, Unit unit, Plant plant, int row, int column,
                                  String plantName) {
        if ((!isWater) && (!waterPlant)) {
            unit.setPlant0(plant);
            Collection.getPlantInDeck(plantName).setRespawnTime(Collection.getPlantInDeck(plantName)
                    .getRespawnCoolDown());
            selectedPlant = null;
            sun -= Collection.getPlantInDeck(plantName).getSunCost();
            View.plantedInUnit(row, column, plantName);
        } else if ((!isWater) && waterPlant) {
            View.waterPlantInLand();
        } else if (isWater && (!waterPlant)) {
            View.landPlantInWater();
        } else if (isWater && waterPlant) {
            unit.setPlant0(plant);
            Collection.getPlantInDeck(plantName).setRespawnTime(Collection.getPlantInDeck(plantName)
                    .getRespawnCoolDown());
            selectedPlant = null;
            sun -= Collection.getPlantInDeck(plantName).getSunCost();
            View.plantedInUnit(row, column, plantName);
        }
    }

    private static void plantIn1 (boolean isWater, boolean waterPlant, Unit unit, Plant plant, int row, int column,
                                  String plantName) {
        if (unit.getPlants()[0].getName().equals("Lily Pad")) {
            if (!waterPlant) {
                unit.setPlant1(plant);
                Collection.getPlantInDeck(plantName).setRespawnTime(Collection.getPlantInDeck(plantName)
                        .getRespawnCoolDown());
                selectedPlant = null;
                sun -= Collection.getPlantInDeck(plantName).getSunCost();
                View.plantedInUnit(row, column, plantName);
            } else {
                View.landPlantOnLilyPad();
            }
        } else {
            View.unitIsFilled(row, column, unit);
        }
    }


    public static void removePlant(int row, int column) {
        if (PlayGround.getSpecifiedUnit(row, column) == null) {
            View.invalidCoordinates();
            return;
        }
        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        boolean isWater = unit.getIsWater();
        String plantName;
        if(!isWater) {
            if (unit.getPlants()[0] != null) {
                plantName = unit.getPlants()[0].getName();
                unit.getPlants()[0] = null;
                View.plantRemoved(row, column, plantName);
            } else {
                View.unitIsEmpty(row, column);
            }
        } else { // is water:
            if (unit.getPlants()[0] == null) {
                View.unitIsEmpty(row, column);
            } else {
                if (unit.getPlants()[1] == null) {
                    plantName = unit.getPlants()[0].getName();
                    unit.getPlants()[0] = null;
                    View.plantRemoved(row, column, plantName);
                } else {
                    plantName = unit.getPlants()[1].getName();
                    unit.getPlants()[1] = null;
                    View.plantRemoved(row, column, plantName);
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
