public class ZombieByLocation {
    int X;
    int Y;
    Zombie zombie;

    public ZombieByLocation(int x, int y, Zombie zombie) {
        X = x;
        Y = y;
        this.zombie = zombie;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }
}


// could be inherited