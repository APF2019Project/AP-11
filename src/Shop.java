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
        Plant[] plantsCollection = (Plant[]) Plant.getPlants().toArray();
        Zombie[] zombiesCollection = (Zombie[]) Zombie.getZombies().toArray();
        for (int i = 0; i < plantsCollection.length; i++)
            for (Plant plant : account.getPlantsCollection())
                if (plantsCollection[i] == plant) {
                    plantsCollection[i] = null;
                    break;
                }
        for (int i = 0; i < zombiesCollection.length; i++)
            for (Zombie zombie : account.getZombiesCollection())
                if (zombiesCollection[i] == zombie) {
                    zombiesCollection[i] = null;
                    break;
                }
        ArrayList<Plant> plantArrayList = new ArrayList<>();
        ArrayList<Zombie> zombieArrayList = new ArrayList<>();
        for (Plant plant : plantsCollection)
            if (plant != null)
                plantArrayList.add(plant);
        for (Zombie zombie : zombiesCollection)
            if (zombie != null)
                zombieArrayList.add(zombie);

        int i = 0;
        int j = 0;
        while (i < plantArrayList.size() || j < zombieArrayList.size()) {
            if (i < plantArrayList.size())
                System.out.print(plantsCollection[i].getName());
            else
                System.out.print(" \t ");
            if (j < zombieArrayList.size())
                System.out.println(zombiesCollection[i].getName());
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
        for (Plant plant : Plant.getPlants()) {
            if (cardName.equals(plant.getName())) {
                if (account.getMoney() >= plant.getPrice()) {
                    account.setMoney(account.getMoney() - plant.getPrice());
                    account.getPlantsCollection().add(plant);
                    return;
                }
            }
        }
        for (Zombie zombie : account.getZombiesCollection()) {
            if (cardName.equals(zombie.getName())) {
                if (account.getMoney() >= zombie.getPrice()) {
                    account.setMoney(account.getMoney() - zombie.getPrice());
                    account.getZombiesCollection().add(zombie);
                    return;
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
