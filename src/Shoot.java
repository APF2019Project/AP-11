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

        // movement of shoots
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