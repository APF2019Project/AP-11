public class Shoot {

    private int damage;
    private int speed;
    private int effectiveTime;

    private boolean isPea;
    private boolean hasBuff;

    public Shoot(int damage, int speed, int effectiveTime, boolean isPea, boolean hasBuff) {
        this.damage = damage;
        this.speed = speed;
        this.effectiveTime = effectiveTime;
        this.isPea = isPea;
        this.hasBuff = hasBuff;
    }

    // All getters:
    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }

    public boolean isPea() {
        return isPea;
    }

    public boolean isHasBuff() {
        return hasBuff;
    }

    // All setters:

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public void setPea(boolean pea) {
        isPea = pea;
    }

    public void setHasBuff(boolean hasBuff) {
        this.hasBuff = hasBuff;
    }

}
