public class PvP {

    static int waveNumbers;
    static boolean IsPvPTrue;
    static Account account1;
    static Account account2;
    static int ZombieWinNumber = 0;
    static int PlantWinNumber = 0;

    public static void pvpTurn() {
        account1 = Account.getPlayingAccount(); // It is for plantPlayingAccount
        account2 = Account.getZombiePlayingAccount();
        IsPvPTrue = true;
        setWaveNumber(Integer.MAX_VALUE); // could be any number
        while (IsPvPTrue) {
            if (checkFinished()) {
                doFinalThings("Zombie");
                return;
            }
            Shoot.shootTurn();
            // shoot turn is done

            if (checkFinished()) {
                doFinalThings("Plant");
                return;
            }

            Plant.plantsTurn();
            Menu.dayMenu(1); // probably need playingAccount
            // plant is done

            if (checkFinished()) {
                doFinalThings("Plant");
                return;
            }

            Zombie.zombiesTurn();
            Menu.zombieMenu();
            // zombie is done
        }

        Menu.pvpMenu();
    }

    private static void setWaveNumber(int waveNum) {
        waveNumbers = waveNum;
    }

    private static void doFinalThings(String whoWon) {
        if (whoWon.equals("Zombie")){
            ZombieWinNumber++;
            ZombieStyle.setCoins(ZombieStyle.getCoins() + 200);

        }
        else if (whoWon.equals("Plant")){
            PlantWinNumber++;
        }
        if (ZombieStyle.getCoins() < ZombieStyle.getLowestZombieCost()){
            // game is finished
        }
    }

    private static boolean checkFinished() {
        boolean flag = false;
        for (int i = 0; i < 6; i++)
            if (PlayGround.getSpecifiedUnit(i, 0).getZombies().size() > 0) {
                PlayGround.getSpecifiedUnit(i, 0).getZombies().clear();
                flag = true;
            }

        if (flag)
            return true;
        if (ZombieStyle.allZombiesAreDead()) {
            return true;
        }

        return false;
        // sadjad will code it
    }

}
