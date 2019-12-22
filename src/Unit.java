import java.util.ArrayList;

public class Unit {

    private int X;
    private int Y;
    private boolean IsWater;
    private boolean haveLawnMover;  //chamanZan
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private ArrayList<Plant> plants = new ArrayList<>();
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


    public void AddToZombies(Zombie zombie){
        zombies.add(zombie);
    }

    public void AddToPlants(Plant plant){
        plants.add(plant);
    }
    public void AddToShoots(Shoot shoot){
        shoots.add(shoot);
    }


    public void RemoveFromZombies(Zombie zombie){
        zombies.remove(zombie);
    }

    public void RemoveFromPlants(Plant plant){
        plants.remove(plant);
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

    public void printUnit() {
        System.out.print(this.getX() + " " + this.getY() + " ");
        for (Zombie z: Zombie.getZombies()){
            System.out.print(z.getName() + " ");
        }
//        for (Plant p: plants.getPlants()){
//            System.out.print(p.getName() + " ");
//        }
//        for (Shoot s: Shoot.getShoots()){
//            System.out.print();
//        }
    }
}
