public class Shoot {
    private final boolean isPea;
    private final int damage;
    private int speed;
    private double bufFactor;
    private final int effectiveTime;

    public Shoot(boolean isPea, int damage, int speed, double bufFactor, int effectiveTime) {
        this.isPea = isPea;
        this.damage = damage;
        this.speed = speed;
        this.bufFactor = bufFactor;
        this.effectiveTime = effectiveTime;
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

    public double getBufFactor() {
        return bufFactor;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }
}