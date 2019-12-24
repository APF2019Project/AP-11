import java.util.*;

// All of the outputs that are starting with $$$ are being told to the programmer
// because they might not happen in ideal situation.

public class Account {

    private String username;
    private String password;
    private int money;
    private int killedZombies;
    private int killedPlants;

    public Plant[] plantsDeck = new Plant[7];
    public ArrayList<Zombie> zombiesDeck = new ArrayList<>();
    public ArrayList<Plant> plantsCollection = new ArrayList<>();
    public ArrayList<Zombie> zombiesCollection = new ArrayList<>();

    private static ArrayList<Account> accounts = new ArrayList<>();

    private static Account playingAccount;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.money = 0;
        this.killedPlants = 0;
        this.killedZombies = 0;
    }

    // All setters:
    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setKilledZombies(int killedZombies) {
        this.killedZombies = killedZombies;
    }

    public void setKilledPlants(int killedPlants) {
        this.killedPlants = killedPlants;
    }

    public void setPlantsDeck(Plant[] plantsDeck) {
        this.plantsDeck = plantsDeck;
    }

    public void setZombiesDeck(ArrayList<Zombie> zombiesDeck) {
        this.zombiesDeck = zombiesDeck;
    }

    public void setPlantsCollection(ArrayList<Plant> plantsCollection) {
        this.plantsCollection = plantsCollection;
    }

    public void setZombiesCollection(ArrayList<Zombie> zombiesCollection) {
        this.zombiesCollection = zombiesCollection;
    }

    public static void setPlayingAccount(Account playingAccount) {
        Account.playingAccount = playingAccount;
    }


    // All getters:
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public int getKilledZombies() {
        return killedZombies;
    }

    public int getKilledPlants() {
        return killedPlants;
    }

    public Plant[] getPlantsDeck() {
        return plantsDeck;
    }

    public ArrayList<Zombie> getZombiesDeck() {
        return zombiesDeck;
    }

    public ArrayList<Plant> getPlantsCollection() {
        return plantsCollection;
    }

    public ArrayList<Zombie> getZombiesCollection() {
        return zombiesCollection;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static Account getPlayingAccount() {
        return playingAccount;
    }


    // Methods:

    static void createAccount(boolean calledFromLoginMenu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Create account ---");
        String username;
        String password;
        System.out.println("Enter username");
        username = scanner.nextLine();
        if (!accountExists(username)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            accounts.add(new Account(username, password));
            System.out.println("Account created.");
            if (calledFromLoginMenu) {
                System.out.println("Going back to --> Login Menu:");
            } else {
                playingAccount = getAccountByUsername(username);
                System.out.println("Now, you are logged in as: " + username);
                System.out.println("Going to --> your Profile Menu:");
            }
        } else {
            System.out.println("This username is already taken.\n Try again:");
            createAccount(calledFromLoginMenu);
        }
    }


    static void login(boolean calledFromLoginMenu) {
        System.out.println("--- LOGIN ---");
        String username;
        String password;
        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        if (accountExists(username)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            if (getAccountByUsername(username).getPassword().equals(password)) {
                playingAccount = getAccountByUsername(username);
                if (calledFromLoginMenu) {
                    System.out.println("Logged in, going to --> Main Menu:");
                    Collection.setDefaultPlantsDeck();
                    Menu.mainMenu();
                } else {
                    System.out.println("Logged in, going back to --> Profile Menu:");
                }
            } else {
                System.out.println("Wrong password. Try again:");
                login(calledFromLoginMenu);
            }
        } else {
            System.out.println("Username doesn't exist!\nTry again:");
            login(calledFromLoginMenu);
        }
    }


    static boolean accountExists(String username) {
        for (Account accountIterator : accounts) {
            if (accountIterator.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    static Account getAccountByUserAndPass(String username, String password) {
        if (accountExists(username)) {
            for (Account accountIterator : accounts) {
                if (accountIterator.getUsername().equals(username) && accountIterator.getPassword().equals(password)) {
                    return accountIterator;
                }
            }
            System.out.println("$$$ Wrong password passed to the function getAccountByUserAndPass. returning null.");
            return null;
        } else {
            System.out.println("$$$ dear programmer, you passed a not existing username to the" +
                    " function getAccountByUserAndPass. returning null.");
            return null;
        }
    }


    static Account getAccountByUsername(String username) {
        if (accountExists(username)) {
            for (Account accountIterator : accounts) {
                if (accountIterator.getUsername().equals(username)) {
                    return accountIterator;
                }
            }
            System.out.println("$$$ you passed a not existing account to the function getAccountByUsername. returning null");
            return null;
        } else {
            System.out.println("$$$ you passed a not existing account to the function getAccountByUsername. returning null");
            return null;
        }
    }


    static void leaderboard() {
        System.out.println("--- Leaderboard ---");
        ArrayList<Account> sortedAccounts = new ArrayList<>(accounts);
        sortedAccounts.sort(new sortAccountsByKilledZombies());
        for (Account accountIterator : sortedAccounts) {
            System.out.println(accountIterator.getUsername() + " " + "Killed zombies: " + accountIterator.getKilledZombies());
        }
        System.out.println("Going back to --> Login Menu:");
    }


    static void profileMenu() { // Profile Menu index = -3
        Scanner scanner = new Scanner(System.in);
        boolean whileTrue = true;
        while (whileTrue) {
            System.out.println("--- PROFILE MENU ---");
            System.out.println("You are logged in as: " + playingAccount.getUsername());
            System.out.println("Enter command:");
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case "change":
                    System.out.println("Logging in with another account:");
                    login(false);
                    break;
                case "change password":
                    Account.changePassword();
                    break;
                case "delete account":
                    Account.deleteAccount();
                    break;
                case "rename":
                    Account.renameAccount();
                    break;
                case "create account":
                    createAccount(false);
                    break;
                case "show":
                    Account.showAccount();
                    break;
                case "exit":
                    View.goingBackTo(-2); // Going back to Main Menu
                    whileTrue = false;
                    break;
                case "help":
                    View.showHelp(-3);
                    break;
                default:
                    View.invalidCommand(-3);
                    break;
            }
        }
    }


    static void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- DELETE ACCOUNT ---");
        System.out.println("Enter your password:");
        String command = scanner.nextLine();
        if (playingAccount.getPassword().equals(command)) {
            System.out.println("Are you sure you want delete your account?");
            String isSure = scanner.nextLine();
            if (isSure.equals("yes")) {
                accounts.remove(playingAccount);
                playingAccount = null;
                System.out.println("Account deleted. going to --> Login Menu:");
                Menu.loginMenu();
            } else if (isSure.equals("no")) {
                System.out.println("Your account is safe. going back to --> Profile Menu:");
            } else {
                System.out.println("Invalid answer. Try again:");
                deleteAccount();
            }
        } else {
            System.out.println("Wrong password!\nTry again:");
            deleteAccount();
        }
    }


    static void renameAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Rename account ---");
        System.out.println("Enter your password:");
        String input = scanner.nextLine();
        if (playingAccount.getPassword().equals(input)) {
            System.out.println("Enter your new username");
            String newUsername = scanner.nextLine();
            if (accountExists(newUsername)) {
                System.out.println("This username is already taken.\nTry again:");
                renameAccount();
            } else {
                System.out.println("Are you sure you want to change your username?");
                String isSure = scanner.nextLine();
                if (isSure.equals("yes")) {
                    playingAccount.setUsername(newUsername);
                    System.out.println("Your username changed. Going back to --> Profile Menu:");
                } else if (isSure.equals("no")) {
                    System.out.println("Your username is safe. Going back to --> Profile Menu:");
                } else {
                    System.out.println("Invalid answer.\nTry again:");
                    renameAccount();
                }
            }
        } else {
            System.out.println("Wrong password!\nTry again:");
            renameAccount();
        }
    }


    static void showAccount() {
        System.out.println("--- Show account ---");
        System.out.print("You are logged in as: ");
        System.out.println(playingAccount.getUsername());
        System.out.println("Going back to --> Profile Menu:");
    }


    static void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- change password ---");
        System.out.println("Enter you old password:");
        String oldPass = scanner.nextLine();
        if (playingAccount.getPassword().equals(oldPass)) {
            System.out.println("Enter your new password:");
            String newPass = scanner.nextLine();
            playingAccount.setPassword(newPass);
            System.out.println("Your password changed. Going back to --> Profile Menu:");
        } else {
            System.out.println("Wrong password!\nTry again:");
            changePassword();
        }
    }

}

// This class is used for sorting Accounts by their killed zombies for Leaderboard:
class sortAccountsByKilledZombies implements Comparator<Account> {
    public int compare(Account a, Account b) {
        return a.getKilledZombies() - b.getKilledZombies();

    }
}
