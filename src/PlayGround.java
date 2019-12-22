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

    public void printAllGround() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 20; j++) {
                playGround[i][j].printUnit();
            }
        }
    }

    public static Unit getSpecifiedUnit(int i, int j) {
        return playGround[i][j];
    }
}
