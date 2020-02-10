
import com.gilecode.yagson.YaGson;
import com.sun.tools.classfile.ConstantPool;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6000);

        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();

            new Thread(() -> {
                try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
                     Scanner reader = new Scanner(clientSocket.getInputStream())) {

                    String mode = reader.nextLine();

                    if (mode.equals("create account")) {
                        clientCreateAccount(printer, reader);

                    } else if (mode.equals("login")) {
                        clientLogin(printer, reader, clientSocket);

                    } else if (mode.equals("leaderboard")) {
                        clientLeaderboard(printer, reader);

                    } else if (mode.equals("change password")) {
                        clientChangePassword(printer, reader);

                    } else if (mode.equals("rename account")) {
                        clientRenameAccount(printer, reader);

                    } else if (mode.equals("delete account")) {
                        clientDeleteAccount(printer, reader);

                    } else if (mode.equals("shop")) {
                        ShopServerSide.handleRequest(reader, printer);

                    } else if (mode.equals("shop2")) {
                        ShopServerSide.handleRequest_2(reader, printer);

                    } else if (mode.equals("logout")) {
                        clientLogout(printer, reader);

                    } else if (mode.equals("chat")) {
                        ChatServerSide.handleRequest(printer, reader);

                    } else if (mode.equals("check messages")) {
                        String username = reader.nextLine();
                        ChatServerSide.checkMessages(printer, reader, username);

                    } else if (mode.equals("collection")) {
                        CollectionServerSide.handleRequest(printer, reader);

                    } else if (mode.equals("play")) {
                        PlayServerSide.handleRequest(printer, reader);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


        }
    }


    public static void clientCreateAccount(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        boolean accountExists = Account.accountExists(username);
        if (accountExists) {
            printer.println("true");
        } else {
            printer.println("false");
            String password = reader.nextLine();
            System.out.println(username);
            System.out.println(password);
            Account.accounts.add(new Account(username.trim(), password.trim()));
            System.out.println(Account.accounts.size());
        }
    }

    public static void clientLogin(PrintStream printer, Scanner reader, Socket clientSocket) {
        String username = reader.nextLine();
        String password = reader.nextLine();
        boolean canLogin = Account.userAndPassMatch(username, password);
        if (canLogin) {
            Account thisAccount = Account.getAccountByUsername(username);
            Collection.setDefaultPlantsCollection(thisAccount);
            Collection.setDefaultZombiesCollection(thisAccount);
            OnlineAccount newOnlineAccount = new OnlineAccount(clientSocket, thisAccount);
            printer.println("matches");
        } else {
            printer.println("not match");
            return;
        }
    }

    public static void clientLeaderboard(PrintStream printer, Scanner reader) {
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<Integer> killedZombies = new ArrayList<>();
        for (Account accountIterator : Account.accounts) {
            usernames.add(accountIterator.getUsername());
            killedZombies.add(accountIterator.getKilledZombies());
        }
        YaGson yaGson = new YaGson();
        String usernamesYaGson = yaGson.toJson(usernames);
        String killedZombiesYaGson = yaGson.toJson(killedZombies);
        printer.println(usernamesYaGson);
        printer.println(killedZombiesYaGson);

    }

    public static void clientChangePassword(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        String password = reader.nextLine();
        boolean matches = Account.userAndPassMatch(username, password);
        if (matches) {
            printer.println("matches");
            String newPassword = reader.nextLine();
            Account.getAccountByUsername(username).setPassword(newPassword);
        } else {
            printer.println("not match");
        }
    }

    public static void clientRenameAccount(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        String password = reader.nextLine();
        boolean matches = Account.userAndPassMatch(username, password);
        if (matches) {
            printer.println("matches");
            String newUsername = reader.nextLine();
            boolean alreadyTakenUser = Account.accountExists(newUsername);
            if (!alreadyTakenUser) {
                printer.println("can change");
                Account.getAccountByUsername(username).setUsername(newUsername);
            } else {
                printer.println("duplicate username");
            }
        } else {
            printer.println("not match");
        }
    }

    public static void clientDeleteAccount(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        String password = reader.nextLine();
        boolean matches = Account.userAndPassMatch(username, password);
        if (matches) {
            printer.println("matches");
            String isUserSure = reader.nextLine();
            if (isUserSure.equals("delete this account")) {
                Account tempAccount = Account.getAccountByUsername(username);
                Account.accounts.remove(tempAccount);
            }
        } else {
            printer.println("not match");
        }
    }

    public static void clientLogout(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        OnlineAccount.logout(username);
    }

}

class PlayServerSide {


    public static void handleRequest(PrintStream printer, Scanner reader) {
        String request = reader.nextLine();
        if (request.equals("save after game")) {
            saveAfterGame(printer, reader);

        } else if (request.equals("play request")) {
            setPlayRequest(printer, reader);
        }
    }

    private static void setPlayRequest(PrintStream printer, Scanner reader) {
        String messageJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        Message2 message = yaGson.fromJson(messageJson, Message2.class);
        String receiver = message.receiver;
        OnlineAccount.getOnlineAccount(receiver).message2s.add(message);
    }

