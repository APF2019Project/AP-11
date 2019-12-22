import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static Scanner scanner = new Scanner(System.in);

    public static void shop() {
        System.out.println("--- Shop ---\nEnter command:");
        String command;
        while (true) {
         command = scanner.nextLine();
            switch (command) {
                case "Exit":
                case "exit":
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
                default:
                    if (command.matches("[b,B]uy (.+)")) {
                        command = command.replaceFirst("[b,B]uy", "");
                        command = command.trim();
                        buy(Account.getPlayingAccount(), command);
                    } else {
                        System.out.println("invalid command in Login Menu\nTry again:");
                    }
            }
        }
    }

    public static void showShop(Account account) {
        ArrayList<Plant> collection = account.getPlantsCollection();
        for (Plant plant : Plant.getPlants()) {
            boolean cardIsSold = false;
            for (int i =0; i < collection.size(); i++)
                if (plant == collection.get(i)) {
                    cardIsSold = true;
                    break;
                }
            if (cardIsSold)
                continue;
            System.out.println(plant.getName() +": "+plant.getSunCost());
        }
    }

    public static void buy(Account account, String cardName) {
        for (Plant plant: Plant.getPlants())
            if (cardName.equals(plant.getName())){
                account.getPlantsCollection().add(plant);
                return;
            }
    }

    public static void showMoney(Account account){
        System.out.println("Your money: "+ account.getMoney());
    }

    public static void showCollection(Account account) {
        Plant[] collection = (Plant[]) account.getPlantsCollection().toArray();
        for (int i = 0; i<collection.length; i++){
            if (i +1 == collection.length){
                System.out.println(collection[i].getName());
                break;
            }
            System.out.print(collection[i].getName()+" ,\t");
        }
    }
}
