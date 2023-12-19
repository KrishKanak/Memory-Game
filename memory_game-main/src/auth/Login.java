
package auth;

import mechanism.Game;
import nav.Navigation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Login {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    public static String username;
    public static boolean login() throws Exception {
        Connection conn =  MyConnection.getConnection();
        PreparedStatement ps;

        try {
            Scanner login_scanner = new Scanner(System.in);

            System.out.print("Name: ");
            username = login_scanner.nextLine();

            System.out.print("Password: ");
            String pwd = login_scanner.nextLine();

            String query = "SELECT player_name, pwd FROM player WHERE player_name = ? AND pwd=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pwd);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String rsName = rs.getString("player_name");
                    String rsPwd = rs.getString("pwd");

                    if (username.equals(rsName) && pwd.equals(rsPwd)) {
                        System.out.println("Login Success! Welcome " + ANSI_PURPLE + username + ANSI_RESET);
                        Game.playGame();
                        return true;
                    } else {
                        System.out.println(ANSI_RED + "Incorrect name or password!" + ANSI_RESET);
                        return false;
                    }
                }
            }
            login_scanner.close();
            MyConnection.closeConnection(conn); // close the connection
        }

        catch (Exception e) {
            System.out.println(e.getMessage() + ". Something went wrong.");

            Navigation.getNav();
        }

        return false;
    }

    public static String getUsername(){
        return username;
    }
}
