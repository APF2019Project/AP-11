import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

public class Main {

    public static Scanner scanner;


    public static void main(String[] args) {

        View.startHeader();
        Menu.loginMenu();

//        YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
//        YaGson yaGson = yaGsonBuilder.create();
//
//        ArrayList<String> al = readFileLineByLine(scanner, "plants.txt");
//        ArrayList<Plant> plants2 = new ArrayList<>();
//
//        for (int i = 0; i < al.size(); i++) {
//            plants2.add(yaGson.fromJson(al.get(i), Plant.class));
//        }
//
//        System.out.println("plants:");
//        for (Plant plant : Plant.plants) {
//            System.out.println(plant.getName());
//        }
//        System.out.println();
//        System.out.println();
//        for (Plant plant : plants2) {
//            System.out.println(plant.getName());
//        }



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

    public static void printArrayList(ArrayList<String> arrayList) {
        for (Object object : arrayList) {
            System.out.println(object);
        }
    }
}