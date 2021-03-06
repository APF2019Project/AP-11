import com.gilecode.yagson.YaGson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    public static Scanner scanner = new Scanner(System.in);


    public static String showShop(Account account) {
        ArrayList<Plant> plantsShop = (ArrayList<Plant>) Plant.getPlants().clone();
        ArrayList<Zombie> zombiesShop = (ArrayList<Zombie>) Zombie.getZombies().clone();

        for (Plant plant : Plant.getPlants()) {
            for (Plant myPlant : account.getPlantsCollection())
                if (myPlant.equals(plant)) {
                    plantsShop.remove(plant);
                    break;
                }
        }
        for (Zombie zombie : Zombie.getZombies()) {
            for (Zombie myZombie : account.getZombiesCollection())
                if (myZombie == zombie) {
                    zombiesShop.remove(zombie);
                    break;
                }
        }
        // before network part:
        // View.printShopWithPrices(plantsShop, zombiesShop);
        YaGson yaGson = new YaGson();
        TwoArrayLists twoArrayLists = new TwoArrayLists(plantsShop, zombiesShop);
        String twoArrayListsJson = yaGson.toJson(twoArrayLists);
        return twoArrayListsJson;
    }


    public static void readyToBuy(String command) throws IOException {
        command = command.replaceFirst("[b,B]uy", "");
        command = command.trim();
        clientShopFunctions.buy(command, clientTestAccount.clientUsername);
       //buy(Account.getMainPlayingAccount(), command);
    }


    public static void buy(Account account, String cardName) {
        boolean cardIsPlant = Plant.plantExist(cardName);
        boolean cardIsZombie = Zombie.zombieExists(cardName);
        if (!(cardIsPlant || cardIsZombie)) {
            View.invalidCardName();
            return;
        }
        if (cardIsPlant) {
            buyPlant(cardName, account);
        } else if (cardIsZombie) {
            buyZombie(cardName, account);
        }
    }

    public static void buyPlant(String cardName, Account account) {
        if (Collection.plantExistsInCollection(cardName)) {
            View.cardAlreadyExistsInCollection("plants", cardName);
        } else {
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
        }
    }

    public static void buyZombie(String cardName, Account account) {
        if (Collection.zombieExistsInCollection(cardName)) {
            View.cardAlreadyExistsInCollection("zombies", cardName);
        } else {
            for (Zombie zombie : Zombie.getZombies()) {
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


    public static void showMoney(Account account) {
        System.out.println("Your money: " + account.getMoney());
    }

    public static void showCollection(Account account) {
        ArrayList<Plant> plants = (ArrayList<Plant>) account.getPlantsCollection().clone();
        ArrayList<Zombie> zombies = (ArrayList<Zombie>) account.getZombiesCollection().clone();
        View.printCollections(plants, zombies);
    }

}

class TwoArrayLists {
    public ArrayList<Plant> plants;
    public ArrayList<Zombie> zombies;

    public TwoArrayLists(ArrayList<Plant> plants, ArrayList<Zombie> zombies) {
        this.plants = plants;
        this.zombies = zombies;
    }
}