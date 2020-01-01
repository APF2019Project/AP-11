public class PvP {

    static int waveNumbers;
    static boolean IsPvPTrue;
    static Account account1;
    static Account account2;

    public static void pvpTurn() {
        account1 = Account.getPlayingAccount(); // It is for plantPlayingAccount
        account2 = Account.getZombiePlayingAccount();
        IsPvPTrue = true;
        setWaveNumber(3); // could be any number
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
        if (IsPvPTrue) {
            if (waveNumbers > 0) {
                waveNumbers--;
            } else {
                IsPvPTrue = false;
            }
        }
    }

    private static boolean checkFinished() {
        return false;
        // sadjad will code it
    }

}
