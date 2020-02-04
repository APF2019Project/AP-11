import com.gilecode.yagson.YaGson;

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
                        clientLogin(printer, reader);

                    } else if (mode.equals("leaderboard")) {
                        clientLeaderboard(printer, reader);

                    } else if (mode.equals("change password")) {
                        clientChangePassword(printer, reader);

                    } else if (mode.equals("rename account")) {
                        clientRenameAccount(printer, reader);

                    } else if (mode.equals("delete account")) {
                        clientDeleteAccount(printer, reader);

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

    public static void clientLogin(PrintStream printer, Scanner reader) {
        String username = reader.nextLine();
        String password = reader.nextLine();
        boolean canLogin = Account.userAndPassMatch(username, password);
        if (canLogin) {
            printer.println("matches");
        } else {
            printer.println("not match");
            return;
        }
        Account thisAccount = Account.getAccountByUsername(username);
        Account.onlineAccounts.add(thisAccount);
        notifyOthers(thisAccount);
        Collection.setDefaultPlantsCollection(thisAccount);
        Collection.setDefaultZombiesCollection(thisAccount);

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

    public static void notifyOthers(Account account) {
        for (Account accountIterator : Account.onlineAccounts) {

        }
    }
}

