public class PlayGround {

    private static Unit[][] units = new Unit[6][20];

    public static void BuildNewWithoutWaterPlayGround(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                units[i][j] = new Unit(i, j, false, false);
            }
            units[i][0].setHaveChamanZan(true);
        }
    }

    public static void BuildNewWithWaterPlayGround(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                units[i][j] = new Unit(i, j, false, false);
                if (i == 2 || i == 3){
                    units[i][j].setIsWater(true);
                }
            }
            units[i][0].setHaveChamanZan(true);
        }
    }

    public static Unit[][] getUnits() {
        return units;
    }

    public void printAllGround(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                units[i][j].printUnit();
            }
        }
    }

    public static Unit getSpecifiedUnit(int i, int j) {
        return units[i][j];
    }
}
