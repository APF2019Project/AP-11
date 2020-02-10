import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.internal.$Gson$Preconditions;
import com.gilecode.yagson.com.google.gson.internal.bind.util.ISO8601Utils;

import javax.crypto.spec.PSource;
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

                while (clientTestAccount.clientUsername != null) {
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
                        String serverAns = null;
                        try {
                            serverAns = socketScanner.nextLine();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (serverAns.equals("new message")) {
                            String messageJson = socketScanner.nextLine();
                            YaGson yaGson = new YaGson();
                            Message2 message = yaGson.fromJson(messageJson, Message2.class);
                            ClientChat.showNotif(message);
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

    public static void logout() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("logout");
            printer.println(clientUsername);
        }
        clientTestAccount.clientUsername = null;
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
            printer.println("shop2");
            printer.println("show collection");
            printer.println(clientTestAccount.clientUsername);
            String plantsJson = socketScanner.nextLine();
            String zombiesJson = socketScanner.nextLine();
            YaGson yaGson = new YaGson();
            ArrayList<Plant> plants = yaGson.fromJson(plantsJson, ArrayList.class);
            ArrayList<Zombie> zombie = yaGson.fromJson(zombiesJson, ArrayList.class);
            View.printCollections(plants, zombie);
        }
    }

    public static void buy(String cardName, String username) throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop2");
            printer.println("buy");
            printer.println(clientTestAccount.clientUsername);
            printer.println(cardName);

            String serverAnswer = socketScanner.nextLine();
            if (serverAnswer.equals("-1")) {
                System.out.println("Invalid card name.");

            } else if (serverAnswer.equals("-2")) {
                System.out.println("This card is already in your collection.");

            } else if (serverAnswer.equals("-3")) {
                System.out.println("Not enough money.");

            } else if (serverAnswer.equals("done")) {
                System.out.println(cardName + " added to your plants collection");

            } else if (serverAnswer.equals("-4")) {
                System.out.println("This card is sold out.");
            }


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

    public static void createCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create new plant or zombie?");
        String plantOrZombie = scanner.nextLine();
        if (plantOrZombie.equals("plant")) {
            createPlant();
        } else if (plantOrZombie.equals("zombie")) {
            createZombie();
        }
    }


    public static void createPlant() {
        Scanner scanner = new Scanner(System.in);
        try {

            Shoot normalPea = new Shoot(true, 1, 3, 1, 0);
            Shoot frozenPea = new Shoot(true, 1, 3, 0.5, 1);
            Shoot cabbage = new Shoot(false, 2, 3, 1, 0);
            Shoot kernel = new Shoot(false, 0, 3, 0, 2);
            Shoot melon = new Shoot(false, 3, 3, 1, 0);
            Shoot frozenMelon = new Shoot(false, 3, 3, 0.5, Integer.MAX_VALUE);

            System.out.println("Enter card name:");
            String name = scanner.nextLine();
            System.out.println("Enter health:");
            int health = scanner.nextInt();
            int age = 0;
            System.out.println("Enter sun prince:");
            final int sunCost = scanner.nextInt();
            System.out.println("Select bullet type:");
            System.out.println("normalPea, frozenPea, cabbage, kernel, melon, frozenMelon");
            Scanner scanner1 = new Scanner(System.in);
            String bulletType;
            bulletType = scanner1.nextLine();
            Shoot bullet = null;
            switch (bulletType) {
                case "normalPea":
                    bullet = normalPea;
                    break;
                case "frozenPea":
                    bullet = frozenPea;
                    break;
                case "cabbage":
                    bullet = cabbage;
                    break;
                case "kernel":
                    bullet = kernel;
                    break;
                case "melon":
                    bullet = melon;
                    break;
                case "frozenMelon":
                    bullet = frozenMelon;
                    break;
                default:
                    System.out.println("wrong bullet type");
                    break;
            }
            System.out.println("Enter bullet number:");
            final int bulletNumber = scanner.nextInt();
            System.out.println("Enter melee damage:");
            final int meleeDamage = scanner.nextInt();
            System.out.println("Enter respawn time:");
            final int respawnCoolDown = scanner.nextInt();
            int respawnTime;
            System.out.println("Enter shoot cool down:");
            final int shootCoolDown = scanner.nextInt();
            System.out.println("Enter shoot time:");
            int shootTime = scanner.nextInt();
            System.out.println("Is split pea? (boolean)");
            boolean isSplitPea = scanner.nextBoolean();
            System.out.println("Is cattail? (boolean)");
            final boolean isCattail = scanner.nextBoolean();
            System.out.println("Is this a water plant? (boolean)");
            final boolean canBePlantedInWater = scanner.nextBoolean();
            String type = "range -1 normal";
            System.out.println("Done! Your plant is created and is available on PvZ shop!");

            Plant plant = new Plant(name, health, sunCost, bullet, bulletNumber, meleeDamage, respawnCoolDown,
                    shootCoolDown, isCattail, canBePlantedInWater, type);
            YaGson yaGson = new YaGson();
            String plantJson = yaGson.toJson(plant);

            Socket clientSocket = new Socket("localhost", 6000);
            try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
                 Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
                printer.println("shop2");
                printer.println("new plant");
                printer.println(plantJson);
            }
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            System.out.println("probably wrong input type.");
        }
    }

    public static void createZombie() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter zombie name:");
            String name = scanner.nextLine();
            System.out.println("Have bucket head? (boolean)");
            boolean haveBucketHead = scanner.nextBoolean();
            System.out.println("Could revert to regular zombie? (boolean)");
            boolean couldRevertToRegularZombie = scanner.nextBoolean();
            System.out.println("Could destroy in row? (boolean)");
            boolean couldDestroyInRow = scanner.nextBoolean();
            System.out.println("Does it have random position? (boolean)");
            boolean RandomPosition = scanner.nextBoolean();
            System.out.println("Could jump? (boolean)");
            boolean couldJump = scanner.nextBoolean();
            System.out.println("Turn thief? (int)");
            int turnThief = scanner.nextInt();
            System.out.println("Speed?");
            int speed = scanner.nextInt();
            int currentSpeed;
            System.out.println("How many turn speed is reduced?");
            int howManyTurnSpeedIsReduced = scanner.nextInt();
            System.out.println("Speed reduction ratio?");
            int speedReductionRatio = scanner.nextInt();
            System.out.println("Health?");
            int health = scanner.nextInt();
            System.out.println("Have anti tiq? (boolean)");
            boolean haveAntiTiq = scanner.nextBoolean();
            System.out.println("Is pea proof? (boolean)");
            boolean IsPeaProof = scanner.nextBoolean();
            System.out.println("Shield strength?");
            int shieldStrength = scanner.nextInt();
            System.out.println("Damage power? (int)");
            int damagePower = scanner.nextInt();
            System.out.println("Is water proof? (boolean)");
            boolean IsWaterProof = scanner.nextBoolean();
            System.out.println("Have duck? (boolean)");
            boolean haveDuck = scanner.nextBoolean();
            System.out.println("Have ladder? (boolean)");
            boolean haveLadder = scanner.nextBoolean();
            System.out.println("Done! Your zombie is created and is available on PvZ shop!");

            Zombie zombie = new Zombie(name, haveAntiTiq, haveBucketHead, couldRevertToRegularZombie, couldDestroyInRow,
                    IsWaterProof, IsPeaProof, RandomPosition, haveDuck, couldJump, turnThief, speed,
                    howManyTurnSpeedIsReduced, speedReductionRatio, health, shieldStrength, damagePower, haveLadder);
            YaGson yaGson = new YaGson();
            String zombieJson = yaGson.toJson(zombie);

            Socket clientSocket = new Socket("localhost", 6000);
            try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
                 Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
                printer.println("shop2");
                printer.println("new zombie");
                printer.println(zombieJson);
            }

        } catch (Exception ex) {
            System.out.println("probably wrong input type.");
            ex.printStackTrace();
        }

    }

    public static void buyFromAnotherClient() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you wanna buy from?");
        String seller = scanner.nextLine();
        System.out.println("What card are you looking for?");
        String cardName = scanner.nextLine();

        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {

            printer.println("shop2");
            printer.println("buy from client");
            printer.println(seller);
            printer.println(clientTestAccount.clientUsername);
            printer.println(cardName);

            String serverAns = socketScanner.nextLine();
            if (serverAns.equals("wrong seller")) {
                System.out.println("This user is not available.");
            } else if (serverAns.equals("wrong card name")) {
                System.out.println("This card doesn't exist.");
            } else if (serverAns.equals("not in collection")) {
                System.out.println("It seems like this user doesn't have this card!");
            } else if (serverAns.equals("request sent")) {
                System.out.println("Your buy request has been sent.");
            }

        }

    }
}

