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

    //        account1 = Account.getMainPlayingAccount(); // It is for plantPlayingAccount
//        account2 = Account.getSecondPlayingAccount();

    public static void pvpTurn() {
        // in this line: main account = zombie player

        IsPvPTrue = true;
        setWaveNumber(Integer.MAX_VALUE); // could be any number
        while (IsPvPTrue) {
            // in this line: main account = zombie player
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
            Day.increaseSun();
            Plant.plantsTurn();
            Menu.dayMenu(1);

            // probably need playingAccount
            //*** switch
            Menu.dayMenu(1); // probably need playingAccount
            // plant is done

            if (checkFinished()) {
                doFinalThings("Plant");
                return;
            }
            //*** switch
            Zombie.zombiesTurn();
            Menu.zombieMenu(false, true, ZombieStyle.waveFinished());
            ///
            ////
            Menu.zombieMenu(false, true, ZombieStyle.waveFinished());
            /////
            ZombieStyle.popZombiesToPlayground();
            // zombie is done
        }
    }

    private static void setWaveNumber(int waveNum) {
        waveNumbers = waveNum;
    }

    private static void doFinalThings(String whoWon) { // sajad should add Account.numOfKilledZombiesHandlingInAccount(false);
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
            IsPvPTrue = false;
            return;
        }

        PlayGround.BuildDayPlayGround();
        IsPvPTrue = true;
    }

    private static void gameFinisher() {
        if (PlantWinNumber >= ZombieWinNumber){
            View.printWinnerOfTheGame(account1);
        }
        else {
            View.printWinnerOfTheGame(account2);
        }

        Account.setSecondPlayingAccount(null);
        Menu.aGameHasFinished = 1;
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
