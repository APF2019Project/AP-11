public class Shoot {
    private final boolean isPea;
    private final int damage;
    private int speed;
    private double bufFactor;
    private int effectiveTime;
    private String direction;

    public Shoot(boolean isPea, int damage, int speed, double bufFactor, int effectiveTime) {
        this.isPea = isPea;
        this.damage = damage;
        this.speed = speed;
        this.bufFactor = bufFactor;
        this.effectiveTime = effectiveTime;
    }
    public Shoot(Shoot shoot) {
        this.isPea = shoot.isPea;
        this.damage = shoot.damage;
        this.speed = shoot.speed;
        this.bufFactor = shoot.bufFactor;
        this.effectiveTime = shoot.effectiveTime;
    }

    public static void shootTurn() {
        Unit[][] units = new Unit[6][20];
        tmpPlayGroundInitializer(units);
        updateMainPlayGround(units);
    }

    private static void tmpPlayGroundInitializer(Unit[][] units) {
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                Unit unit = PlayGround.getSpecifiedUnit(i, j);
                shootUnit(unit, units);
            }
        }
    }

    private static void updateMainPlayGround(Unit[][] units) {
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 20; j++){
                Unit unit = PlayGround.getSpecifiedUnit(i, j);
                unit.removeAllShoots();
                Unit tmpUnit = units[i][j];
                unit.cpShootsFrom(tmpUnit);
            }
        }
    }

    private static void shootUnit(Unit unit, Unit[][] tmpUnits) {
        for (Shoot shoot: unit.getShoots()){
            shootAction(unit.getX(), unit.getY(), tmpUnits, shoot);
        }
    }

    private static void shootAction(int X, int Y, Unit[][] tmpUnits, Shoot shoot) {
        if (shoot.getDirection().equals("forward")){
            int destinationForward = findDestinationForward(X, Y, shoot);
            if (destinationForward == Integer.MAX_VALUE){
                return;
            }
            tmpUnits[X][destinationForward].addToShoots(shoot);
        }
        if (shoot.getDirection().equals("backward")){
            int destinationBackward = findDestinationBackward(X, Y, shoot);
            if (destinationBackward == Integer.MAX_VALUE){
                return;
            }
            tmpUnits[X][destinationBackward].addToShoots(shoot);
        }
        if (shoot.getDirection().equals("up")) {
            int destinationUp = findDestinationUp(X, Y, shoot);
            if (destinationUp == Integer.MAX_VALUE) {
                return;
            }
            tmpUnits[destinationUp][Y].addToShoots(shoot);
        }
        if (shoot.getDirection().equals("down")){
            int destinationDown = findDestinationDown(X, Y, shoot);
            if (destinationDown == Integer.MAX_VALUE) {
                return;
            }
            tmpUnits[destinationDown][Y].addToShoots(shoot);
        }
    }

    private static int findDestinationDown(int x, int y, Shoot shoot) {
        // todo or not todo is the quest
        return Integer.MAX_VALUE; // dead
    }

    private static int findDestinationUp(int x, int y, Shoot shoot) {
        // todo or not todo is the quest
        return Integer.MAX_VALUE; // dead
    }

    private static int findDestinationBackward(int x, int y, Shoot shoot) {
        // todo or not todo is the quest
        return Integer.MAX_VALUE; // dead
    }

    private static int findDestinationForward(int x, int y, Shoot shoot) {
        // todo or not todo is the quest
        return Integer.MAX_VALUE; // dead
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    //Getters:

    public boolean isPea() {
        return isPea;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public double getBufFactor() {
        return bufFactor;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }


}