class ClientChat {

    static ArrayList<Message2> unreadMessages = new ArrayList<>();

    public static void showNotif(Message2 message) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (message.isNew) {

            if (message.content.toLowerCase().contains("sellreq")) {
                System.out.println();
                System.out.println("    " + "* New Sell Request!");
                System.out.println("    " + "From: " + message.sender);
                unreadMessages.add(message);

            } else if (message.content.toLowerCase().equals("play request")) {

                System.out.println();
                System.out.println("    " + "* New Play Request!");
                System.out.println("    " + "From: " + message.sender);
                unreadMessages.add(message);

            } else {
                System.out.println();
                System.out.println("    " + "* New message from " + message.sender);
                String preview;
                if (message.content.length() > 7) {
                    preview = message.content.substring(0, 7);
                    System.out.println("    " + "preview: " + preview + "...");
                } else {
                    preview = message.content;
                    System.out.println("    " + "preview: " + preview);
                }
                unreadMessages.add(message);
            }
        } else {

        }


    }

    public static void sendMessage(boolean isReply, Message2 otherMessage) throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            String receiver = null;
            String content = null;
            if (isReply) {
                while (true) {
                    receiver = otherMessage.sender;
                    System.out.println("    " + "Reply:");
                    Scanner scanner = new Scanner(System.in);
                    content = scanner.nextLine();
                    if (content.equals("send picture")) {
                        sendPicture();
                    } else {
                        if (content.toLowerCase().equals("end chat")) {
                            break;
                        }
                        Message2 message = new Message2(receiver, clientTestAccount.clientUsername, content, false);
                        ChatRequest request = new ChatRequest(clientTestAccount.clientUsername, receiver, content,
                                ChatRequest.RequestType.sendMessage, message);
                        YaGson yaGson = new YaGson();
                        String requestJson = yaGson.toJson(request);
                        printer.println("chat");
                        printer.println(requestJson);
                    }
                }

            } else {
                System.out.println("___Select an online username:");
                receiver = View.input();
                System.out.println("___Enter content in one line:");
                content = View.input();
                if (content.equals("send picture")) {
                    sendPicture();
                } else {
                    System.out.println("___Message sent.");

                    printer.println("chat");

                    Message2 message = new Message2(receiver, clientTestAccount.clientUsername, content, true);
                    ChatRequest request = new ChatRequest(clientTestAccount.clientUsername, receiver, content,
                            ChatRequest.RequestType.sendMessage, message);
                    YaGson yaGson = new YaGson();
                    String requestJson = yaGson.toJson(request);
                    printer.println(requestJson);
                }
            }


        }
    }

    public static void startChat(Message2 message) {

    }

    public static void showOnlineUsers() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("chat");
            ChatRequest request = new ChatRequest(clientTestAccount.clientUsername, null, null,
                    ChatRequest.RequestType.showOnlineUsers, null);
            YaGson yaGson = new YaGson();
            String requestJson = yaGson.toJson(request);
            printer.println(requestJson);
            String onlineUsersJson = socketScanner.nextLine();
            String offlineUsernamesJson = socketScanner.nextLine();
            ArrayList<String> onlineUsers = yaGson.fromJson(onlineUsersJson, ArrayList.class);
            ArrayList<String> offlineUsers = yaGson.fromJson(offlineUsernamesJson, ArrayList.class);
            System.out.println("Online users:");
            View.printNumberedStringArrayList(onlineUsers);
            System.out.println("Offline users:");
            View.printNumberedStringArrayList(offlineUsers);
            System.out.println();
        }
    }

    public static void showNewMessages() throws IOException {
        System.out.println("-- New Messages --");
        System.out.println("You have " + unreadMessages.size() + " new messages");
        if (unreadMessages.size() > 0) {
            View.printNewMessages(unreadMessages);
            System.out.println("");
            System.out.println("Select message number to reply, or 0 to exit:");
            String number = View.input();
            Message2 message = unreadMessages.get(Integer.parseInt(number) - 1);
            System.out.print(unreadMessages.get(Integer.parseInt(number) - 1).sender + ": ");
            System.out.println(unreadMessages.get(Integer.parseInt(number) - 1).content);

            if (unreadMessages.get(Integer.parseInt(number) - 1).content.toLowerCase().contains("sellreq")) {
                String cardName = message.getCardName();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Do you sell?");
                String answer = scanner.nextLine();
                if (answer.toLowerCase().equals("yes")) {
                    exchangeCard(message.receiver, message.sender, cardName);
                    NotifyBuyer(message.sender, message.receiver);
                    unreadMessages.remove(unreadMessages.get(Integer.parseInt(number) - 1));

                } else if (answer.toLowerCase().equals("no")) {
                    System.out.println("Ok, request denied.");
                    unreadMessages.remove(unreadMessages.get(Integer.parseInt(number) - 1));
                }

            } else if (unreadMessages.get(Integer.parseInt(number) - 1).content.toLowerCase().contains("play request")) {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Do accept this play request?");
                String answer = scanner.nextLine();
                if (answer.toLowerCase().equals("yes")) {
                    ClientCollection.setAccount();
                    ClientMenus.collectionMenu(4, true);
                    ClientPlay.notifyPlay(message.sender);

                } else if (answer.toLowerCase().equals("no")) {
                    System.out.println("Ok, request denied.");
                    unreadMessages.remove(unreadMessages.get(Integer.parseInt(number) - 1));
                }

            } else if (unreadMessages.get(Integer.parseInt(number) - 1).content.toLowerCase().contains("your play request has been")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Start the game?");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    ClientMenus.dayMenu(1, true);
                }
            } else {
                // Regular message:
                if (Integer.parseInt(number) == 0) {
                    return;
                }
                System.out.println("reply in one line:");
                String content = View.input();
                if (content.equals("send picture")) {
                    sendPicture();
                } else {
                    System.out.println("message sent.");
                    String receiver = unreadMessages.get(Integer.parseInt(number) - 1).sender;
                    String sender = clientTestAccount.clientUsername;
                    Message2 message2 = new Message2(receiver, sender, content, true);
                    Socket clientSocket = new Socket("localhost", 6000);
                    try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
                         Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
                        printer.println("chat");
                        ChatRequest request = new ChatRequest(sender, receiver, content, ChatRequest.RequestType.sendMessage,
                                message2);
                        YaGson yaGson = new YaGson();
                        String requestJson = yaGson.toJson(request);
                        printer.println(requestJson);
                    }
                    unreadMessages.remove(unreadMessages.get(Integer.parseInt(number) - 1));
                }
            }
        }
    }

    private static void NotifyBuyer(String buyer, String seller) throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop2");
            printer.println("sellReq accepted");
            printer.println(buyer);
            printer.println(seller);

        }

    }


    public static void exchangeCard(String sellerUsername, String buyerUsername, String cardName) throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("shop2");
            printer.println("exchange card");
            printer.println(sellerUsername);
            printer.println(buyerUsername);
            printer.println(cardName);
            String serverAns = socketScanner.nextLine();
            if (serverAns.equals("not enough money")) {
                System.out.println("Not enough money!");
            } else if (serverAns.equals("done")) {
                System.out.println("Done!");
            }
        }
    }

    public static void sendPicture() {
        System.out.println("Sadly not implemented :((");
    }


}

