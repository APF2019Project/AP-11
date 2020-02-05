import com.sun.jdi.connect.Connector;

import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineAccount implements Runnable {

    public static ArrayList<OnlineAccount> onlineAccounts = new ArrayList<>();
    Socket socket;
    Account account;
    private PrintStream reader;
    private Connector connector;

    public OnlineAccount(Socket socket) {
        //todo

    }

    @Override
    public void run() {


    }
}
