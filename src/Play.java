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

    public static void goToPlayByPlayType(int playTypeIndex) {
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
                    ZombieStyle.zombieStyleTurn();
                } else if (index == 2) {
                    PlayGround.BuildWaterPlayGround();
                    ZombieStyle.zombieStyleTurn();
                }
                break;
            case 5:
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



}
