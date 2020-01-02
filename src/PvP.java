public class PvP {

    static int waveNumbers;
    private static boolean IsPvPTrue = true;
    static Account account1;
    static Account account2;
    static int ZombieWinNumber = 0;
    static int PlantWinNumber = 0;

    public static void setIsPvPTrue(boolean isPvPTrue) {
        IsPvPTrue = isPvPTrue;
    }

    public static void pvpTurn() {
//        account1 = Account.getMainPlayingAccount(); // It is for plantPlayingAccount
//        account2 = Account.getSecondPlayingAccount();
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
            ////
            Menu.zombieMenu(false, true);
            /////
            ZombieStyle.popZombiesToPlayground();
            // zombie is done
        }
    }

    private static void setWaveNumber(int waveNum) {
        waveNumbers = waveNum;
    }

    private static void doFinalThings(String whoWon) { // sadjad should add Account.numOfKilledZombiesHandlingInAccount(false);
        if (whoWon.equals("Zombie")){
            ZombieWinNumber++;
            ZombieStyle.setCoins(ZombieStyle.getCoins() + 200);

        }
        else if (whoWon.equals("Plant")){
            PlantWinNumber++;
        }

        if (ZombieStyle.getCoins() < ZombieStyle.getLowestZombieCost()){
            // game is finished
            gameFinisher();
            return;
        }

        PlayGround.BuildDayPlayGround();
        PvP.pvpTurn();
    }

    private static void gameFinisher() {
        if (PlantWinNumber >= ZombieWinNumber){
            View.printWinnerOfTheGame(account1);
        }
        else {
            View.printWinnerOfTheGame(account2);
        }

        Account.setSecondPlayingAccount(null);
        return;
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
    }

}
