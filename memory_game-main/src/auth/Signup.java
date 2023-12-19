
package auth;
import nav.Navigation;

import java.util.Scanner;
import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Signup {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void signup() throws ClassNotFoundException, SQLException {
        Connection conn = MyConnection.getConnection();

        Statement stmt;

        try {
            Scanner signup_scanner = new Scanner(System.in);

            System.out.print("Name: ");
            String name = signup_scanner.nextLine();

            System.out.print("Birthdate(YYY-MM-DD): ");
            String birthdate = signup_scanner.nextLine();

            // Parse the date input from the user in MySQL DATE FORMAT
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            } catch (ParseException e) {
                System.out.println(ANSI_RED + "Incorrect Date!" + ANSI_RESET);
            }

            System.out.print("Password: ");
            String password = signup_scanner.nextLine();

            stmt = conn.createStatement();
            String query = "INSERT INTO player(player_name, birthdate, pwd) VALUES('" + name + "','" + birthdate + "','" + password + "')";
            int m = stmt.executeUpdate(query);


            if (m==1) {
                System.out.println("Inserted successfully : " +ANSI_PURPLE + query +  ANSI_RESET);
                Navigation.getNav();
            }else {
                System.out.println("Insertion failed");
            }

            signup_scanner.close();
        }

        catch(SQLException ex){
            System.out.println(ANSI_RED + ex.getMessage() +". Failed to signup." +  ANSI_RESET);
        }

        MyConnection.closeConnection(conn);// close the connection
    }
}
