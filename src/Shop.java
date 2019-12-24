import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static Scanner scanner = new Scanner(System.in);

    public static void shop() { // Show Shop index: -10
        String command;
        boolean exitShop = false;
        while (!exitShop) {
            System.out.println("--- Shop ---\nEnter command:");
            command = scanner.nextLine();

            if (command.matches("[b,B]uy (.+)")) {
                readyToBuy(command);
                continue;
            }
            switch (command.toLowerCase()) {
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    exitShop = true;
                    break;
                case "show shop":
                    showShop(Account.getPlayingAccount());
                    break;
                case "show collection":
                    showCollection(Account.getPlayingAccount());
                    break;
                case "money":
                    showMoney(Account.getPlayingAccount());
                    break;
                case "help":
                    View.showHelp(-10);
                    break;
                default:
                    View.invalidCommand(-10);
                    break;
            }
        }
    }


    private static void showShop(Account account) {
        ArrayList<Plant> plantsShop =(ArrayList<Plant>) Plant.getPlants().clone();
        ArrayList<Zombie> zombiesShop =(ArrayList<Zombie>) Zombie.getZombies().clone();


        for (Plant plant: Plant.getPlants())
            for (Plant myPlant : account.getPlantsCollection())
                if (myPlant == plant) {
                    plantsShop.remove(plant);
                    break;
                }
        for (Zombie zombie : Zombie.getZombies())
            for (Zombie myZombie : account.getZombiesCollection())
                if (myZombie == zombie) {
                    zombiesShop.remove(zombie);
                    break;
                }
        System.out.println("Plants:  \t\t  Zombies:");
        int i = 0;
        int j = 0;
        while (i < plantsShop.size() || j < zombiesShop.size()) {
            if (i < plantsShop.size())
                System.out.print(plantsShop.get(i).getName()+"\t\t");
            else
                System.out.print(" \t \t \t \t");
            if (j < zombiesShop.size())
                System.out.println(zombiesShop.get(i).getName());
            else
                System.out.println();
            i++;
            j++;
        }
    }


    private static void readyToBuy(String command) {
        command = command.replaceFirst("[b,B]uy", "");
        command = command.trim();
        buy(Account.getPlayingAccount(), command);
    }

    private static void buy(Account account, String cardName) {
        if (!(Plant.plantExist(cardName) || Zombie.zombieExists(cardName))) {
            View.invalidCardName();
            return;
        }
        if (Plant.plantExist(cardName)) {
            for (Plant plant : Plant.getPlants()) {
                if (cardName.equals(plant.getName())) {
                    if (account.getMoney() >= plant.getPrice()) {
                        account.setMoney(account.getMoney() - plant.getPrice());
                        account.getPlantsCollection().add(plant);
                        View.plantPurchased(cardName);
                        return;
                    } else {
                        View.notEnoughMoney();
                        View.goingBackTo(-10);
                    }
                }
            }
        } else if (Zombie.zombieExists(cardName)) {
            for (Zombie zombie : account.getZombiesCollection()) {
                if (cardName.equals(zombie.getName())) {
                    if (account.getMoney() >= zombie.getPrice()) {
                        account.setMoney(account.getMoney() - zombie.getPrice());
                        account.getZombiesCollection().add(zombie);
                        View.zombiePurchased(cardName);
                        return;
                    } else {
                        View.notEnoughMoney();
                        View.goingBackTo(-10);
                    }
                }
            }
        }
    }

    private static void showMoney(Account account) {
        System.out.println("Your money: " + account.getMoney());
    }

    private static void showCollection(Account account) {
        System.out.println(" Plants\tZombies ");
        Plant[] plants = (Plant[]) account.getPlantsCollection().toArray();
        Zombie[] zombies = (Zombie[]) account.getZombiesCollection().toArray();
        int i = 0;
        while (i < plants.length || i < zombies.length) {
            if (i < plants.length && i < zombies.length)
                System.out.println(plants[i].getName() + "\t" + zombies[i].getName());
            else if (i < plants.length)
                System.out.println(plants[i].getName());
            else
                System.out.println(" \t\t " + zombies[i].getName());
            i++;
        }
    }
}
