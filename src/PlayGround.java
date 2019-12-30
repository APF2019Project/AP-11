//import com.sun.xml.internal.bind.v2.TODO;
import java.util.ArrayList;

public class PlayGround {

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
        // TODO rand;
        return 0;
    }

    public static int randomPositionX() {
        // TODO rand;
        return 0;
    }

    public void printAllGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                playGround[i][j].printUnit();
            }
        }
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
}
