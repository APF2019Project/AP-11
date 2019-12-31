 /*  in positioning isWaterProof is important
  *
  * */

 import java.util.ArrayList;


 public class Zombie {
     private static ArrayList<Zombie> zombies = new ArrayList<>();
     private ArrayList<Shoot> shootsRecieved = new ArrayList<>();

     private String name;
     private boolean haveBucketHead; //
     private boolean couldRevertToRegularZombie; //
     private boolean couldDestroyInRow; ////////// handle nashode hanooz
     private boolean RandomPosition; //
     private boolean couldJump;  //
     private int turnThief; //
     private int speed; //
     private int currentSpeed; //
     private int howManyTurnSpeedIsReduced;  //
     private int speedReductionRatio;  //
     private int health; //
     private boolean haveAntiTiq; // mona
     private boolean IsPeaProof; //
     private int shieldStrength; //
     private int damagePower; //
     private boolean IsWaterProof; //
     private boolean haveDuck; //

     public void recievingShoot(Shoot shoot) {
//         if (shoot.isPea() && this.isPeaProof()) {
//             return;
//         }

         if (shoot.getEffectiveTime() != 0) {
             shootsRecieved.add(shoot);
         }

         if (shoot.isPea() && this.getShieldStrength() > 0) {
             this.decreaseShieldStrenght(shoot.getDamage());
         } else {
             this.decreaseHealth(shoot.getDamage());
         }
     }

     private void decreaseShieldStrenght(int damage) {
         this.shieldStrength -= damage;
     }

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

     public static Zombie getZombie(String zombieName) {
         for (Zombie zombieIterator : zombies) {
             if (zombieIterator.getName().equals(zombieName)) {
                 return zombieIterator;
             }
         }
         return null;
     }

     public static void zombiesTurn() {
         for (int i = 0; i < 6; i++) {
             for (int j = 1; j <= 19; j++) {
                 zombiesActionsInSpecifiedUnit(i, j);
             }

//             // debug
//             if (PlayGround.getSpecifiedUnit(i, 0).getZombies().size() > 0){
//                 System.out.println(i + " , " + 0 + " is full ");
//             }
             //end debug

             lawnMoverAction(i);
         }
     }

     public static void zombiesActionsInSpecifiedUnit(int X, int Y) {
         ArrayList<Zombie> tmpArrForDestroyedZombies = new ArrayList<>();
         ArrayList<ZombieByLocation> tmpArrZombieByLocation = new ArrayList<>();

         // not used yet
         ArrayList<Zombie> ConcurrenMEErrorArrayList = new ArrayList<>();
         for (Zombie z: PlayGround.getSpecifiedUnit(X, Y).getZombies()){
             ConcurrenMEErrorArrayList.add(z);
         }
         // not used yet

         for (Zombie zombie : PlayGround.getSpecifiedUnit(X, Y).getZombies()) {
             zombie.zombieAction(X, Y, tmpArrForDestroyedZombies, tmpArrZombieByLocation);
         }
         for (Zombie zombie : tmpArrForDestroyedZombies) {
             if (PlayGround.getSpecifiedUnit(X, Y) != null) {
                 PlayGround.getSpecifiedUnit(X, Y).RemoveFromZombies(zombie);
             }
         }
         for (ZombieByLocation zombieXY: tmpArrZombieByLocation){
             PlayGround.getSpecifiedUnit(zombieXY.X, zombieXY.Y).addToZombies(zombieXY.getZombie());
         }

     }

     public void zombieAction(int X, int Y, ArrayList<Zombie> tmpArrForDestroyedZombies, ArrayList<ZombieByLocation> tmpArrZombieByLocation) {
         this.currentSpeed = this.curSpeedCalculationByAffectingPreviousShoots();
//        positioning
         if (Y == 19 && this.isRandomPosition()) {
             int newX = PlayGround.randomPositionX();
             int newY = PlayGround.randomPositiomY();
             PlayGround.getSpecifiedUnit(newX, newY).addToZombies(this);
             tmpArrForDestroyedZombies.add(this);
             return;
             // remove az 19
         }

         int newY = this.distinationFinderAndDestroyedShootRemover(X, Y);
         if (newY == Integer.MAX_VALUE) {
             tmpArrForDestroyedZombies.add(this);
             return;
         }

         if (this.getTurnThief() != Integer.MAX_VALUE) {
             if (this.getTurnThief() == 0) {
                 this.TurnToThief(X, Y);
                 tmpArrForDestroyedZombies.add(this);
                 return;
             } else
                 this.setTurnThief(this.getTurnThief() - 1);
         }
         tmpArrForDestroyedZombies.add(this);
         tmpArrZombieByLocation.add(new ZombieByLocation(X, newY, this));
//         Unit unitNew = PlayGround.getSpecifiedUnit(X, newY);
//         unitNew.addToZombies(this);
         this.damagingPlantIfExist(PlayGround.getSpecifiedUnit(X, newY));


     }

     private void damagingPlantIfExist(Unit unitNew) {
         Plant[] plants = unitNew.getPlants();
         if (plants[0] == null) return;
         plants[0].decreaseHealth(this.getDamagePower());
         if (plants[0].getHealth() <= 0) {
             plants[0] = null;
         }
         return;
     }

     //  this can find the distination
     public int distinationFinderAndDestroyedShootRemover(int X, int Y) {
         boolean baseConditionToMove = true;
         int basedY = Y;
         while (baseConditionToMove) {
             System.out.println("1");
             ArrayList<Shoot> shootsTmp = new ArrayList<>();
             Unit thisUnit = PlayGround.getSpecifiedUnit(X, Y);
             for (Shoot shoot : thisUnit.getShoots()) {
                 if (this.getHealth() > 0) {
                     System.out.println("2");
                     this.recievingShoot(shoot);
                     shootsTmp.add(shoot);
                 } else {
                     Y = Integer.MAX_VALUE;// zombie is dead and gone to heaven far from home
                 }
             }
             for (Shoot shoot : shootsTmp) {
                 thisUnit.RemoveFromShoots(shoot);
//                 System.out.println("shoot location is: 'removed' " + X + " " + Y);
             }
             baseConditionToMove = couldZombieGoToNextUnit(X, Y, basedY);
             if (!baseConditionToMove) {
                 return Y;
             }
             if (Y == Integer.MAX_VALUE) {
                 return Integer.MAX_VALUE;
             }
             Y--;
         }
         return Y;
     }

     public static void lawnMoverAction(int X) {
         Unit[][] playGround = PlayGround.getUnits();
         if (!playGround[X][0].getHaveChamanZan())
             return;
         if (playGround[X][0].getZombies().size() == 0) {
             return;
         }
         playGround[X][0].setHaveLawnMover(false);
         for (int i = 0; i < 20; i++) {
             playGround[X][i].removeAllPlants();
             playGround[X][i].removeAllZombies();
         }
     }

     private boolean couldZombieGoToNextUnit(int X, int Y, int basedY) {
         boolean condition;
         int farestUnitDidicatedBySpeed = basedY - this.currentSpeed;
         Plant[] plant = PlayGround.getSpecifiedUnit(X, Y).getPlants();
         if (this.isCouldJump()) {
             condition = (Y >= 1) && (Y > farestUnitDidicatedBySpeed);
             return condition;
         } else {
             condition = (Y >= 1) && (plant[0] == null && Y > farestUnitDidicatedBySpeed);
             return condition;
         }
     }

     private void TurnToThief(int X, int Y) {
         PlayGround.getSpecifiedUnit(X, Y).removeAllPlants();
     }

     public int curSpeedCalculationByAffectingPreviousShoots() { // return curSpeed
         int currentSpeed = this.getSpeed(); // bayad avval bemune
         ArrayList<Shoot> tmp = new ArrayList<>();
         for (Shoot shoot : shootsRecieved) {
             if (shoot.getEffectiveTime() == 0)
                 tmp.add(shoot);
             else {
                 shoot.setEffectiveTime(shoot.getEffectiveTime() - 1);
                 if (currentSpeed > shoot.getBufFactor() * this.getSpeed()) {
                     currentSpeed = (int) Math.floor(shoot.getBufFactor() * this.getSpeed());
                 }
             }
         }
         for (Shoot shoot : tmp) {
             shootsRecieved.remove(shoot);
         }
         return currentSpeed;
     }

     public static Zombie initializeRegularZombie() {
         Zombie zRegular = new Zombie("Zombie", false, false,
                 false, false, false, false,
                 false, false, false, Integer.MAX_VALUE, 2, 0,
                 1, 2, 0, 1);
         return zRegular;
     }

     public int getPrice() {
         return (1 + this.getSpeed()) * this.getHealth() * 10;
     }

     public void turnToRegularZombie(int X, int Y) {
         PlayGround.getSpecifiedUnit(X, Y).RemoveFromZombies(this);
         Zombie zRegular = Zombie.initializeRegularZombie();
         PlayGround.getSpecifiedUnit(X, Y).addToZombies(zRegular);
     }

     public static Zombie cloningZombieByName(String name) {
         Zombie zombie = null;
         for (Zombie z : zombies) {
             if (name.equals(z.getName())) {
                 zombie = cloningZombie(z);
             }
         }
         return zombie;
     }

     public static Zombie cloningZombie(Zombie zombie) {
         Zombie returnZom = new Zombie(zombie.getName(), zombie.isHaveAntiTiq(), zombie.isHaveBucketHead(), zombie.isCouldRevertToRegularZombie(),
                 zombie.isCouldDestroyInRow(), zombie.isWaterProof(), zombie.isPeaProof(), zombie.isRandomPosition(), zombie.isHaveDuck(), zombie.isCouldJump(), zombie.getTurnThief(),
                 zombie.getSpeed(), zombie.getHowManyTurnSpeedIsReduced(), zombie.getSpeedReductionRatio(), zombie.getHealth(), zombie.getShieldStrength(), zombie.getDamagePower());
         return returnZom;
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

     public static boolean zombieExists(String zombieName) {
         for (Zombie zombieIterator : zombies) {
             if (zombieIterator.getName().equals(zombieName)) {
                 return true;
             }
         }
         return false;
     }

     public static ArrayList<Zombie> getZombies() {
         return zombies;
     }

     public static void addZombies(Zombie zombie) {
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

     public void decreaseHealth(int damage) { // in che joor encapsulationiye marde momen??
         this.health -= damage;
     }

     public int getCurrentSpeed() {
         return currentSpeed;
     }

     public void setCurrentSpeed(int currentSpeed) {
         this.currentSpeed = currentSpeed;
     }
 }