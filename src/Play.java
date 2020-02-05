import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Play Type Indexes:
// Day: 1
// Water: 2
// Rail: 3
// Zombie: 4
// PvP: 5

public class Play {


    public static Plant selectedPlant = null;

    public static void goToPlayByPlayType(int playTypeIndex) throws IOException {
        switch (playTypeIndex) {
            case 1:
                PlayGround.BuildDayPlayGround();
                Day.dayAndWaterTurn(1);
                break;
            case 2:
                PlayGround.BuildWaterPlayGround();
                Water.dayAndWaterTurn(2);
                break;
            case 3:
                PlayGround.BuildDayPlayGround();
                Rail.railTurn();
                break;
            case 4:
                int index = View.choosePlayGroundInZombieStyle();
                if (index == 1) {
                    PlayGround.BuildDayPlayGround();
                    ZombieStyle.zombieStyleTurn(false);
                } else if (index == 2) {
                    PlayGround.BuildWaterPlayGround();
                    ZombieStyle.zombieStyleTurn(true);
                }
                break;
            case 5:
                // in this line: main account = zombie player
                PlayGround.BuildDayPlayGround();
                PvP.pvpTurn();
                break;
            default:
                System.out.println("$$$ playType index Error in Play class");
                break;
        }
    }


    public static String getPlayType (int playTypeIndex) {
        switch (playTypeIndex) {
            case 1:
                return "Day";
            case 2:
                return "Water";
            case 3:
                return "Rail";
            case 4:
                return "Zombie";
            case 5:
                return "PvP";
        }
        return null;
    }




     protected static boolean allZombiesAreDead() {
        for (int i = 0; i < 6; i++)
            for (int j = 1; j < 20; j++)
                if (PlayGround.getSpecifiedUnit(i, j).getZombies().size() > 0)
                    return false;
        return true;
    }
}
