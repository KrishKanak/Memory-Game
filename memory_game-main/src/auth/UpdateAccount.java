
package auth;

import nav.Navigation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateAccount {
    public static void changeData() throws ClassNotFoundException {
        Connection conn = MyConnection.getConnection();

        PreparedStatement ps;

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Previous username: ");
            String old_username = sc.next();

            System.out.print("Previous password: ");
            String old_password = sc.next();

            System.out.print("New username: ");
            String new_username = sc.next();

            System.out.print("New password: ");
            String new_password = sc.next();

            String query = "UPDATE player SET player_name = ?, pwd = ? WHERE player_name= ? AND pwd= ?";

            ps = conn.prepareStatement(query);
            ps.setString(1, new_username);
            ps.setString(2, new_password);
            ps.setString(3, old_username);
            ps.setString(4, old_password);

            int m = ps.executeUpdate();

            if (m == 1) {
                System.out.println("Database update is successful");
                Navigation.getNav();
            }else {
                System.out.println("Update failed");
            }

            sc.close();
            MyConnection.closeConnection(conn);  // close the connection
        }

        catch(SQLException | RuntimeException ex){
            ex.printStackTrace();
            System.out.println("Failed to change your credentials");
        }


    }
}