    private static void saveAfterGame(PrintStream printer, Scanner reader) {
        String accountJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        Account account = yaGson.fromJson(accountJson, Account.class);
        String username = account.getUsername();
        Account oldAccount = Account.getAccountByUsername(username);
        oldAccount = account;
    }


}

class CollectionServerSide {

    public static void handleRequest(PrintStream printer, Scanner reader) {
        String request = reader.nextLine();
        if (request.equals("set collections")) {
            setCollections(printer, reader);
        }

    }

    private static void setCollections(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        Account account = Account.getAccountByUsername(username);
        YaGson yaGson = new YaGson();
        String accountJson = yaGson.toJson(account);
        printer.println(accountJson);


    }

}
class ShopServerSide {

    public static void handleRequest(Scanner reader, PrintStream printer) {
        String requestJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        ShopRequest request = yaGson.fromJson(requestJson, ShopRequest.class);
        ShopRequest.RequestType requestType = request.getRequestType();
        String username = request.getUsername();

        if (requestType.equals(ShopRequest.RequestType.showShop)) {
            // Show Shop
            Account account = Account.getAccountByUsername(username);
            String twoArrayListsJson = Shop.showShop(account);
            printer.println(twoArrayListsJson);

        } else if (requestType.equals(ShopRequest.RequestType.showCollection)) {
            // Show Collection

        } else if (requestType.equals(ShopRequest.RequestType.showMoney)) {
            // Show Money
            printer.println(Integer.toString(Account.getAccountByUsername(username).getMoney()));

        } else if (requestType.equals(ShopRequest.RequestType.moneyCheat)) {
            // Money Cheat code
            Account.getAccountByUsername(username).setMoney(Account.getAccountByUsername(username).getMoney() + 1000);
        }

    }

    public static void handleRequest_2(Scanner reader, PrintStream printer) {
        String request = reader.nextLine();
        if (request.equals("buy")) {
            buy(reader, printer);

        } else if (request.equals("show collection")) {
            showCollection(printer, reader);

        } else if (request.equals("new plant")) {
            newPlant(printer, reader);

        } else if (request.equals("new zombie")) {
            newZombie(printer, reader);

        } else if (request.equals("buy from client")) {
            buyFromClient(printer, reader);

        } else if (request.equals("exchange card")) {
            exchangeCard(printer, reader);

        } else if (request.equals("sellReq accepted")) {
            notifyBuyer(printer, reader);

        }
    }

    private static void notifyBuyer(PrintStream printer, Scanner reader) {
        String buyerUsername = reader.nextLine();
        String sellerUsername = reader.nextLine();
        String content = "Your sell request to " + sellerUsername + " has been accepted and card is yours";
        Message2 message = new Message2(buyerUsername, sellerUsername, content, true);
        OnlineAccount.getOnlineAccount(buyerUsername).message2s.add(message);
    }

    private static void exchangeCard(PrintStream printer, Scanner reader) {
        String sellerUsername = reader.nextLine();
        String buyerUsername = reader.nextLine();
        String cardName = reader.nextLine();

        Account seller = Account.getAccountByUsername(sellerUsername);
        Account buyer = Account.getAccountByUsername(buyerUsername);
        boolean isPlant = Plant.plantExist(cardName);
        boolean isZombie = Zombie.zombieExists(cardName);

        if (isPlant) {
            Plant plant = Collection.getPlantInCollection(seller, cardName);
            if (buyer.getMoney() < plant.getPrice()) {
                printer.println("not enough money");
                return;
            }
            buyer.plantsCollection.add(plant);
            buyer.setMoney(seller.getMoney() - plant.getPrice());
            seller.plantsCollection.remove(plant);
            seller.setMoney(seller.getMoney() + plant.getPrice());
            printer.println("done");
        }

    }

    private static void buyFromClient(PrintStream printer, Scanner reader) {
        String sellerUsername = reader.nextLine();
        String buyerUsername = reader.nextLine();
        String cardName = reader.nextLine();
        if (!OnlineAccount.isOnline(sellerUsername)) {
            printer.println("wrong seller");
            return;
        }
        if (!Plant.plantExist(cardName) && !Zombie.zombieExists(cardName)) {
            printer.println("wrong card name");
            return;
        }
        OnlineAccount seller = OnlineAccount.getOnlineAccount(sellerUsername);
        if (!Collection.isInCollections(cardName, Account.getAccountByUsername(sellerUsername))) {
            printer.println("not in collection");
            return;
        }
        String sellReq = "SellReq!\nDo you sell your " + cardName + "to " + buyerUsername + "?";
        Message2 buyRequestMessage = new Message2(sellerUsername, buyerUsername, sellReq, true);
        buyRequestMessage.setCardName(cardName);
        seller.message2s.add(buyRequestMessage);
        printer.println("request sent");
    }

    private static void newZombie(PrintStream printer, Scanner reader) {
        String zombieJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        Zombie zombie = yaGson.fromJson(zombieJson, Zombie.class);
        Zombie.zombies.add(zombie);
                                 View.printNumberedZombieArrayList(Zombie.zombies);
    }

