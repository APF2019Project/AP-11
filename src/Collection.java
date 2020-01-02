import java.util.ArrayList;

public class Collection {


    public static void readyToSelect(String command, int playTypeIndex) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        selectCard(command, playTypeIndex);
    }

    public static void readyToRemove(String command, int playTypeIndex) {
        command = command.replaceFirst("[r,R]emove", "");
        command = command.trim();
        removeCard(command, playTypeIndex);
    }

    public static void selectCard(String cardName, int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            putPlantInDeck(cardName);
        } else if (playTypeIndex == 4) {
            putZombieInDeck(cardName);
        }
    }

    public static void putPlantInDeck(String cardName) {
        if (!Plant.plantExist(cardName)) {
            View.invalidCardName();
        } else if (Account.getMainPlayingAccount().plantsDeck.size() >= 7) {
            View.notEnoughSpacePlantDeck();
        } else if (plantExistsInDeck(cardName)) {
            View.cardAlreadyExistsInDeck("plants", cardName);
        } else if (!plantExistsInCollection(cardName)) {
            View.cardNotInCollection("plants", cardName);
        } else {
            Account.getMainPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant(cardName)));
            Account.getMainPlayingAccount().getPlantsCollection().remove(Plant.getPlant(cardName));
            View.cardAddedToDeck("plants", cardName);
        }
    }

    public static void putZombieInDeck(String cardName) {
        if (!Zombie.zombieExists(cardName)) {
            View.invalidCardName();
        } else if (zombieExistsInDeck(cardName)) {
            View.cardAlreadyExistsInDeck("zombies", cardName);
        } else if (!zombieExistsInCollection(cardName)) {
            View.cardNotInCollection("zombies", cardName);
        } else {
            Account.getMainPlayingAccount().zombiesDeck.add(Zombie.getZombie(cardName));
            Account.getMainPlayingAccount().getZombiesCollection().remove(Zombie.getZombie(cardName));
            View.cardAddedToDeck("zombies", cardName);
        }
    }

    public static void removeCard(String cardName, int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            removeCardFromPlantsDeck(cardName);
        } else if (playTypeIndex == 4) {
            removeCardFromZombiesDeck(cardName);
        }
    }

    public static void removeCardFromPlantsDeck(String cardName) {
        if (!Plant.plantExist(cardName)) {
            View.invalidCardName();
        } else {
            Plant plantSample = null;
            for (Plant plantIterator : Account.getMainPlayingAccount().plantsDeck) {
                if (plantIterator.getName().equals(cardName)) {
                    plantSample = plantIterator;
                    break;
                }
            }
            Account.getMainPlayingAccount().plantsDeck.remove(plantSample);
            Account.getMainPlayingAccount().getPlantsCollection().add(plantSample);
            View.cardRemovedFromDeck(cardName);
        }
    }

    public static void removeCardFromZombiesDeck(String cardName) {
        if (!Zombie.zombieExists(cardName)) {
            View.invalidCardName();
        }
        else {
            Zombie zombieSample = null;
            for (Zombie zombieIterator : Account.getMainPlayingAccount().zombiesDeck) {
                if (zombieIterator.getName().equals(cardName)) {
                    zombieSample = zombieIterator;
                    break;
                }
            }
            Account.getMainPlayingAccount().zombiesDeck.remove(zombieSample);
            Account.getMainPlayingAccount().getZombiesCollection().add(zombieSample);
            View.cardRemovedFromDeck(cardName);
        }
    }

    public static void showHand(int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            View.showPlantsDeck();
        } else if (playTypeIndex == 4) {
            View.showZombiesDeck();
        }
    }

    public static Plant getPlantInDeck(String plantName) {
        for (Plant plantIterator : Account.getMainPlayingAccount().plantsDeck) {
            if (plantIterator.getName().equals(plantName)) {
                return plantIterator;
            }
        }
        return null;
    }

    public static Zombie getZombieInDeck(String zombieName) {
        for (Zombie zombieIterator : Account.getMainPlayingAccount().zombiesDeck) {
            if (zombieIterator.getName().equals(zombieName)) {
                return zombieIterator;
            }
        }
        return null;
    }


    public static boolean plantExistsInCollection(String plantName) {
        for (Plant plantIterator : Account.getMainPlayingAccount().getPlantsCollection()) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zombieExistsInCollection(String zombieName) {
        for (Zombie zombieIterator : Account.getMainPlayingAccount().getZombiesCollection()) {
            if (zombieIterator.getName().equals(zombieName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean plantExistsInDeck(String plantName) {
        for (Plant plantIterator : Account.getMainPlayingAccount().plantsDeck) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zombieExistsInDeck(String zombieName) {
        for (Zombie zombieIterator : Account.getMainPlayingAccount().zombiesDeck) {
            if (zombieIterator.getName().equals(zombieName)) {
                return true;
            }
        }
        return false;
    }

    public static void showCollection(int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            ArrayList<Plant> collectionMinusDeck = new ArrayList<>();
            for (Plant plantIterator : Account.getMainPlayingAccount().getPlantsCollection()) {
                if (plantExistsInDeck(plantIterator.getName())) {
                    continue;
                }
                collectionMinusDeck.add(plantIterator);
            }
            System.out.println("Your collection:");
            View.printNumberedPlantArrayList(collectionMinusDeck);
        } else if (playTypeIndex == 4) {
            ArrayList<Zombie> collectionMinusDeck = new ArrayList<>();
            for (Zombie zombieIterator : Account.getMainPlayingAccount().getZombiesCollection()) {
                if (zombieExistsInDeck(zombieIterator.getName())) {
                    continue;
                }
                collectionMinusDeck.add(zombieIterator);
            }
            System.out.println("Your zombies collection");
            View.printNumberedZombieArrayList(collectionMinusDeck);
        }
    }


    public static void decreaseRespawnsInDeck() {
        for (Plant plantIterator : Account.getMainPlayingAccount().plantsDeck) {
            if (plantIterator.getRespawnTime() >= 1) {
                plantIterator.setRespawnTime(plantIterator.getRespawnTime() - 1);
            }
        }
    }

    public static void clearPlantsDeck() {
        Account.getMainPlayingAccount().plantsDeck.clear();
    }

    public static void clearZombiesDeck() {
        Account.getMainPlayingAccount().zombiesDeck.clear();
    }


    public static void setDefaultPlantsCollection() {
        boolean Peashooter = false;
        boolean SnowPea = false;
        boolean Explode_o_nut = false;
        boolean Scaredy_Shroom = false;
        boolean Cherry_Bomb = false;
        boolean Kernel_Pult = false;
        boolean Sunflower = false;

        String plantIteratorName;
        for (Plant plantIterator : Account.getMainPlayingAccount().getPlantsCollection()) {
            plantIteratorName = plantIterator.getName();
            if (plantIteratorName.equals("Peashooter")) {
                Peashooter = true;
            } else if (plantIteratorName.equals("Snow Pea")) {
                SnowPea = true;
            } else if (plantIteratorName.equals("Explode-o-nut")) {
                Explode_o_nut = true;
            } else if (plantIteratorName.equals("Scaredy-shroom")) {
                Scaredy_Shroom = true;
            } else if (plantIteratorName.equals("Cherry Bomb")) {
                Cherry_Bomb = true;
            } else if (plantIteratorName.equals("Kernel-pult")) {
                Kernel_Pult = true;
            } else if (plantIteratorName.equals("Sunflower")) {
                Sunflower = true;
            }
        }

        if (!Peashooter) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Peashooter")));
        }
        if (!SnowPea) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Snow Pea")));
        }
        if (!Explode_o_nut) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Explode-o-nut")));
        }
        if (!Scaredy_Shroom) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Scaredy-shroom")));
        }
        if (!Cherry_Bomb) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Cherry Bomb")));
        }
        if (!Kernel_Pult) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Kernel-pult")));
        }
        if (!Sunflower) {
            Account.getMainPlayingAccount().getPlantsCollection().add((Plant.getPlant("Sunflower")));
        }
    }


    public static void setDefaultZombiesCollection() {
        boolean ZombieBoolean = false;
        boolean Football_Zombie = false;
        boolean Screen_Door_Zombie = false;
        boolean Zomboni = false;
        boolean Balloon_Zombie = false;
        boolean Bungee_Zombie = false;
        boolean Conehead_Zombie = false;

        String zombieIteratorName;
        for (Zombie zombieIterator : Account.getMainPlayingAccount().getZombiesCollection()) {
            zombieIteratorName = zombieIterator.getName();
            if (zombieIteratorName.equals("Zombie")) {
                ZombieBoolean = true;
            } else if (zombieIteratorName.equals("Football Zombie")) {
                Football_Zombie = true;
            } else if (zombieIteratorName.equals("Screen Door Zombie")) {
                Screen_Door_Zombie = true;
            } else if (zombieIteratorName.equals("Zomboni")) {
                Zomboni = true;
            } else if (zombieIteratorName.equals("Balloon Zombie")) {
                Balloon_Zombie = true;
            } else if (zombieIteratorName.equals("Bungee Zombie")) {
                Bungee_Zombie = true;
            } else if (zombieIteratorName.equals("Conehead Zombie")) {
                Conehead_Zombie = true;
            }
        }

        if (!ZombieBoolean) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zombie"));
        }
        if (!Football_Zombie) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Football Zombie"));
        }
        if (!Screen_Door_Zombie) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Screen Door Zombie"));
        }
        if (!Zomboni) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zomboni"));
        }
        if (!Balloon_Zombie) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Balloon Zombie"));
        }
        if (!Bungee_Zombie) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Bungee Zombie"));
        }
        if (!Conehead_Zombie) {
            Account.getMainPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Conehead Zombie"));
        }
    }


    public static void setDefaultZombieCollectionForSecondPlayer() {
        boolean ZombieBoolean = false;
        boolean Football_Zombie = false;
        boolean Screen_Door_Zombie = false;
        boolean Zomboni = false;
        boolean Balloon_Zombie = false;
        boolean Bungee_Zombie = false;
        boolean Conehead_Zombie = false;

        String zombieIteratorName;
        for (Zombie zombieIterator : Account.getSecondPlayingAccount().getZombiesCollection()) {
            zombieIteratorName = zombieIterator.getName();
            if (zombieIteratorName.equals("Zombie")) {
                ZombieBoolean = true;
            } else if (zombieIteratorName.equals("Football Zombie")) {
                Football_Zombie = true;
            } else if (zombieIteratorName.equals("Screen Door Zombie")) {
                Screen_Door_Zombie = true;
            } else if (zombieIteratorName.equals("Zomboni")) {
                Zomboni = true;
            } else if (zombieIteratorName.equals("Balloon Zombie")) {
                Balloon_Zombie = true;
            } else if (zombieIteratorName.equals("Bungee Zombie")) {
                Bungee_Zombie = true;
            } else if (zombieIteratorName.equals("Conehead Zombie")) {
                Conehead_Zombie = true;
            }
        }

        if (!ZombieBoolean) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zombie"));
        }
        if (!Football_Zombie) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Football Zombie"));
        }
        if (!Screen_Door_Zombie) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Screen Door Zombie"));
        }
        if (!Zomboni) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zomboni"));
        }
        if (!Balloon_Zombie) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Balloon Zombie"));
        }
        if (!Bungee_Zombie) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Bungee Zombie"));
        }
        if (!Conehead_Zombie) {
            Account.getSecondPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Conehead Zombie"));
        }
    }

    public static void clearDecksSetCollections() {
        Collection.clearPlantsDeck();
        Collection.clearZombiesDeck();
        Collection.setDefaultPlantsCollection();
        Collection.setDefaultZombiesCollection();
    }

}

