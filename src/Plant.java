import java.util.ArrayList;

public class Plant {
    private static ArrayList<Plant> plants = new ArrayList<>();

    private String name;
    private int health;
    private int age = 0;
    private final int sunCost;
    private final Shoot bullet;
    private final int bulletNumber;
    private final int meleeDamage;
    private final int respawnCoolDown;
    private int respawnTime;
    private final int shootCoolDown;
    private int shootTime = 100;
    private final boolean isCattail;
    private final boolean isLilyPad;
    private String type;

    public Plant(String name, int health, int sunCost, Shoot bullet, int bulletNumber, int meleeDamage, int respawnCoolDown, int shootCoolDown, Boolean isCattail, boolean isLilyPad, String type) {
        this.name = name;
        this.health = health;
        this.sunCost = sunCost;
        this.bullet = bullet;
        this.bulletNumber = bulletNumber;
        this.meleeDamage = meleeDamage;
        this.respawnCoolDown = respawnCoolDown;
        this.shootCoolDown = shootCoolDown;
        this.isCattail = isCattail;
        this.isLilyPad = isLilyPad;
        this.type = type;
        Plant.plants.add(this);
    }

    public Plant(Plant plant) {
        this.name = plant.name;
        this.health = plant.health;
        this.sunCost = plant.sunCost;
        this.bullet = plant.bullet;
        this.bulletNumber = plant.bulletNumber;
        this.meleeDamage = plant.meleeDamage;
        this.respawnCoolDown = plant.respawnCoolDown;
        this.shootCoolDown = plant.shootCoolDown;
        this.isCattail = plant.isCattail;
        this.isLilyPad = plant.isLilyPad;
        this.type = plant.type;

    }

    static {
        //Shoots:
        Shoot normalPea = new Shoot(true, 1, 3, 1, 0);
        Shoot frozenPea = new Shoot(true, 1, 3, 0.5, 1);
        Shoot cabbage = new Shoot(false, 2, 3, 1, 0);
        Shoot kernel = new Shoot(false, 0, 3, 0, 2);
        Shoot melon = new Shoot(false, 3, 3, 1, 0);
        Shoot frozenMelon = new Shoot(false, 3, 3, 0.5, Integer.MAX_VALUE);


        new Plant("Peashooter", 2, 2, normalPea, 1, 0, 2, 2, false, false, "range -1 normal").setRespawnTime(2);
        new Plant("Snow Pea", 3, 3, frozenPea, 1, 0, 3, 3, false, false, "range -1 normal").setRespawnTime(3);
        new Plant("Cabbage-pult", 2, 2, cabbage, 1, 0, 3, 2, false, false, "range -1 normal").setRespawnTime(3);
        new Plant("Repeater", 4, 3, normalPea, 2, 0, 4, 3, false, false, "range -1 normal").setRespawnTime(4);
        new Plant("Threepeater", 5, 4, normalPea, 1, 0, 4, 4, false, false, "range -1 threeWay").setRespawnTime(4);
        new Plant("Cactus", 5, 5, normalPea, 1, 1, 4, 2, false, false, "range -1 normal").setRespawnTime(4);
        new Plant("Gatling Pea", 3, 5, normalPea, 4, 0, 4, 5, false, false, "range -1 normal").setRespawnTime(4);
        new Plant("Scaredy-shroom", 1, 1, normalPea, 1, 0, 2, 2, false, false, "range 2 normal").setRespawnTime(2);
        new Plant("Kernel-pult", 2, 3, kernel, 1, 0, 3, 4, false, false,"range -1 normal").setRespawnTime(3);
        new Plant("Melon-pult", 3, 3, melon, 1, 0, 3, 4, false, false, "range -1 normal").setRespawnTime(3);
        new Plant("Winter Melon", 3, 4, frozenMelon, 1, 0, 5, 4, false, false, "range -1 normal").setRespawnTime(5);

        new Plant("Wall-nut", 4, 2, null, 0, 0, 4, 0, false, false, "wall").setRespawnTime(4);
        new Plant("Explode-o-nut", 3, 4, null, 0, 1, 5, 0, false, false, "wall").setRespawnTime(5);
        new Plant("Tall-nut", 6, 4, null, 0, 0, 6, 0, false, false, "wall").setRespawnTime(6);
        new Plant("Potato Mine", 1, 2, null, 0, 100, 3, 0, false, false, "bomb mine").setRespawnTime(3);
        new Plant("Cherry Bomb", 0, 2, null, 0, 100, 4, 0, false, false, "bomb circle 1").setRespawnTime(4);
        new Plant("Jalapeno", 0, 4, null, 0, 100, 5, 0, false, false, "bomb linear").setRespawnTime(5);
        new Plant("Magnet-shroom", 2, 4, null, 0, 0, 4, 0, false, false, "magnet 1").setRespawnTime(4);
        new Plant("Sunflower", 2, 1, null, 1, 0, 2, 1, false, false, "producer").setRespawnTime(2);
        new Plant("Twin Sunflower", 2, 3, null, 2, 0, 5, 2, false, false, "producer").setRespawnTime(5);

      //  new Plant("Split Pea", 3, 4, normalPea, , 0, 4, , false, "range -1 twoWay")
        new Plant("Lily Pad", 1, 0, null, 0, 0, 1, 0, false, true, "stage")

    }

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public static boolean plantExist(String plantName) {
        for (Plant plantIterator : plants) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }

