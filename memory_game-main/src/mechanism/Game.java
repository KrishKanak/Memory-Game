package mechanism;

import auth.Login;
import nav.Navigation;
import auth.MyConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Game{
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final Scanner scanner = new Scanner(System.in);
    public static String uname = Login.getUsername();
    private static final LocalDate localDate = LocalDate.now();
    private static final String [][] board = new String [4][5];
    private static final String [][] cards = new String  [4][5];

    private static int attemptsLeft = 10;
    private static int score;

    // Method to print the board
    private static void printBoard(){
        System.out.println("     1     2     3     4     5");
        for(int i = 0; i < board.length; i++){
            System.out.print((i + 1) + " |");
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // shuffles the card in a random order
    private static void shuffleCards(){
        Random rd = new Random();
        String[] cardValues = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        ArrayList<String> cardsToProcess = new ArrayList<>(20);

        // copy each element of the array
        for(String element: cardValues){
            cardsToProcess.addAll(Collections.nCopies(2, element));
        }

        // Shuffle the cards
        int idx;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                idx = rd.nextInt(cardsToProcess.size());
                cards[i][j] = cardsToProcess.get(idx);
                cardsToProcess.remove(idx);
                System.out.println(cards[i][j]);
            }
        }
    }

    // checks the input of the user
    private static void checkInput() throws ClassNotFoundException, SQLException {
        while(true){
            if(!gameOver()){
                System.out.print("Select the row from 1-4: ");
                int row1 = scanner.nextInt();

                System.out.print("Select the row from 1-5: ");
                int column1 = scanner.nextInt();

                if(!board[row1-1][column1-1].equals("  -  ")){
                    System.out.println("Already Entered!!");
                    System.out.println();

                    printBoard();
                    continue;
                } else {
                    board[row1-1][column1-1] = "  " + Game.cards[row1-1][column1-1] + "  ";
                    printBoard();
                }

                System.out.print("Select the row from 1-4: ");
                int row2 = scanner.nextInt();

                System.out.print("Select the row from 1- 5: ");
                int column2 = scanner.nextInt();

                if(!board[row2-1][column2-1].equals("  -  ")){
                    System.out.println("Already Entered!!");

                    board[row1-1][column1-1] = "  -  ";
                    System.out.println();

                    printBoard();
                }else{
                    board[row2-1][column2-1] = "  " + Game.cards[row2-1][2-1] + "  ";

                    if(board[row1-1][column1-1].equals (board[row2-1][column2-1])){
                        printBoard();
                        System.out.println( ANSI_GREEN + "Correct guess!" + ANSI_RESET);
                        score += 2;
                    }else{
                        printBoard();

                        System.out.println(ANSI_RED + "Incorrect guess!" + ANSI_RESET);

                        board[row1-1][column1-1] = "  -  ";
                        board[row2-1][column2-1] = "  -  ";

                        printBoard();

                        attemptsLeft--; // Reduce the chances
                        System.out.println("Your remaining attempts: " + attemptsLeft);
                        getScore();

                    }
                }
            }else{
                getScore();
                break;
            }
        }
    }

    private static boolean gameOver() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("  -  ")) {
                    return false;
                }
            }
        }

        return true;
    }

    // This method adds the bonus if the player found all cards
    private static void addBonus() {
        int bonus = (10 - (10 - attemptsLeft)) * 3;
        score += bonus;
        System.out.println("Your score:" +  score);
    }

    // This method to inserts the score into the database
    private static void insertScore() throws ClassNotFoundException, SQLException {
        Connection conn = MyConnection.getConnection();
        PreparedStatement ps1, ps2;

        try {
            String id = UUID.randomUUID().toString();     // create an uuid for each game played

            String queryGame = "INSERT INTO game(game_uuid, game_date, score) VALUES(?,?,?)";
            String queryActivity = "INSERT INTO activity(player_id, game_id, commentary) VALUES((SELECT player_id from player where player_name = ?), (SELECT game_id FROM game WHERE game_uuid = ?), 'played a game')";
            ps1 = conn.prepareStatement(queryGame);
            ps2 =  conn.prepareStatement(queryActivity);

            ps1.setObject(1, id);
            ps1.setDate(2, Date.valueOf(localDate));
            ps1.setInt(3, score);

            ps2.setString(1, uname);
            ps2.setObject(2, id);

            ps1.executeUpdate();
            ps2.executeUpdate();
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

        MyConnection.closeConnection(conn);
    }

    // This is the main method to  control the score system
    private static void getScore() throws ClassNotFoundException, SQLException {
        if (attemptsLeft == 0) {
            System.err.println("You have zero attempts left. Game over!");
            System.out.println("Your score: " + ANSI_BLUE+ score + ANSI_RESET);

            insertScore();
            Navigation.getNav();
        } else if(gameOver()){
            addBonus();
        }
    }

    // This the main method of the game class file
    public static void playGame() throws Exception {
        while(true){
            shuffleCards();

            // hide the card value with a dash
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = "  -  ";
                }
            }

            printBoard();
            checkInput();

            Navigation.getNav();
        }
    }
}
