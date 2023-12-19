package nav;

import auth.Login;
import auth.Signup;
import auth.UpdateAccount;
import mechanism.Scoreboard;

import java.sql.SQLException;
import java.util.Scanner;



public class Navigation {
    static final String menu =
        """
            Select the number of your option
            1. Play Game
            2. Sign up
            3. Edit account
            4. See leaderboard
            5. Exit
            
        """;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static void getNav() {
        while(true) {
            try {
                Scanner menu_scanner = new Scanner(System.in);

                System.out.println(ANSI_CYAN + "Welcome to Memory Game" + ANSI_RESET);
                System.out.print(menu);
                System.out.print("User choice: ");
                int options = menu_scanner.nextInt();  // Read user input

                boolean loginSucess = false;

                while (!loginSucess) {
                    if (options == 1) {
                        loginSucess = Login.login();
                    } else if (options == 2) {
                        Signup.signup(); // other name for main
                    } else if (options == 3) {
                        UpdateAccount.changeData();
                    } else if (options == 4) {
                        Scoreboard.getScoreboard();
                    } else if (options == 5) {
                        break;
                    } else {
                        System.out.println("Please select the correct number!");
                        break;
                    }
                }

                menu_scanner.close();
            }

            catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage() + "Something went wrong!");
            }

            catch (Exception e) {
                System.out.println("Incorrect input.");
            }
        }
    }
}
