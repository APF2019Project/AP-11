import java.util.ArrayList;

public class Plant {

    // added in network phase:
    public int QuantityInShop = 5;

    public static ArrayList<Plant> plants = new ArrayList<>();

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
    private int shootTime;
    private boolean isSplitPea = false;
    private final boolean isCattail;
    private final boolean canBePlantedInWater;
    private String type;

    public Plant(String name, int health, int sunCost, Shoot bullet, int bulletNumber, int meleeDamage, int respawnCoolDown, int shootCoolDown, Boolean isCattail, boolean canBePlantedInWater, String type) {
        this.name = name;
        this.health = health;
        this.sunCost = sunCost;
        this.bullet = bullet;
        this.bulletNumber = bulletNumber;
        this.meleeDamage = meleeDamage;
        this.respawnCoolDown = respawnCoolDown;
        this.shootCoolDown = shootCoolDown;
        this.isCattail = isCattail;
        this.canBePlantedInWater = canBePlantedInWater;
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
        this.canBePlantedInWater = plant.canBePlantedInWater;
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


        new Plant("Peashooter", 2, 2, normalPea, 1, 0, 2, 2, false, false, "range -1 normal");
        new Plant("Snow Pea", 3, 3, frozenPea, 1, 0, 3, 3, false, false, "range -1 normal");
        new Plant("Cabbage-pult", 2, 2, cabbage, 1, 0, 3, 2, false, false, "range -1 normal");
        new Plant("Repeater", 4, 3, normalPea, 2, 0, 4, 3, false, false, "range -1 normal");
        new Plant("Threepeater", 5, 4, normalPea, 1, 0, 4, 4, false, false, "range -1 threeWay");
        new Plant("Cactus", 5, 5, normalPea, 1, 1, 4, 2, false, false, "range -1 normal");
        new Plant("Gatling Pea", 3, 5, normalPea, 4, 0, 4, 5, false, false, "range -1 normal");
        new Plant("Scaredy-shroom", 1, 1, normalPea, 1, 0, 2, 2, false, false, "range 2 normal");
        new Plant("Kernel-pult", 2, 3, kernel, 1, 0, 3, 4, false, false, "range -1 normal");
        new Plant("Melon-pult", 3, 3, melon, 1, 0, 3, 4, false, false, "range -1 normal");
        new Plant("Winter Melon", 3, 4, frozenMelon, 1, 0, 5, 4, false, false, "range -1 normal");

        new Plant("Wall-nut", 4, 2, null, 0, 0, 4, 0, false, false, "wall");
        new Plant("Explode-o-nut", 3, 4, null, 0, 1, 5, 0, false, false, "wall");
        new Plant("Tall-nut", 6, 4, null, 0, 0, 6, 0, false, false, "wall");
        new Plant("Potato Mine", 1, 2, null, 0, 100, 3, 0, false, false, "bomb mine");
        new Plant("Cherry Bomb", 0, 2, null, 0, 100, 4, 0, false, false, "bomb circle 1");
        new Plant("Jalapeno", 0, 4, null, 0, 100, 5, 0, false, false, "bomb linear");
        new Plant("Magnet-shroom", 2, 4, null, 0, 0, 4, 0, false, false, "magnet 1");
        new Plant("Sunflower", 2, 1, null, 1, 0, 2, 1, false, false, "producer");
        new Plant("Twin Sunflower", 2, 3, null, 2, 0, 5, 2, false, false, "producer");

        //  new Plant("Split Pea", 3, 4, normalPea, , 0, 4, , false, "range -1 twoWay")
        new Plant("Lily Pad", 1, 0, null, 0, 0, 1, 0, false, true, "stage");
        new Plant("Tangle Kelp", 0, 3, null, 0, 100, 3, 0, false, true, "bomb mine");
        new Plant("Cattail", 3, 5, null, 0, 100, 5, 0, true, false, "melee");
        new Plant("Split Pea", 3, 4, null, 0, 0, 4, 0, false, false, "fuck").setSplitPea(true);
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

    public static void plantsTurn() {
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 20; j++) {
                Unit unit = PlayGround.getSpecifiedUnit(i, j);
                Plant plant = unit.getPlants()[0];
                if (plant != null)
                    plant.turn(unit);
            }
    }

    //Getters:


    public int getQuantityInShop() {
        return QuantityInShop;
    }

    public void setQuantityInShop(int quantityInShop) {
        QuantityInShop = quantityInShop;
    }

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

    public int getShootTime() {
        return shootTime;
    }

