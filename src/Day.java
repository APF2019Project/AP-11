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

    public static void dayTurn() {

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
            dayMenu();
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


    public static void dayMenu() {  // DayAndWater Menu index : 11

        boolean whileTrue = true;
        String command;
        Pattern plantingPattern = Pattern.compile("[p,P]lant (?<row>\\d+),(?<column>\\d+)");
        Pattern removePattern = Pattern.compile("[r,R]emove (?<row>\\d+),(?<column>\\d+)");

        while (whileTrue) {
            System.out.println("--- Day Menu ---\nEnter command:");

            command = scanner.nextLine();
            Matcher plantMatcher = plantingPattern.matcher(command);
            Matcher removeMatcher = removePattern.matcher(command);

            if (command.matches("[s,S]elect (.+)")) {
                String cardName = getCardName(command);
                selectPlant(cardName);

            } else if (plantMatcher.matches()) {
                int row = Integer.parseInt(plantMatcher.group("row"));
                int column = Integer.parseInt(plantMatcher.group("column"));
                plantSelectedPlant(row, column, selectedPlant.getName());

            } else if (removeMatcher.matches()) {
                int row = Integer.parseInt(removeMatcher.group("row"));
                int column = Integer.parseInt(removeMatcher.group("column"));
                removePlant(row, column);

            } else if (command.toLowerCase().equals("show hand")) {
                showHandInDay();

            } else if (command.toLowerCase().equals("end turn")) {
                whileTrue = false;
                return;

            } else if (command.toLowerCase().equals("show lawn")) {
                //
            } else if (command.toLowerCase().equals("exit")) {
                whileTrue = false;
                whileDayTurn = false;
                View.goingBackTo(-5);
            } else if (command.toLowerCase().equals("help")) {
                View.showHelp(11);

            } else {
                View.invalidCommand(11);
            }
        }
    }


    private static String getCardName(String command) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        return command;
    }

    private static void selectPlant(String plantName) {
        Plant selectCandidate = null;
        if (!Collection.plantExistsInDeck(plantName)) {
            View.cardNotInDeck("plants", plantName);
            return;
        } else {
            selectCandidate = Collection.getPlantInDeck(plantName);
        }
        if (selectCandidate.getSunCost() > sun || selectCandidate.getRespawnTime() < selectCandidate.getRespawnCoolDown()) {
            View.notEnoughSunOrCharge();
        } else {
            selectedPlant = selectCandidate;
            View.cardSelected(plantName);
        }
    }

    public static void plantSelectedPlant(int row, int column, String plantName) {
        Unit unit = PlayGround.getSpecifiedUnit(row, column);
        Plant plant = selectedPlant;
        boolean isWater = unit.getIsWater();
        boolean waterPlant = plant.isCanBePlantedInWater();
        if (unit.getPlants()[0] == null) {
            if ((!isWater) && (!waterPlant)) {
                unit.setPlant0(plant);
                Collection.getPlantInDeck(plantName).setRespawnTime(0);
                selectedPlant = null;
                View.plantedInUnit(row, column, plantName);
            } else if ((!isWater) && waterPlant) {
                View.waterPlantInLand();
            } else if (isWater && (!waterPlant)) {
                View.landPlantInWater();
            } else if (isWater && waterPlant) {
                unit.setPlant0(plant);
                Collection.getPlantInDeck(plantName).setRespawnTime(0);
                selectedPlant = null;
                View.plantedInUnit(row, column, plantName);
            }
        } else if (unit.getPlants()[1] == null) {
            if (unit.getPlants()[0].getName().equals("Lily Pad")) {
                if (!waterPlant) {
                    unit.setPlant1(plant);
                    Collection.getPlantInDeck(plantName).setRespawnTime(0);
                    selectedPlant = null;
                    View.plantedInUnit(row, column, plantName);
                } else {
                    View.landPlantOnLilyPad();
                }
            } else {
                View.unitIsFilled(row, column, unit);
            }
        } else {
            View.unitIsFilled(row, column, unit);
        }
    }

    public static void removePlant(int row, int column) {
        PlayGround.getSpecifiedUnit(row, column).setPlant0(null);
    }

    private static void showHandInDay() {
        int i = 1;
        for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
            System.out.print(i + ". ");
            System.out.println(plantIterator.getName() + "  sun: " + plantIterator.getSunCost() + "  respawnTime: " +
                    plantIterator.getRespawnTime());
            i++;
        }
    }
}
