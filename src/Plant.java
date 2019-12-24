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
    private final boolean isCat;
    private String type;

    public Plant(String name, int health, int sunCost, Shoot bullet, int bulletNumber, int meleeDamage, int respawnCoolDown, int shootCoolDown, Boolean isCat, String type) {
        this.name = name;
        this.health = health;
        this.sunCost = sunCost;
        this.bullet = bullet;
        this.bulletNumber = bulletNumber;
        this.meleeDamage = meleeDamage;
        this.respawnCoolDown = respawnCoolDown;
        this.shootCoolDown = shootCoolDown;
        this.isCat = isCat;
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
        this.isCat = plant.isCat;
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


        new Plant("Peashooter", 2, 2, normalPea, 1, 0, 2, 2, false, "range -1").setRespawnTime(2);
        new Plant("Snow Pea", 3, 3, frozenPea, 1, 0, 3, 3, false, "range -1").setRespawnTime(3);
        new Plant("Cabbage-pult", 2, 2, cabbage, 1, 0, 3, 2, false, "range -1").setRespawnTime(3);
        new Plant("Repeater", 4, 3, normalPea, 2, 0, 4, 3, false, "range -1").setRespawnTime(4);
        new Plant("Threepeater", 5, 4, normalPea, 1, 0, 4, 4, false, "range -1").setRespawnTime(4);
        new Plant("Cactus", 5, 5, normalPea, 1, 1, 4, 2, false, "range -1").setRespawnTime(4);
        new Plant("Gatling Pea", 3, 5, normalPea, 4, 0, 4, 5, false, "range -1").setRespawnTime(4);
        new Plant("Scaredy-shroom", 1, 1, normalPea, 1, 0, 2, 2, false, "range 2").setRespawnTime(2);
        new Plant("Kernel-pult", 2, 3, kernel, 1, 0, 3, 4, false, "range -1").setRespawnTime(3);
        new Plant("Melon-pult", 3, 3, melon, 1, 0, 3, 4, false, "range -1").setRespawnTime(3);
        new Plant("Winter Melon", 3, 4, frozenMelon, 1, 0, 5, 4, false, "range -1").setRespawnTime(5);

        new Plant("Wall-nut", 4, 2, null, 0, 0, 4, 0, false, "wall").setRespawnTime(4);
        new Plant("Explode-o-nut", 3, 4, null, 0, 1, 5, 0, false, "wall").setRespawnTime(5);
        new Plant("Tall-nut", 6, 4, null, 0, 0, 6, 0, false, "wall").setRespawnTime(6);
        new Plant("Potato Mine", 1, 2, null, 0, 100, 3, 0, false, "mine").setRespawnTime(3);
        new Plant("Cherry Bomb", 0, 2, null, 0, 100, 4, 0, false, "circleBomb 1").setRespawnTime(4);
        new Plant("Jalapeno", 0, 4, null, 0, 100, 5, 0, false, "linearBomb").setRespawnTime(5);
        new Plant("Magnet-shroom", 2, 4, null, 0, 0, 4, 0, false, "magnet 1").setRespawnTime(4);
        new Plant("Sunflower", 2, 1, null, 1, 0, 2, 1, false, "producer").setRespawnTime(2);
        new Plant("Twin Sunflower", 2, 3, null, 2, 0, 5, 2, false, "producer").setRespawnTime(5);
    }

    //Getters:

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public static Plant getPlant(String name){
        for (Plant plant : Plant.plants) {
            if (name.equals(plant.getName()))
                return plant;
        }
        return null;
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

    public boolean isCat() {
        return isCat;
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


    public static boolean plantExist(String plantName) {
        for (Plant plantIterator : plants) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }


    //Turn:

    public void turn(Unit unit) {

        this.age++;
        if (unit.getZombies().size() > 0) {
            Zombie zombie = unit.getZombies().get(0);
            zombie.decreaseHealth(this.meleeDamage);
        }
        if (this.type.matches("range [-]?\\d*"))
            if ((this.age % this.shootCoolDown) == 0) {
                boolean isRangeOk = true;
                String rangeString = this.type.replaceFirst("range ", "");
                int range = Integer.parseInt(rangeString);
                for (int i = range; i > 0; i--)
                    if (PlayGround.getSpecifiedUnit(unit.getX(), unit.getY() + i).getZombies().size() > 0){
                        isRangeOk = false;
                        break;
                    }
                if (isRangeOk)
                    for (int i = 0; i < this.bulletNumber; i++){
                        unit.addToShoots(new Shoot(this.bullet));
                    }


            }
    }

}