    public boolean isCanBePlantedInWater() {
        return canBePlantedInWater;
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

    private void setSplitPea(boolean bool) {
        this.isSplitPea = bool;
    }

    private void setShootTime(int time) {
        this.shootTime = time;
    }

    public int getPrice() {
        return (this.sunCost * this.health * this.respawnCoolDown + 1);
    }


    //Turn:

    public void turn(Unit unit) {
        if (isSplitPea)
            handleSplitPea(unit);

        this.age++;
        if (this.shootTime > 0)
            this.shootTime--;
        if (this.isCattail)
            isMelee(unit);
        if (this.type.matches("range [-]?(\\d+) (\\w+)")) {
            isRange(unit);
        } else if (this.type.matches("bomb .+"))
            isBomb(unit);
        else if (this.type.equals("wall"))
            isWall(unit);
        else if (this.type.equals("producer"))
            isProducer(unit);
        else if (this.type.matches("magnet \\d+"))
            isMagnet(unit);

    }

    private void isRange(Unit unit) {
        if (unit.getZombies().size() > 0)
            damageZombie(unit, unit.getZombies().get(0));
        if (this.shootTime == 0) {
            boolean isRangeOk = true;
            String[] rangeType = this.type.split(" ");
            int range = Integer.parseInt(rangeType[1]);
            for (int i = range; i > 0; i--)
                if (PlayGround.getSpecifiedUnit(unit.getX(), unit.getY() + i).getZombies().size() > 0) {
                    isRangeOk = false;
                    break;
                }
            if (isRangeOk) {
                if (rangeType[2].equals("threeWay")) {
                    threepeaterShoot(unit);
                } else if (rangeType[2].equals("normal")) {
                    normalShoot(unit);
                }
            }
        }
    }

    private void threepeaterShoot(Unit unit) {
        if (checkForthForZombies(unit)) {
            normalShoot(unit);
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
            this.shootTime = this.shootCoolDown;
        }
    }

    private void normalShoot(Unit unit) {
        if (checkForthForZombies(unit)) {
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("forward");
                unit.addToShoots(bullet);
            }
            this.shootTime = this.shootCoolDown;
        }
    }

    private void backShoot(Unit unit) {
        if (checkBackForZombies(unit)) {
            for (int i = 0; i < this.bulletNumber; i++) {
                Shoot bullet = new Shoot(this.bullet);
                bullet.setDirection("backward");
                unit.addToShoots(bullet);
            }
            this.shootTime = this.shootCoolDown;
        }
    }

    private void isBomb(Unit unit) {
        String[] bombType = this.type.split(" ");
        if (bombType[1].equals("linear"))
            isLinearBomb(unit);
        else if (bombType[1].equals("circle"))
            isCircleBomb(unit, Integer.parseInt(bombType[2]));
    }

    public boolean explodeMine(Unit unit) {
        if (this.type.matches("bomb mine"))
            if (this.age >= 1) {
                unit.killPlant(0);
                return true;
            }
        return false;
    }

    private void explodeZombie(Zombie zombie) {
        zombie.decreaseHealth(this.meleeDamage);
    }

    private void isLinearBomb(Unit unit) {
        int x = unit.getX();
        for (int y = 1; y < 20; y++)
            for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(x, y).getZombies())
                damageZombie(PlayGround.getSpecifiedUnit(x, y), zombieIterator);
        unit.killPlant(0);
    }

    private void isCircleBomb(Unit unit, int range) {
        int[] limits = setCircleLimits(unit.getX(), unit.getY(), range);
        int minRow = limits[0];
        int minColumn = limits[1];
        int maxRow = limits[2];
        int maxColumn = limits[3];
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
                Day.increaseSun();
    }

    private void isMelee(Unit unit) {
        int[][] distances = new int[6][20];
        int x = unit.getX();
        int y = unit.getY();
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 20; j++)
                if (PlayGround.getSpecifiedUnit(i, j).getZombies().size() > 0)
                    distances[i][j] = (int) Math.pow(i - x, 2) + (int) Math.pow(j - y, 2);
                else
                    distances[i][j] = 400;
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 20; j++)
                if (distances[i][j] < distances[x][y]) {
                    x = i;
                    y = j;
                }
        unit = PlayGround.getSpecifiedUnit(x, y);
        damageZombie(unit, unit.getZombies().get(0));
    }

    private void isMagnet(Unit unit) {
        String[] rangeString = this.type.split(" ");
        int range = Integer.parseInt(rangeString[1]);
        int[] limits = setCircleLimits(unit.getX(), unit.getY(), range);
        int minRow = limits[0];
        int minColumn = limits[1];
        int maxRow = limits[2];
        int maxColumn = limits[3];
        for (int i = minRow; i < maxRow; i++)
            for (int j = minColumn; j < maxColumn; j++)
                for (Zombie zombieIterator : PlayGround.getSpecifiedUnit(i, j).getZombies()){
                    if (zombieIterator.getName().equals("Buckethead Zombie"))
                        zombieIterator.setHaveBucketHead(false);
                    else if (zombieIterator.getName().equals("Screen Door Zombie"))
                        zombieIterator.setShieldStrength(0);
                }


    }

    private void damageZombie(Unit unit, Zombie zombie) {
        zombie.decreaseHealth(this.meleeDamage);
        if (zombie.getHealth() <= 0) {
            unit.removeFromZombies(zombie);
            Unit.increaseRecordKilledZombies(1);
        }
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

    private int[] setCircleLimits(int X, int Y, int range) {
        int[] limits = new int[4];
        limits[0] = X - range;
        limits[1] = Y - range;
        limits[2] = X + range;
        limits[3] = Y + range;
        if (limits[0] < 0)
            limits[0] = 0;
        if (limits[1] < 0)
            limits[1] = 0;
        if (limits[2] > 6)
            limits[2] = 6;
        if (limits[3] > 19)
            limits[3] = 19;
        return limits;
    }

    private void handleSplitPea(Unit unit) {
        if (this.age == 0) {
            Plant forwardPlant = new Plant(Plant.getPlant("Peashooter"));
            forwardPlant.normalShoot(unit);
            this.age = forwardPlant.shootTime;
        } else
            this.age--;
        if (this.shootTime == 0) {
            Plant backwardPlant = new Plant(Plant.getPlant("Repeater"));
            backwardPlant.backShoot(unit);
            this.shootTime = backwardPlant.shootTime;
        } else
            this.shootTime--;
    }
}
