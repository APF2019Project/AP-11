import Requests.AccountRequests;
import Requests.ChatRequest;
import Requests.ShopRequest;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        View.startHeader_2();
        ClientMenus.loginMenu();
    }
}


class clientTestAccount { // Login menu, Main menu, and Profile menu parts

    public static String clientUsername;

    // Login Menu functions:
    public static void createAccount(boolean calledFromLoginMenu) throws IOException {

        System.out.println("--- Create account ---");
        String username;
        String password;
        System.out.println("Enter username");
        username = View.input();
        if (username.length() < 3) {
            View.usernameLengthError();
            return;
        }
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("create account");
            printer.println(username.trim());
            String exists = socketScanner.nextLine();
            if (exists.equals("true")) {
                View.usernameIsAlreadyTaken();
                return;
            }
            System.out.println("Enter password:");
            password = View.input();
            if (password.length() < 4) {
                View.passwordLengthError();
                return;
            }
            printer.println(password.trim());
            View.accountCreated();
        }
    }

    public static void login(boolean calledFromLoginMenu) throws IOException {

        Thread checkMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Socket clientSocket_2 = null;
                    try {
                        clientSocket_2 = new Socket("localhost", 6000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try (PrintStream printer = new PrintStream(clientSocket_2.getOutputStream());
                         Scanner socketScanner = new Scanner(clientSocket_2.getInputStream())) {
                        Thread.sleep(500);
                        printer.println("check messages");
                        printer.println(clientTestAccount.clientUsername);
                        String serverAns = socketScanner.nextLine();
                        if (serverAns.equals("new message")) {
                            String messageJson = socketScanner.nextLine();
                            YaGson yaGson = new YaGson();
                            Message2 message = yaGson.fromJson(messageJson, Message2.class);
                            System.out.println("NEW Message from: " + message.getSender());
                            System.out.println(message.getContent());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                 }
            }
        });

        System.out.println("--- LOGIN ---");
        String username;
        String password;
        System.out.println("Enter username:");
        username = View.input();
        System.out.println("Enter password:");
        password = View.input();
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("login");
            printer.println(username);
            printer.println(password);
            String canLogin = socketScanner.nextLine();
            if (canLogin.equals("matches")) {
                View.youAreLoggedIn();
                clientUsername = username;
                if (calledFromLoginMenu) {
                    System.out.println("Logged in, going to --> Main Menu:");
                    checkMessageThread.start();
                    ClientMenus.mainMenu();
                } else {
                    System.out.println("Logged in, going back to --> Profile Menu:");
                }

            } else {
                View.loginFailed();
            }

        } // try ends

    }

    public static void leaderboard() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("leaderboard");
            String usernamesJson = socketScanner.nextLine();
            String killedZombiesJson = socketScanner.nextLine();
            YaGson yaGson = new YaGson();
            ArrayList<String> usernames = yaGson.fromJson(usernamesJson, ArrayList.class);
            ArrayList<Integer> killedZombies = yaGson.fromJson(killedZombiesJson, ArrayList.class);
            for (int i = 0; i < usernames.size(); i++) {
                System.out.print(usernames.get(i) + " killed zombies: " + killedZombies.get(i));
                System.out.println();
            }
            System.out.println();
        }
    }

    // Profile Menu functions:
    public static void renameAccount() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("rename account");
            System.out.println("Enter your username");
            String username = View.input();
            System.out.println("Enter your password:");
            String password = View.input();
            printer.println(username);
            printer.println(password);
            String matches = socketScanner.nextLine();
            if (matches.equals("matches")) {
                System.out.println("Enter your new username:");
                String newUsername = View.input();
                printer.println(newUsername);
                String canChange = socketScanner.nextLine();
                if (canChange.equals("can change")) {
                    System.out.println("Your username has been changed.");
                    clientUsername = newUsername;
                    System.out.println();
                } else {
                    View.usernameIsAlreadyTaken();
                }
            } else {
                View.loginFailed();
            }
        }
    }

    public static void changePassword() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("change password");
            System.out.println("Enter username:");
            String username = View.input();
            printer.println(username);
            System.out.println("Enter your old password:");
            String password = View.input();
            printer.println(password);
            String matches = socketScanner.nextLine();
            if (matches.equals("matches")) {
                System.out.println("Enter your new password:");
                String newPassword = View.input();
                printer.println(newPassword);
                System.out.println("Your password has been changed.");
            } else {
                System.out.println("Username and password doesn't match.\nTry again.");
            }
        }
    }

    public static void showPlayingUsername() {
        System.out.println("You are logged in as: " + clientUsername);
    }

    public static void deleteAccount() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
                printer.println("delete account");
            System.out.println("Enter your username");
            String username = View.input();
            System.out.println("Enter your password:");
            String password = View.input();
                printer.println(username);
                printer.println(password);
                String matches = socketScanner.nextLine();
                System.out.println(matches);
            if (matches.equals("matches")) {
                System.out.println("Are you sure you want to delete your account?");
                String answer = View.input();
                if (answer.toLowerCase().equals("no")) {
                    System.out.println("Your account is safe.");
                    printer.println("don't delete");

                } else if (answer.toLowerCase().equals("yes")) {
                    printer.println("delete this account");
                    clientUsername = null;
                    System.out.println("Your account has been deleted. Going to login Menu:");
                    System.out.println();
                    ClientMenus.loginMenu();
                }
            } else {
                View.loginFailed();
            }
        }
    }
}