    public  static void newPlant(PrintStream printer, Scanner reader) {
        String plantJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        Plant plant = yaGson.fromJson(plantJson, Plant.class);
        Plant.plants.add(plant);
    }

    public static void showCollection(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        Account account = Account.getAccountByUsername(username);
        ArrayList<Plant> plants = (ArrayList<Plant>) account.getPlantsCollection().clone();
        ArrayList<Zombie> zombies = (ArrayList<Zombie>) account.getZombiesCollection().clone();
        YaGson yaGson = new YaGson();
        String plantsJson = yaGson.toJson(plants);
        String zombiesJson = yaGson.toJson(zombies);
        printer.println(plantsJson);
        printer.println(zombiesJson);
    }

    public static void buy(Scanner reader, PrintStream printer) {
        String username = reader.nextLine();
        String cardName = reader.nextLine();

        Account account = Account.getAccountByUsername(username);

        boolean cardIsPlant = Plant.plantExist(cardName);
        boolean cardIsZombie = Zombie.zombieExists(cardName);
        if (!(cardIsPlant || cardIsZombie)) {
            printer.println("-1"); // invalid card name
            return;
        }
        if (cardIsPlant) {
            buyPlant(cardName, account, printer);
        } else if (cardIsZombie) {
            buyZombie(cardName, account, printer);
        }
    }

    public static void buyZombie(String cardName, Account account, PrintStream printer) {
        if (Collection.zombieExistsInCollection_2(cardName, account)) {
            //  View.cardAlreadyExistsInCollection("zombies", cardName);
            printer.println("-2"); // card already exists in collection
        } else {
            for (Zombie zombie : Zombie.getZombies()) {
                if (cardName.equals(zombie.getName())) {
                    if (account.getMoney() >= zombie.getPrice()) {
                        if (zombie.getQuantityInShop() > 0) {
                            account.setMoney(account.getMoney() - zombie.getPrice());
                            account.getZombiesCollection().add(zombie);
                            zombie.setQuantityInShop(zombie.getQuantityInShop() - 1);
                            //    View.zombiePurchased(cardName);
                            printer.println("done");
                            return;
                        } else {
                            printer.println("-4"); // sold out.
                        }
                    } else {
                        //  View.notEnoughMoney();
                        printer.println("-3");
                    }
                }
            }
        }
    }

    public static void buyPlant(String cardName, Account account, PrintStream printer) {
        if (Collection.plantExistsInCollection_2(cardName, account)) {
            printer.println("-2"); // card already exists in collection
        } else {
            for (Plant plant : Plant.getPlants()) {
                if (cardName.equals(plant.getName())) {
                    if (account.getMoney() >= plant.getPrice()) {
                        if (plant.getQuantityInShop() > 0) {

                            account.setMoney(account.getMoney() - plant.getPrice());
                            account.getPlantsCollection().add(plant);
                            plant.setQuantityInShop(plant.getQuantityInShop() - 1);
                            //View.plantPurchased(cardName);
                            printer.println("done");
                            return;

                        } else {
                            printer.println("-4"); // sold out.
                        }
                    } else {
                        //View.notEnoughMoney();
                        printer.println("-3");
                    }
                }
            }
        }
    }


}

class ChatServerSide {

    public static void handleRequest(PrintStream printer, Scanner reader) {
        String requestJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        ChatRequest request = yaGson.fromJson(requestJson, ChatRequest.class);
        ChatRequest.RequestType requestType = request.getRequestType();
        String username = request.sender;

        if (requestType.equals(ChatRequest.RequestType.showOnlineUsers)) {
            showOnlineUsers(printer, reader, username);

        } else if (requestType.equals(ChatRequest.RequestType.sendMessage)) {
            sendMessage(printer, reader, request);

        }


    }


    public static void sendMessage(PrintStream printer, Scanner reader, ChatRequest request) {
        String receiverUsername = request.message.receiver;
        Message2 message = request.message;
        OnlineAccount.getOnlineAccount(receiverUsername).message2s.add(message);
    }

    public static void showOnlineUsers(PrintStream printer, Scanner reader, String username) {
        ArrayList<String> onlineUsernames = OnlineAccount.getOnlineUsernames(username);
        ArrayList<String> offlineUsernames = Account.getOfflineUsernames();
        YaGson yaGson = new YaGson();
        String onlineUsernamesJson = yaGson.toJson(onlineUsernames);
        String offlineUsernamesJson = yaGson.toJson(offlineUsernames);
        printer.println(onlineUsernamesJson);
        printer.println(offlineUsernamesJson);
    }


    // not user reachable function:
    public static void checkMessages(PrintStream printer, Scanner reader, String username) {
        if (OnlineAccount.getOnlineAccount(username).message2s.size() > 0) {
            printer.println("new message");
            YaGson yaGson = new YaGson();
            String messageJson = yaGson.toJson(OnlineAccount.getOnlineAccount(username).message2s.get(0));
            printer.println(messageJson);
            OnlineAccount.getOnlineAccount(username).message2s.clear();
        } else {
            printer.println("no message");
        }
    }


}

