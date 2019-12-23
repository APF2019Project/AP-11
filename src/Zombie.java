import com.sun.xml.internal.bind.v2.TODO;
 /*  in positioning isWaterProof is important
 *
 * */

import java.util.ArrayList;


public class Zombie {
    private static ArrayList<Zombie> zombies = new ArrayList<>();

    private String name;
    private boolean haveBucketHead;
    private boolean couldRevertToRegularZombie; //
    private boolean couldDestroyInRow;
    private boolean RandomPosition; //
    private boolean couldJump;
    private int turnThief; //
    private int speed; //
    private int howManyTurnSpeedIsReduced;  //
    private int speedReductionRatio;  //
    private int health;
    private boolean haveAntiTiq;
    private boolean IsPeaProof;
    private int shieldStrength;
    private int damagePower; //
    private boolean IsWaterProof;
    private boolean haveDuck;

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
        zBungee.setTurnThief(3);
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
        zTarget.setName("Target Zombie");
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
        zPogo.setName("Pogo Zombie");
        zPogo.setCouldJump(true);
        addZombies(zPogo);

        Zombie zSnorkel = Zombie.initializeRegularZombie();
        zSnorkel.setName("Snorkel Zombie");
        zSnorkel.setWaterProof(true);
        addZombies(zSnorkel);


        Zombie zDolphin = Zombie.initializeRegularZombie();
        zDolphin.setName("Dolphin Rider Zombie");
        zDolphin.setWaterProof(true);
        zDolphin.setShieldStrength(2);
        addZombies(zDolphin);
    }

    public void zombieAction(int X, int Y){
        int currentSpeed = Math.floorDiv(this.getSpeed(), this.speedReductionRatio);
//        positioning
        if (Y == 20 && this.isRandomPosition()){
            int newX = PlayGround.randomPositionX();
            int newY = PlayGround.randomPositiomY();
            PlayGround.getSpecifiedUnit(newX, newY).addToZombies(this);
        }


//        TODO function ;

        if (this.getHowManyTurnSpeedIsReduced() != 0)
            this.setHowManyTurnSpeedIsReduced(this.getHowManyTurnSpeedIsReduced() - 1);
        else
            setSpeedReductionRatio(1);

        if (this.getTurnThief() != Integer.MAX_VALUE){
            if (this.getTurnThief() == 0)
                this.TurnToThief();
            else
                this.setTurnThief(this.getTurnThief() - 1);
        }



    }

    private void TurnToThief() {
        TODO burglaring;
    }

    public static Zombie initializeRegularZombie(){
        Zombie zRegular = new Zombie("Zombie",false, false,
                false, false, false, false,
                false, false, false, Integer.MAX_VALUE, 2, 0,
                1, 2, 0, 1);
        return zRegular;
    }

    public int getPrice(){
        return (1 + this.getSpeed()) * this.getHealth() * 10;
    }

    public boolean killZombie(){
        if (this.getHealth() <= 0)
            return true;

        return false;
    }

    public void turnToRegularZombie(int X, int Y){
        PlayGround.getSpecifiedUnit(X, Y).RemoveFromZombies(this);
        Zombie zRegular = Zombie.initializeRegularZombie();
        PlayGround.getSpecifiedUnit(X, Y).addToZombies(zRegular);
    }


    public Zombie(String name, boolean haveAntiTiq, boolean haveBucketHead, boolean couldRevertToRegularZombie,
                  boolean couldDestroyInRow, boolean isWaterProof, boolean isPeaProof, boolean randomPosition, boolean haveDuck, boolean couldJump,
                  int turnThief, int speed, int howManyTurnSpeedIsReduced, int speedReductionRatio, int health, int shieldStrength, int damagePower) {

        this.speedReductionRatio = speedReductionRatio;
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

    public int getSpeedReductionRatio() {
        return speedReductionRatio;
    }

    public void setSpeedReductionRatio(int speedReductionRatio) {
        this.speedReductionRatio = speedReductionRatio;
    }

    public void decreaseHealth(int damage){
        this.health -= damage;
    }
}
