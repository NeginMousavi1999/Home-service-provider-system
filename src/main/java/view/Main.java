package view;

import dao.UserDao;

/**
 * @author Negin Mousavi
 */
public class Main {
    public static void main(String[] args) {
        printHomeLogo();

        UserView userView = new UserView();
        userView.createUser();

    }

    private static void printHomeLogo() {
        System.out.println("                                   ___\n" +
                "                                   T T\n" +
                "                                   ===\n" +
                "                                   |.|\n" +
                "                                  .'.`.\n" +
                "                                .'.' `.`.\n" +
                "                  %%          .'.' ___ `.`.\n" +
                "                 %%%%       .'.'  |_|_|  `.`.\n" +
                "                %%%%%%    .'.'    |_|_|    `.`.\n" +
                "                %%%%%__.--`'| []  |_|_|  [] |`'---.\n" +
                "__              %%%%|------||               |||||||\n" +
                " /\\     =========%%%|    _&||      ___      ||===='\n" +
                "/  \\   ///////////H/| j |  ||     |_|_|     ||    |\n" +
                "||||  ////////////H%|   |- ||     |_|_|     ||____|\n" +
                "|||| /////////////H/|   |  ||     |_|_|     ||  TT|       .   &\n" +
                "|||| @@@@@@@@@@@@@H@|======||               ||====|  \"==='   (f\n" +
                "|\\//|\\/|/\\//\\||//|\\|||/\\//|//\\||\\//||//|\\||\\||\\/|/\\//\\||////|//\\/||\n");
    }
}
