import java.util.ArrayList;

public class Unit {

    private int X;
    private int Y;
    private boolean IsWater;
    private boolean haveLawnMover;
    private boolean haveLadder;
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


    public void removeFromZombies(Zombie zombie){
        Account.increaseKilledZombiesTmp(1);
        boolean flag = false;
        if (zombie.isCouldRevertToRegularZombie()){
            flag = true;
        }
        zombies.remove(zombie);
        if (flag)
            this.addToZombies(Zombie.initializeRegularZombie());
    }

    public void putLadder() {
        if (this.plants[0] != null)
            if (this.plants[0].getType().equals("wall"))
                this.haveLadder = true;
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


    public boolean isWater() {
        return IsWater;
    }

    public boolean isHaveLadder() {
        return haveLadder;
    }

    public void setIsWater(boolean water) {
        IsWater = water;
    }

    public boolean getHaveLawnMover() {
        return haveLawnMover;
    }

    public void setHaveLawnMover(boolean haveLawnMover) {
        this.haveLawnMover = haveLawnMover;
    }

    public void killPlant(int index){
        plants[index] = null;
    }

    public void printUnit() {
        System.out.print(this.getX() + " " + this.getY() + " ");
        for (Zombie z: Zombie.getZombies()){
            System.out.print(z.getName() + " ");
        }
    }

    public void removeAllPlants() {
        this.plants[0] = null;
        this.plants[1] = null;
    }

    public void removeAllZombies() {
        int n = this.getZombies().size();
        Account.increaseKilledZombiesTmp(n);
        ArrayList <Zombie> regularZombies = new ArrayList<>();

        for (Zombie zombie: this.getZombies()){
            if (zombie.isCouldRevertToRegularZombie()){
                Zombie zombieTmp = Zombie.initializeRegularZombie();
                regularZombies.add(zombieTmp);
            }
        }
        this.getZombies().clear();
        for (Zombie zombie: regularZombies){
            this.addToZombies(zombie);
        }
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
        if (!unit.isWater()) {
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


    public static boolean zombieExistsInUnit(int row, int column, String zombieName) {
        for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(row, column).getZombies()) {
            if (zombieIterator.getName().equals(zombieName))
                return true;
        }
        return false;
    }
}
