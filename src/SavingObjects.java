import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SavingObjects {

    public static Scanner scanner;

    public static YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
    public static YaGson yaGson = yaGsonBuilder.create();


    public static ArrayList<Plant> addingPlantsToPlantsArrayList(String fileName, Scanner scanner) {
        ArrayList<Plant> tempPlants = new ArrayList<>();
        ArrayList<String> jsonPlants = readFileLineByLine(scanner, "plants.txt");
        for (int i = 0; i < jsonPlants.size(); i++) {
            System.out.println(i);
            tempPlants.add(yaGson.fromJson(jsonPlants.get(i), Plant.class));
        }
        return tempPlants;
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
