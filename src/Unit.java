import java.util.ArrayList;

public class Unit {

    private int X;
    private int Y;
    private boolean IsWater;
    private boolean haveLawnMover;  //chamanZan
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private Plant[] plants = new Plant[2];
    private ArrayList<Shoot> shoots = new ArrayList<>();


    public Unit(int x, int y, boolean isWater, boolean haveLawnMover) {
        setHaveLawnMover(haveLawnMover);
        setIsWater(isWater);
        setX(x);
        setY(y);
    }


    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }


    public void addToZombies(Zombie zombie){
        zombies.add(zombie);
    }

    public void setPlant0(Plant plant){
        this.plants[0] = plant;
    }
    public void setPlant1(Plant plant){
        this.plants[1] = plant;
    }
    public void addToShoots(Shoot shoot){
        shoots.add(shoot);
    }


    public void RemoveFromZombies(Zombie zombie){
        zombies.remove(zombie);
    }

    public void RemovePlant(Plant plant){
        if (plants[0] == plant)
            plants[0] = null;
        else if (plants[1] == plant)
            plants[1] = null;
    }

    //Getters:


    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public Plant[] getPlants() {
        return plants;
    }

    public ArrayList<Shoot> getShoots() {
        return shoots;
    }

    public void RemoveFromShoots(Shoot shoot){
        shoots.remove(shoot);
    }


    public boolean getIsWater() {
        return IsWater;
    }

    public void setIsWater(boolean water) {
        IsWater = water;
    }

    public boolean getHaveChamanZan() {
        return haveLawnMover;
    }

    public void setHaveLawnMover(boolean haveLawnMover) {
        this.haveLawnMover = haveLawnMover;
    }

    public void killPlant(int index){
        plants[index] = null;
    }

    public void killZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    public void printUnit() {
        System.out.print(this.getX() + " " + this.getY() + " ");
        for (Zombie z: Zombie.getZombies()){
            System.out.print(z.getName() + " ");
        }
    }

    public void removeAllPlants() {
        Plant[] arr = this.getPlants();
        arr[0] = null;
        arr[1] = null;
    }

    public void removeAllZombies() {
        this.getZombies().clear();
    }

    public void removeAllShoots() {
        this.getShoots().clear();
    }

    public void cpShootsFrom(Unit tmpUnit) {
        for (Shoot shoot : tmpUnit.getShoots()) {
            this.addToShoots(shoot);
        }
    }

    public static int plantsNumbers(Unit unit) {
        Plant plant_1 = unit.getPlants()[1];
        Plant plant_0 = unit.getPlants()[0];
        if (!unit.getIsWater()) {
            if (plant_0 == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (plant_1 != null) {
                if (plant_0 != null) {
                    return 2;
                } else {
                    return 1;
                }
            } else {
                if (plant_0 != null) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