    public static Plant getPlant(String plantName) {
        for (Plant plantIterator : Plant.plants) {
            if (plantIterator.getName().equals(plantName)) {
                return plantIterator;
            }
        }
        return null;
    }
    //Getters:

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public int getSunCost() {
        return sunCost;
    }

    public Shoot getBullet() {
        return bullet;
    }

    public int getBulletNumber() {
        return bulletNumber;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public int getRespawnCoolDown() {
        return respawnCoolDown;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getShootCoolDown() {
        return shootCoolDown;
    }

    public boolean isCattail() {
        return isCattail;
    }

    public String getType() {
        return type;
    }

    //Methods:

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    public void respawnTime() {
        if (this.respawnTime < this.respawnCoolDown)
            this.respawnTime++;
    }

    public void setRespawnTime(int time) {
        this.respawnTime = time;
    }

    public int getPrice() {
        return (this.sunCost * this.health * this.respawnCoolDown + 1);
    }


    //Turn:

    public void turn(Unit unit) {

        this.age++;
        if (this.shootTime < this.shootCoolDown)
            this.shootTime++;

        if (this.type.matches("range [-]?\\d*"))
            isRange(unit);
        else if (this.type.matches("bomb .+"))
            isBomb(unit);
        else if (this.type.equals("wall"))
            isWall(unit);
        else if (this.type.equals("producer"))
            isProducer(unit);
    }

    private void isRange(Unit unit) {
        if (unit.getZombies().size() > 0)
            damageZombie(unit, unit.getZombies().get(0));
        if ((this.shootTime % this.shootCoolDown) == 0) {
            boolean isRangeOk = true;
            String[] rangeType = this.type.split(" ");
            int range = Integer.parseInt(rangeType[1]);
            for (int i = range; i > 0; i--)
                if (PlayGround.getSpecifiedUnit(unit.getX(), unit.getY() + i).getZombies().size() > 0) {
                    isRangeOk = false;
                    break;
                }
            if (isRangeOk) {
                if (rangeType[2].equals("threeWay"))
                    threepeaterShoot(unit);
                else if (rangeType[2].equals("normal"))
                    normalShoot(unit);
            }
        }
    }

    private void threepeaterShoot(Unit unit) {
        if (checkForthForZombies(unit)) {
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("forward");
                unit.addToShoots(bullet);
            }
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("up");
                unit.addToShoots(bullet);
            }
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("down");
                unit.addToShoots(bullet);
            }
        }
    }

    private void normalShoot(Unit unit) {
        if (checkForthForZombies(unit))
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("forward");
                unit.addToShoots(bullet);
            }
    }

    private void backShoot(Unit unit) {
        if (checkBackForZombies(unit))
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("backward");
                unit.addToShoots(bullet);
            }
    }

    private void isBomb(Unit unit) {
        String[] bombType = this.type.split(" ");
        if (bombType[1].equals("linear"))
            isLinearBomb(unit);
        else if (bombType[1].equals("circle"))
            isCircleBomb(unit, Integer.parseInt(bombType[2]));
    }

    public void explodeMine(Unit unit) {
        damageZombie(unit, unit.getZombies().get(0));
        unit.killPlant(0);
    }

    private void isLinearBomb(Unit unit) {
        int x = unit.getX();
        for (int y = 1; y < 20; y++)
            for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(x, y).getZombies())
                damageZombie(PlayGround.getSpecifiedUnit(x, y), zombieIterator);
        unit.killPlant(0);
    }

    private void isCircleBomb(Unit unit, int range) {
        int minRow = unit.getX() - range;
        int minColumn = unit.getY() - range;
        int maxRow = unit.getX() + range;
        int maxColumn = unit.getY() + range;
        if (minRow < 0)
            minRow = 0;
        if (minColumn < 0)
            minColumn = 0;
        if (maxRow > 6)
            maxRow = 6;
        if (maxColumn > 19)
            maxColumn = 19;
        for (int i = minRow; i < maxRow; i++)
            for (int j = minColumn; j < maxColumn; j++)
                for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(i, j).getZombies())
                    damageZombie(PlayGround.getSpecifiedUnit(i, j), zombieIterator);
        unit.killPlant(0);

    }

    private void isWall(Unit unit) {
        if (unit.getZombies().size() > 0) {
            damageZombie(unit, unit.getZombies().get(0));
        }
    }

    private void isProducer(Unit unit) {
        if (unit.getZombies().size() > 0)
            damageZombie(unit, unit.getZombies().get(0));
        if (age % shootCoolDown == 0)
            for (int i = 0; i < this.bulletNumber; i++)
                ;
    }

    private void damageZombie(Unit unit, Zombie zombie) {
        zombie.decreaseHealth(this.meleeDamage);
        if (zombie.getHealth() <= 0)
            unit.killZombie(zombie);
    }

    private boolean checkForthForZombies(Unit unit) {
        for (int j = unit.getY(); j < 20; j++) {
            if (PlayGround.getSpecifiedUnit(unit.getX(), j).getZombies().size() > 0)
                break;
            if (j == 19)
                return false;
        }
        return true;
    }

    private boolean checkBackForZombies(Unit unit) {
        for (int j = unit.getY(); j > 0; j--) {
            if (PlayGround.getSpecifiedUnit(unit.getX(), j).getZombies().size() > 0)
                break;
            if (j == 1)
                return false;
        }
        return true;
    }
}
