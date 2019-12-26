import java.util.ArrayList;
import java.util.Scanner;

public class Collection {

    static void collectionMenu(int playTypeIndex) { // Collection Menu index: -5

        Scanner scanner = new Scanner(System.in);
        String command;
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- Collection Menu ---\nEnter command:");
            command = scanner.nextLine();

            if (command.toLowerCase().matches("select (.+)")) {
                readyToSelect(command, playTypeIndex);
                continue;
            } else if (command.toLowerCase().matches("remove (.+)")) {
                readyToRemove(command, playTypeIndex);
                continue;
            }
            switch (command.toLowerCase()) {
                case "show hand":
                    showHand(playTypeIndex);
                    break;
                case "show collection":
                    showCollection(playTypeIndex);
                    break;
                case "play":
                    goToPlayByPlayType(playTypeIndex);
                    break;
                case "exit": // Going back to Play Menu
                    View.goingBackTo(-4);
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-5);
                    break;
                default:
                    View.invalidCommand(-5);
                    break;
            }
        }
    }

    private static void readyToSelect(String command, int playTypeIndex) {
        command = command.replaceFirst("[s,S]elect", "");
        command = command.trim();
        selectCard(command, playTypeIndex);
    }

    private static void readyToRemove(String command, int playTypeIndex) {
        command = command.replaceFirst("[r,R]emove", "");
        command = command.trim();
        removeCard(command, playTypeIndex);
    }

    private static void selectCard(String cardName, int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            putPlantInDeck(cardName);
        } else if (playTypeIndex == 4) {
            putZombieInDeck(cardName);
        }
    }

    private static void putPlantInDeck(String cardName) {
        if (!Plant.plantExist(cardName)) {
            View.invalidCardName();
        } else if (Account.getPlayingAccount().plantsDeck.size() >= 7) {
            View.notEnoughSpacePlantDeck();
        } else if (plantExistsInDeck(cardName)) {
            View.cardAlreadyExistsInDeck("plants", cardName);
        } else if (!plantExistsInCollection(cardName)) {
            View.cardNotInCollection("plants", cardName);
        } else {
            Account.getPlayingAccount().plantsDeck.add(Plant.getPlant(cardName));
            Account.getPlayingAccount().getPlantsCollection().remove(Plant.getPlant(cardName));
            View.cardAddedToDeck("plants", cardName);
        }
    }

    private static void putZombieInDeck(String cardName) {
        if (!Zombie.zombieExists(cardName)) {
            View.invalidCardName();
        } else if (zombieExistsInDeck(cardName)) {
            View.cardAlreadyExistsInDeck("zombies", cardName);
        } else if (!zombieExistsInCollection(cardName)) {
            View.cardNotInCollection("zombies", cardName);
        } else {
            Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie(cardName));
            Account.getPlayingAccount().getZombiesCollection().remove(Zombie.getZombie(cardName));
            View.cardAddedToDeck("zombies", cardName);
        }
    }

    private static void removeCard(String cardName, int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            removeCardFromPlantsDeck(cardName);
        } else if (playTypeIndex == 4) {
            removeCardFromZombiesDeck(cardName);
        }
    }

    private static void removeCardFromPlantsDeck(String cardName) {
        if (!Plant.plantExist(cardName)) {
            View.invalidCardName();
        } else {
            Plant plantSample = null;
            for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
                if (plantIterator.getName().equals(cardName)) {
                    plantSample = plantIterator;
                    break;
                }
            }
            Account.getPlayingAccount().plantsDeck.remove(plantSample);
            Account.getPlayingAccount().getPlantsCollection().add(plantSample);
            View.cardRemovedFromDeck(cardName);
        }
    }

    private static void removeCardFromZombiesDeck(String cardName) {
        if (!Zombie.zombieExists(cardName)) {
            View.invalidCardName();
        } else {
            Zombie zombieSample = null;
            for (Zombie zombieIterator : Account.getPlayingAccount().zombiesDeck) {
                if (zombieIterator.getName().equals(cardName)) {
                    zombieSample = zombieIterator;
                    break;
                }
            }
            Account.getPlayingAccount().zombiesDeck.remove(zombieSample);
            Account.getPlayingAccount().getZombiesCollection().add(zombieSample);
            View.cardRemovedFromDeck(cardName);
        }
    }

    private static void showHand(int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            View.showPlantsDeck();
        } else if (playTypeIndex == 4) {
            View.showZombiesDeck();
        }
    }


    public static boolean plantExistsInCollection(String plantName) {
        for (Plant plantIterator : Account.getPlayingAccount().getPlantsCollection()) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zombieExistsInCollection(String zombieName) {
        for (Zombie zombieIterator : Account.getPlayingAccount().getZombiesCollection()) {
            if (zombieIterator.getName().equals(zombieName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean plantExistsInDeck(String plantName) {
        for (Plant plantIterator : Account.getPlayingAccount().plantsDeck) {
            if (plantIterator.getName().equals(plantName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zombieExistsInDeck(String zombieName) {
        for (Zombie zombieIterator : Account.getPlayingAccount().zombiesDeck) {
            if (zombieIterator.getName().equals(zombieName)) {
                return true;
            }
        }
        return false;
    }

    private static void showCollection(int playTypeIndex) {
        if (playTypeIndex == 1 || playTypeIndex == 2) {
            ArrayList<Plant> collectionMinusDeck = new ArrayList<>();
            for (Plant plantIterator : Account.getPlayingAccount().getPlantsCollection()) {
                if (plantExistsInDeck(plantIterator.getName())) {
                    continue;
                }
                collectionMinusDeck.add(plantIterator);
            }
            View.printNumberedPlantArrayList(collectionMinusDeck);
        } else if (playTypeIndex == 4) {
            ArrayList<Zombie> collectionMinusDeck = new ArrayList<>();
            for (Zombie zombieIterator : Account.getPlayingAccount().getZombiesCollection()) {
                if (zombieExistsInDeck(zombieIterator.getName())) {
                    continue;
                }
                collectionMinusDeck.add(zombieIterator);
            }
            View.printNumberedZombieArrayList(collectionMinusDeck);
        }
    }

    public static void goToPlayByPlayType(int playTypeIndex) {
        switch (playTypeIndex) {
            case 1:
            case 2:
                Day.dayAndWaterMenu();
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            default:
                //
                break;
        }

    }

//    public static void setDefaultPlantsDeck() {
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Peashooter")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Snow Pea")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Explode-o-nut")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Scaredy-shroom")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Cherry Bomb")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Kernel-pult")));
//        Account.getPlayingAccount().plantsDeck.add((Plant.getPlant("Sunflower")));
//    }
//
//    public static void setDefaultZombiesDeck() {
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Zombie"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Football Zombie"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Screen Door Zombie"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Zomboni"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Balloon Zombie"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Bungee Zombie"));
//        Account.getPlayingAccount().zombiesDeck.add(Zombie.getZombie("Conehead Zombie"));
//    }

    public static void setDefaultPlantsCollection() {
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Peashooter")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Snow Pea")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Explode-o-nut")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Scaredy-shroom")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Cherry Bomb")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Kernel-pult")));
        Account.getPlayingAccount().getPlantsCollection().add((Plant.getPlant("Sunflower")));
    }

    public static void setDefaultZombiesCollection() {
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zombie"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Football Zombie"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Screen Door Zombie"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Zomboni"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Balloon Zombie"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Bungee Zombie"));
        Account.getPlayingAccount().getZombiesCollection().add(Zombie.getZombie("Conehead Zombie"));
    }

}

