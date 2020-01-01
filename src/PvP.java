public class PvP {

    public static void pvpTurn() {
        Account account1 = Account.getPlayingAccount(); // It is for plantPlayingAccount
        Account account2 = Account.getZombiePlayingAccount();

        boolean IsPvPTrue = true;

        while (IsPvPTrue) {

            if (checkFinished()) {
                doFinalThings();
                return;
            }
            Shoot.shootTurn();
            // shoot turn is done



        }

        Menu.pvpMenu();
    }

    private static void doFinalThings() {
    }

    private static boolean checkFinished() {
    }

}
