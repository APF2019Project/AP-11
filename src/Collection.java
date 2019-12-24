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
                    // Shop.showCollection(Account.getPlayingAccount());
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
        command = command.replaceFirst("[s,S]elect", "");
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
        } else {
            //
        }
    }

    private static void putZombieInDeck(String cardName) {
        //
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
            View.cardRemovedFromDeck(cardName);
        }
    }

        private static void showHand ( int playTypeIndex){
            //
        }

        public static void goToPlayByPlayType ( int playTypeIndex){
            switch (playTypeIndex) {
                case 1:
                    //
                    break;
                case 2:
                    //
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

        public static void setDefaultPlantsDeck () {
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Peashooter")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Snow pea")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Explode-o-nut")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Scaredy-shroom")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Cherry Bomb")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Kernel-pult")));
            Account.getPlayingAccount().plantsDeck.add(new Plant(Plant.getPlant("Sunflower")));
        }


    }

