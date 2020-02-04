import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;



public class Main {

    public static Scanner scanner;


    public static void main(String[] args) {

//        SavingObjects.addingPlantsToPlantsArrayList("plants.txt");
        View.startHeader_2();
        Menu.loginMenu();
    }

    public static ArrayList<String> readFileLineByLine(Scanner scanner, String fileName) {
        try {
            scanner = new Scanner(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<>();
        String string = null;
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            lines.add(string);
        }
        return lines;
    }
    
}