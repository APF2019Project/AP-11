import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static Scanner scanner = new Scanner(System.in);

    public static void shop() {
        String command;
        boolean exitShop = false;
        while (!exitShop) {
            System.out.println("--- Shop ---\nEnter command:");
            command = scanner.nextLine();
            if (command.matches("[b,B]uy (.+)")) {
                command = command.replaceFirst("[b,B]uy", "");
                command = command.trim();
                buy(Account.getPlayingAccount(), command);
                continue;
            }
            switch (command) {
                case "Exit":
                case "exit":
                    exitShop = true;
                    break;
                case "Show shop":
                case "show shop":
                    showShop(Account.getPlayingAccount());
                    break;
                case "Show collection":
                case "show collection":
                    showCollection(Account.getPlayingAccount());
                    break;
                case "Money":
                case "money":
                    showMoney(Account.getPlayingAccount());
                case "Help":
                case "help":
                    System.out.println("show shop, show collection, money, buy [card name], help");
                default:
                    invalidCommand(0);
            }
        }
    }

    private static void invalidCommand(int index) {
        if (index == 0)
            System.out.println("invalid command in Login Menu\nTry again:");
        else if (index == 1) ;
    }

    public static void showShop(Account account) {
        Plant[] plantsCollection = (Plant[]) Plant.getPlants().toArray();
        Zombie[] zombiesCollection = (Zombie[]) Zombie.getZombies().toArray();
        int numOfPlants = plantsCollection.length;
        int numOfZombies = zombiesCollection.length;
        for (int i = 0; i < numOfPlants; i++)
            for (Plant plant : account.getPlantsCollection())
                if (plantsCollection[i] == plant) {
                    plantsCollection[i] = null;
                    break;
                }
        for (int i = 0; i < numOfZombies; i++)
            for (Zombie zombie : account.getZombiesCollection())
                if (zombiesCollection[i] == zombie) {
                    zombiesCollection[i] = null;
                    break;
                }

        int i = 0;
        int j = 0;

        while (i < numOfPlants || j < numOfZombies) {
            if (i < numOfPlants) {
                System.out.print(plantsCollection[i].getName());
            } else
                System.out.print(" \t ");
            if (j < numOfZombies)
                System.out.println(zombiesCollection[i].getName());
            else
                System.out.println();
            i++;
            j++;
        }
    }

    public static void buy(Account account, String cardName) {
        for (Plant plant : Plant.getPlants())
            if (cardName.equals(plant.getName()))
                if (account.getMoney() >= plant.getPrice()) {
                    account.setMoney(account.getMoney() - plant.getPrice());
                    account.getPlantsCollection().add(plant);
                    return;
                }
        for (Zombie zombie : account.getZombiesCollection())
            if (cardName.equals(zombie.getName()))
                if (account.getMoney() >= zombie.getPrice()) {
                    account.setMoney(account.getMoney() - zombie.getPrice());
                    account.getZombiesCollection().add(zombie);
                    return;
                }
    }

    public static void showMoney(Account account) {
        System.out.println("Your money: " + account.getMoney());
    }

    public static void showCollection(Account account) {
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
