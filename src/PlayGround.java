import java.util.ArrayList;

public class PlayGround {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private static Unit[][] playGround = new Unit[6][20];

    public static void BuildDayPlayGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                playGround[i][j] = new Unit(i, j, false, false);
            }
            playGround[i][0].setHaveLawnMover(true);
        }
    }

    public static void BuildWaterPlayGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                playGround[i][j] = new Unit(i, j, false, false);
                if (i == 2 || i == 3) {
                    playGround[i][j].setIsWater(true);
                }
            }
            playGround[i][0].setHaveLawnMover(true);
        }
    }

    public static Unit[][] getUnits() {
        return playGround;
    }

    public static int randomPositiomY() {
        double randY = Math.random();
        int randYInt = (int) (randY * 18.68 + 1);
        return randYInt;
    }

    public static int randomPositionX() {
        double randX = Math.random();
        int randXInt = (int) (randX * 5.68);
        return randXInt;
    }


    public static Unit getSpecifiedUnit(int i, int j) {
        if (i > 5 || j > 19) {
            return null;
        }
        if (i < 0 || j < 0) {
            return null;
        }
        return playGround[i][j];
    }

    public static void removeAllPlants(int i, int j) {
        playGround[i][j].removeAllPlants();
    }

    public static void showLawn() {
        System.out.println("Zombies in play ground:");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                View.showLawnZombies(playGround[i][j].getZombies(), i, j);
            }
        }
        System.out.println("plants in play ground:");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
               if (playGround[i][j].getPlants()[0] == null && playGround[i][j].getPlants()[1] == null) {
                   continue;
               } else if (playGround[i][j].getPlants()[1] == null) {
                   View.showLawnPlants(playGround[i][j].getPlants()[0], i, j);
               } else {
                   View.showLawnPlants(playGround[i][j].getPlants()[0], i, j);
                   View.showLawnPlants(playGround[i][j].getPlants()[1], i, j);
               }
            }
        }
        View.showLawnShoots();
    }

    public static void printPlayGround() {
        System.out.print("  ");
        for (int j = 0; j < 20; j++) {
            System.out.print("   " + j + "   ");
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < 6; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 20; j++) {
                Unit unit = getSpecifiedUnit(i, j);
                int zombiesNumber = unit.getZombies().size();
                int shootsNumber = unit.getShoots().size();
                int plantsNumber = Unit.plantsNumbers(unit);

                String zombiesNumberString = Integer.toString(zombiesNumber);
                String shootsNumberString = Integer.toString(shootsNumber);
                String plantsNumberString = Integer.toString(plantsNumber);

                boolean havePlant = false;
                boolean haveShoot = false;
                boolean haveZombie = false;

                if (zombiesNumber > 0)
                    haveZombie = true;
                if (plantsNumber > 0)
                    havePlant = true;
                if (shootsNumber > 0)
                    haveShoot = true;


                if (j < 10) {
                    System.out.print("  ");
                    if (haveZombie) {
                        System.out.print(ANSI_RED + zombiesNumberString + ANSI_RESET);
                    } else {
                        System.out.print(zombiesNumber);
                    }
                    if (haveShoot) {
                        System.out.print(ANSI_BLUE + shootsNumberString + ANSI_RESET);
                    } else {
                        System.out.print(shootsNumber);
                    }
                    if (havePlant) {
                        System.out.print(ANSI_GREEN + plantsNumberString + ANSI_RESET);
                    } else {
                        System.out.print(plantsNumber);
                    }
                    System.out.print("  ");
                } else {
                    System.out.print("   ");
                    if (haveZombie) {
                        System.out.print(ANSI_RED + zombiesNumberString + ANSI_RESET);
                    } else {
                        System.out.print(zombiesNumber);
                    }
                    if (haveShoot) {
                        System.out.print(ANSI_BLUE + shootsNumberString + ANSI_RESET);
                    } else {
                        System.out.print(shootsNumber);
                    }
                    if (havePlant) {
                        System.out.print(ANSI_GREEN + plantsNumberString + ANSI_RESET);
                    } else {
                        System.out.print(plantsNumber);
                    }
                    System.out.print("  ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }



    public static boolean validCoordinates(int row, int column) {
        return !(row < 0) && !(column < 0) && !(row > 5) && !(column > 19);
    }

    public static boolean isHaveLadder() {
        return false;
    }

    public static void removeLawnMover(){
        for (int i =0; i<6; i++)
            playGround[i][0].setHaveLawnMover(false);
    }
}
