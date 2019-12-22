import java.util.ArrayList;

public class Zombie {
    private static ArrayList<Zombie> zombies = new ArrayList<>();
    private String name;
    private boolean haveAntiTiq;
    private boolean haveBucketHead;
    private boolean couldRevertToRegularZombie;
    private boolean couldDestroyInRow;
    private boolean IsWaterProof;
    private boolean IsPeaProof;
    private boolean RandomPosition;
    private boolean haveDuck;
    private boolean couldJump;
    private int turnThief;
    private int speed;
    private int howManyTurnSpeedIsReduced;
    private int speedReductionRatio;
    private int health;
    private int shieldStrength;
    private int damagePower;
    private int effectiveTimeBuff; //
    private boolean isJumper; //
    private boolean mustBeInWater;
    private boolean IsPartabeProof;

    static {
        Zombie zRegular = Zombie.initializeRegularZombie();
        addZombies(zRegular);

        Zombie zFootball = Zombie.initializeRegularZombie();
        zFootball.setName("Football Zombie");
        zFootball.setHaveAntiTiq(true);
        zFootball.setHealth(4);
        zFootball.setSpeed(3);
        addZombies(zFootball);

        Zombie zBucket = Zombie.initializeRegularZombie();
        zBucket.setName("Buckethead Zombie");
        zBucket.setHaveBucketHead(true);
        zBucket.setHealth(3);
        addZombies(zBucket);

        Zombie zConeHead = Zombie.initializeRegularZombie();
        zConeHead.setName("Conehead Zombie");
        zConeHead.setHealth(3);
        addZombies(zConeHead);

        Zombie zOmboni = Zombie.initializeRegularZombie();
        zOmboni.setName("Zomboni");
        zOmboni.setCouldRevertToRegularZombie(true);
        zOmboni.setHealth(3);
        addZombies(zOmboni);

        Zombie zCatapult = Zombie.initializeRegularZombie();
        zCatapult.setName("Catapult Zombie");
        zCatapult.setHealth(3);
        addZombies(zCatapult);

        Zombie zBungee = Zombie.initializeRegularZombie();
        zBungee.setName("Bungee Zombie");
        zBungee.setRandomPosition(true);
        zBungee.setEffectiveTimeBuff(3);
        zBungee.setHealth(3);
        zBungee.setSpeed(0);
        addZombies(zBungee);

        Zombie zBalloon = Zombie.initializeRegularZombie();
        zBalloon.setName("Balloon Zombie");
        zBalloon.setPeaProof(true);
        zBalloon.setHealth(3);
        addZombies(zBalloon);

        Zombie zNewspaper = Zombie.initializeRegularZombie();
        zNewspaper.setName("Newspaper Zombie");
        zNewspaper.setShieldStrength(2);
        addZombies(zNewspaper);

        Zombie zTarget = Zombie.initializeRegularZombie();
        zNewspaper.setName("Target Zombie");
        zTarget.setShieldStrength(3);
        zTarget.setHealth(3);
        addZombies(zTarget);

        Zombie zScreen = Zombie.initializeRegularZombie();
        zScreen.setName("Screen Door Zombie");
        zScreen.setShieldStrength(4);
        addZombies(zScreen);

        Zombie zGiga = Zombie.initializeRegularZombie();
        zGiga.setHealth(6);
        zGiga.setSpeed(1);
        zGiga.setName("Giga-gargantuar");
        zGiga.setDamagePower(Integer.MAX_VALUE);
        addZombies(zGiga);

        Zombie zPogo = Zombie.initializeRegularZombie();
        zPogo.setIsJumper(true);
        addZombies(zPogo);

        Zombie zSnorkel = Zombie.initializeRegularZombie();
        zSnorkel.setName("Snorkel Zombie");
        zSnorkel.setMustBeInWater(true);
        addZombies(zSnorkel);


        Zombie zDolphin = Zombie.initializeRegularZombie();
        zDolphin.setName("Dolphin Rider Zombie");
        zDolphin.setMustBeInWater(true);
        zDolphin.setShieldStrength(2);
        addZombies(zDolphin);
    }

    public static Zombie initializeRegularZombie(){
        Zombie zRegular = new Zombie("Zombie", false, false, false,
                false, false, false, false,
                false, false, false, Integer.MAX_VALUE, 2, 0,
                1, 2, 0, 1, Integer.MAX_VALUE, false, false);
        return zRegular;
    }


