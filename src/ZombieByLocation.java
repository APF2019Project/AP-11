public class ZombieByLocation {
    int X;
    int Y;

    public ZombieByLocation(int x, int y, Zombie zombie) {
        X = x;
        Y = y;
        this.zombie = zombie;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    Zombie zombie;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }
}
