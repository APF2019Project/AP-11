import java.util.ArrayList;

public class Unit {

    private byte X;
    private byte Y;

    private boolean isWater;

    private ArrayList<Plant> plants = new ArrayList<>();
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private ArrayList<Shoot> shoots = new ArrayList<>();

    public Unit(byte x, byte y, boolean isWater) {
        X = x;
        Y = y;
        this.isWater = isWater;
    }


    // All getters:
    public byte getX() {
        return X;
    }

    public byte getY() {
        return Y;
    }

    public boolean isWater() {
        return isWater;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public ArrayList<Shoot> getShoots() {
        return shoots;
    }
}
