import com.sun.jdi.connect.Connector;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineAccount {

    public static ArrayList<OnlineAccount> onlineAccounts = new ArrayList<>();

    Socket socket;
    Account account;
    private PrintStream reader;
    private Connector connector;
    public ArrayList<Message> messages;
    public ArrayList<Message2> message2s;

    public OnlineAccount(Socket socket, Account account) {
       this.socket = socket;
       this.account = account;
       this.message2s = new ArrayList<>();
       onlineAccounts.add(this);
    }

    public static OnlineAccount getOnlineAccount(String username) {
        for (OnlineAccount onlineAccountIterator : onlineAccounts) {
            if (onlineAccountIterator.account.getUsername().equals(username)) {
                return onlineAccountIterator;
            }
        }
        return null;
    }

    public static ArrayList<String> getOnlineUsernames() {
        ArrayList<String> onlineUsernames = new ArrayList<>();
        for (OnlineAccount onlineAccountIterator : onlineAccounts) {
            onlineUsernames.add(onlineAccountIterator.account.getUsername());
        }
        return onlineUsernames;
    }

    public static boolean isOnline(String username) {
        for (OnlineAccount onlineAccountIterator : onlineAccounts) {
            if (onlineAccountIterator.account.getUsername().equals(username))
                return true;
        }
        return false;
    }


}