class ClientCollection {

    public static void setAccount() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("collection");
            printer.println("set collections");
            printer.println(clientTestAccount.clientUsername);
            String accountJson = socketScanner.nextLine();
            YaGson yaGson = new YaGson();
            Account account = yaGson.fromJson(accountJson, Account.class);
            Account.setMainPlayingAccount(account);
        }
    }

}

class ClientPlay {

    public static void saveAfterGame() throws IOException {
        Socket clientSocket = new Socket("localhost", 6000);
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("play");
            printer.println("save after game");
            Account account = Account.getMainPlayingAccount();
            YaGson yaGson = new YaGson();
            String accountJson = yaGson.toJson(account);
            printer.println(accountJson);
        }
    }

    public static void sendPlayRequest() {
        Scanner scanner = new Scanner(System.in);
        try {
            ClientChat.showOnlineUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Which player are you sending request to?");
        String player = scanner.nextLine();
        String content = "play request from " + clientTestAccount.clientUsername;
        Message2 message = new Message2(player, clientTestAccount.clientUsername, content, true);
        YaGson yaGson = new YaGson();
        String messageJson = yaGson.toJson(message);

        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 6000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            printer.println("play");
            printer.println("play request");
            printer.println(messageJson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void notifyPlay(String user) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 6000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (PrintStream printer = new PrintStream(clientSocket.getOutputStream());
             Scanner socketScanner = new Scanner(clientSocket.getInputStream())) {
            String content = "Your play request has been accepted";
            Message2 message = new Message2(user, clientTestAccount.clientUsername, content, true);
            YaGson yaGson = new YaGson();
            String messageJson = yaGson.toJson(message);
            printer.println("play");
            printer.println("play accepted");
            printer.println(messageJson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}