import Requests.ChatRequest;
import Requests.ShopRequest;
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

                    } else if (mode.equals("chat")) {
                        ChatServerSide.handleRequest(printer, reader);

                    } else if (mode.equals("check messages")) {

                        String username = reader.nextLine();


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

}

class ChatServerSide {

    public static void handleRequest(PrintStream printer, Scanner reader) {
        String requestJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        ChatRequest request = yaGson.fromJson(requestJson, ChatRequest.class);
        ChatRequest.RequestType requestType = request.getRequestType();

        if (requestType.equals(ChatRequest.RequestType.showOnlineUsers)) {
            showOnlineUsers(printer, reader);

        } else if (requestType.equals(ChatRequest.RequestType.sendMessage)) {
            sendMessage(printer, reader);

        }


    }


    public static void sendMessage(PrintStream printer, Scanner reader) {
        String messageJson = reader.nextLine();
        YaGson yaGson = new YaGson();
        Message2 message = yaGson.fromJson(messageJson, Message2.class);
        OnlineAccount receiver = OnlineAccount.getOnlineAccount(message.receiver);
        receiver.message2s.add(message);
    }

    public static void showOnlineUsers(PrintStream printer, Scanner reader) {
        ArrayList<String> onlineUsernames = OnlineAccount.getOnlineUsernames();
        YaGson yaGson = new YaGson();
        String onlineUsernamesJson = yaGson.toJson(onlineUsernames);
        printer.println(onlineUsernamesJson);
    }
}

