package mechanism;

import nav.Navigation;
import auth.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Scoreboard {
    public static void getScoreboard() throws SQLException, ClassNotFoundException {
        Connection conn = MyConnection.getConnection();

        Statement stmt;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            String query = """
                    SELECT plr.player_name, game.game_date, game.score
                    FROM activity actvy
                             INNER JOIN player plr
                                        ON actvy.player_id = plr.player_id
                             INNER JOIN game
                                        ON actvy.game_id = game.game_id
                    ORDER BY game.score
                    """;

            rs = stmt.executeQuery(query);
            System.out.println("Player   |  Date    |    Score");
            System.out.println("------------------------------");

            while (rs.next()){
                String RsPlayerName = rs.getString("player_name");
                String RsGameDate = rs.getString("game_date");
                int RsScore = rs.getInt("score");

                System.out.println(RsPlayerName + "   |   " + RsGameDate + "   |   " + RsScore);
            }
        }

        catch(SQLException ex){
            System.out.println(ex.getMessage() + ". Something went wrong.");
        }

        MyConnection.closeConnection(conn);// close the connection
        Navigation.getNav();
    }
}
