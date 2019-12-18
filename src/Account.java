import java.util.*;

// All of the outputs that are starting with $$$ are being told to the programmer
// because they might not happen in ideal situation.

public class Account {

    private String username;
    private String password;
    private int money;
    private int killedZombies;
    private int killedPlants;

    private Plant[] plantsDeck = new Plant[7];
    private ArrayList<Zombie> zombiesDeck = new ArrayList<>();
    private ArrayList<Plant> plantsCollection = new ArrayList<>();
    private ArrayList<Zombie> zombiesCollection = new ArrayList<>();

    private static ArrayList<Account> accounts = new ArrayList<>();

    private static Account playingAccount;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.money = 0;
        this.killedPlants = 0;
        this.killedPlants = 0;
    }

    // All setters:
    public void setUsername(String username) {
        this.username = username;
    }

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

    static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***Creating an account***");
        String username;
        String password;
        System.out.println("enter username");
        username = scanner.nextLine();
        if (!accountExists(username)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            accounts.add(new Account(username, password));
            System.out.println("account created");
            Menu.loginMenu();
        } else {
            System.out.println("this username is already taken.\n Try again:");
            createAccount();
        }
    }

    static void login() {
        System.out.println("***LOGIN***");
        String username;
        String password;
        System.out.println("enter username");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        if (accountExists(username)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
            if (getAccountByUsername(username).getPassword().equals(password)) {
                playingAccount = getAccountByUsername(username);
                System.out.println("loged in");
                Menu.mainMenu();
            } else {
                System.out.println("Wrong password. Try again:");
                login();
            }
        } else {
            System.out.println("username doesn't exist.\nTry again:");
            login();
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
            System.out.println("Wrong password passed to the function getAccountByUserAndPass. returning null.");
            return null;
        } else {
            System.out.println("dear programmer, you passed a not existing username to the" +
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
        ArrayList<Account> sortedAccounts = new ArrayList<>(accounts);
        sortedAccounts.sort(new sortAccountsByKilledZombies());
        for (Account accountIterator : sortedAccounts) {
            System.out.println(accountIterator.getUsername() + " " + "Killed zombies: " + accountIterator.getKilledZombies());
        }
        Menu.loginMenu();
    }

}

// This class is used for sorting Accounts by their killed zombies for Leaderboard:
class sortAccountsByKilledZombies implements Comparator<Account> {
    public int compare(Account a, Account b) {
        return a.getKilledZombies() - b.getKilledZombies();

    }
}