class clientShopFunctions {

    public static void showShop() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            ShopRequest request = new ShopRequest(clientTestAccount.clientUsername, ShopRequest.RequestType.showShop);
            printer.println("shop");
            YaGson yaGson = new YaGson();
            String requestJson = yaGson.toJson(request);
            printer.println(requestJson);
            String twoArrayListsJson = socketScanner.nextLine();
            TwoArrayLists twoArrayLists = yaGson.fromJson(twoArrayListsJson, TwoArrayLists.class);
            View.printShopWithPrices(twoArrayLists.plants, twoArrayLists.zombies);
        }
    }

    public static void showCollection() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop");
            //
            //
            //
            //
            //
            //
            //
        }
    }

    public static void buy(String cardName, String username) throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop");

        }
    }

    public static void showMoney() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop");
            ShopRequest request = new ShopRequest(clientTestAccount.clientUsername, ShopRequest.RequestType.showMoney);
            YaGson yaGson = new YaGson();
            String requestJson = yaGson.toJson(request);
            printer.println(requestJson);
            String money = socketScanner.nextLine();
            System.out.println("Your money is: " + money);
        }
    }

    public static void moneyCheatCode() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop");
            ShopRequest request = new ShopRequest(clientTestAccount.clientUsername, ShopRequest.RequestType.moneyCheat);
            YaGson yaGson = new YaGson();
            String requestJson = yaGson.toJson(request);
            printer.println(requestJson);
        }
    }



}

class ClientChat {

    public static void sendMessage() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            System.out.println("___Select an online username:");
            String receiver = View.input();
            System.out.println("___Enter content in one line:");
            String content = View.input();
            System.out.println("___Message sent.");

            printer.println("chat");

            Message2 message = new Message2(receiver, clientTestAccount.clientUsername, content);
            YaGson yaGson = new YaGson();
            String messageJson = yaGson.toJson(message);
            printer.println(messageJson);

        }
    }

    public static void showOnlineUsers() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("chat");
            ChatRequest request = new ChatRequest(clientTestAccount.clientUsername, null, null,
                    ChatRequest.RequestType.showOnlineUsers);
            YaGson yaGson = new YaGson();
            String requestJson = yaGson.toJson(request);
            printer.println(requestJson);
            String onlineUsersJson = socketScanner.nextLine();
            ArrayList<String> onlineUsers = yaGson.fromJson(onlineUsersJson, ArrayList.class);
            View.printNumberedStringArrayList(onlineUsers);
        }
    }


}