    public Zombie(String name, boolean isPartabeProof, boolean haveAntiTiq, boolean haveBucketHead, boolean couldRevertToRegularZombie,
                  boolean couldDestroyInRow, boolean isWaterProof, boolean isPeaProof, boolean randomPosition, boolean haveDuck, boolean couldJump,
                  int turnThief, int speed, int howManyTurnSpeedIsReduced, int speedReductionRatio, int health, int shieldStrength, int damagePower,
                  int effectiveTimeBuff, boolean isJumper, boolean mustBeInWater) {

        this.speedReductionRatio = speedReductionRatio;
        this.IsPartabeProof = isPartabeProof;
        this.name = name;
        this.haveAntiTiq = haveAntiTiq;
        this.haveBucketHead = haveBucketHead;
        this.couldRevertToRegularZombie = couldRevertToRegularZombie;
        this.couldDestroyInRow = couldDestroyInRow;
        this.IsWaterProof = isWaterProof;
        this.IsPeaProof = isPeaProof;
        this.RandomPosition = randomPosition;
        this.haveDuck = haveDuck;
        this.couldJump = couldJump;
        this.turnThief = turnThief;
        this.speed = speed;
        this.howManyTurnSpeedIsReduced = howManyTurnSpeedIsReduced;
        this.health = health;
        this.shieldStrength = shieldStrength;
        this.damagePower = damagePower;
        this.effectiveTimeBuff = effectiveTimeBuff;
        this.isJumper = isJumper;
        this.mustBeInWater = mustBeInWater;
    }


    public static ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public static void addZombies(Zombie zombie){
        zombies.add(zombie);
    }

    public static void removeZombies(Zombie zombie) {
        zombies.remove(zombie);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHaveAntiTiq() {
        return haveAntiTiq;
    }

    public void setHaveAntiTiq(boolean haveAntiTiq) {
        this.haveAntiTiq = haveAntiTiq;
    }

    public boolean isHaveBucketHead() {
        return haveBucketHead;
    }

    public void setHaveBucketHead(boolean haveBucketHead) {
        this.haveBucketHead = haveBucketHead;
    }

    public boolean isCouldRevertToRegularZombie() {
        return couldRevertToRegularZombie;
    }

    public void setCouldRevertToRegularZombie(boolean couldRevertToRegularZombie) {
        this.couldRevertToRegularZombie = couldRevertToRegularZombie;
    }

    public boolean isCouldDestroyInRow() {
        return couldDestroyInRow;
    }

    public void setCouldDestroyInRow(boolean couldDestroyInRow) {
        this.couldDestroyInRow = couldDestroyInRow;
    }

    public boolean isWaterProof() {
        return IsWaterProof;
    }

    public void setWaterProof(boolean waterProof) {
        IsWaterProof = waterProof;
    }

    public boolean isPeaProof() {
        return IsPeaProof;
    }

    public void setPeaProof(boolean peaProof) {
        IsPeaProof = peaProof;
    }

    public boolean isRandomPosition() {
        return RandomPosition;
    }

    public void setRandomPosition(boolean randomPosition) {
        RandomPosition = randomPosition;
    }

    public boolean isHaveDuck() {
        return haveDuck;
    }

    public void setHaveDuck(boolean haveDuck) {
        this.haveDuck = haveDuck;
    }

    public boolean isCouldJump() {
        return couldJump;
    }

    public void setCouldJump(boolean couldJump) {
        this.couldJump = couldJump;
    }

    public int getTurnThief() {
        return turnThief;
    }

    public void setTurnThief(int turnThief) {
        this.turnThief = turnThief;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHowManyTurnSpeedIsReduced() {
        return howManyTurnSpeedIsReduced;
    }

    public void setHowManyTurnSpeedIsReduced(int howManyTurnSpeedIsReduced) {
        this.howManyTurnSpeedIsReduced = howManyTurnSpeedIsReduced;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public int getDamagePower() {
        return damagePower;
    }

    public void setDamagePower(int damagePower) {
        this.damagePower = damagePower;
    }

    public int getEffectiveTimeBuff() {
        return effectiveTimeBuff;
    }

    public void setEffectiveTimeBuff(int effectiveTimeBuff) {
        this.effectiveTimeBuff = effectiveTimeBuff;
    }

    public int getSpeedReductionRatio() {
        return speedReductionRatio;
    }

    public void setSpeedReductionRatio(int speedReductionRatio) {
        this.speedReductionRatio = speedReductionRatio;
    }

    public boolean getMustBeInWater() {
        return mustBeInWater;
    }

    public void setMustBeInWater(boolean mustBeInWater) {
        this.mustBeInWater = mustBeInWater;
    }

    public boolean getIsJumper() {
        return isJumper;
    }

    public void setIsJumper(boolean jumper) {
        this.isJumper = jumper;
    }

    public boolean isPartabeProof() {
        return IsPartabeProof;
    }

    public void setPartabeProof(boolean partabeProof) {
        IsPartabeProof = partabeProof;
    }
}
