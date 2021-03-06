package Project.UI;

import Project.modules.GameManager;
import Project.modules.Player;
import com.sun.corba.se.impl.orb.ParserTable;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public final class UserIteration {
    private static final char EXIT = 'q';

    public static int MainMsg(){
        Scanner reader = new Scanner(System.in);
        int userChoice = 0;

        do {
            try {
                System.out.println("--------BattelShips Game--------");
                System.out.println("1. Load game configuraion");
                System.out.println("2. Play new game");
                System.out.println("6. Exit");
                System.out.println("--------BattelShips Game--------");
                System.out.println("Please choose a number from the manu:");
                userChoice = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid number. ");
                System.out.print("Please insert number between 1-2\n");

            }
            reader.nextLine();
        } while (userChoice < 1 || userChoice > 2 &&  userChoice != 6);

        return userChoice;
    }

    public static int gameManuMsg(){
        Scanner reader = new Scanner(System.in);
        int userChoice = 0;

        do {
            try {
                System.out.println("--------BattelShips Game--------");
                System.out.println("3. Show game situation");
                System.out.println("4. Attack");
                System.out.println("5. Show statistics");
                System.out.println("6. Exit");
                System.out.println("7. Put Mine");
                System.out.println("--------BattelShips Game--------");
                System.out.println("Please choose a number from the manu:");
                userChoice = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid number. ");
                System.out.print("Please insert number between 1-2\n");

            }
            reader.nextLine();
        } while (userChoice < 3 || userChoice > 7);

        return userChoice;
    }

    public static Point getPointFromPlayer(Player player, int boardSize){
        int asciBoardSize = 64 + boardSize;
        char lastColumn = (char)asciBoardSize;
        int x, y;
        char yChar;
        Scanner reader = new Scanner(System.in);
        Point hit = new Point(0,0);
        boolean validHit = false;
        long timeStart = System.currentTimeMillis();

        while (!validHit) {
            try {
                System.out.println(player.getName() + " choose place to hit: ");
                System.out.println("Please enter a column between: A to " + lastColumn);
                yChar = reader.next().charAt(0);
                yChar = Character.toUpperCase(yChar);
                System.out.println(yChar);
                if(yChar == EXIT){
                    System.out.println("Player required. Thank you for playing...");
                    exit(1);
                }
                y = (int) yChar - 65;
                System.out.println("Please enter a row between: 1 to " + boardSize);
                x = reader.nextInt();
                validHit = checkIfHitIsValid(x -1,y, boardSize);
                hit.setLocation(x -1 , y);
            }catch (InputMismatchException e){
                System.out.print("Invalid Hit. ");
            }
            reader.nextLine();
        }

        player.setAvgTimeForMove(GameManager.calculateTotalTime(timeStart));

        return hit;
    }

    private static boolean checkIfHitIsValid(int x, int y, int boardSize) {
        if(y >= 0 && y <= boardSize && x >= 0 && x <= boardSize){
            return true;
        }
        System.out.println("your hit is out of the board!");
        return false;
    }

    public static void goodShotMsg(){
        System.out.println("Good Shot! play again...");
    }

    public static void badShotMsg(){
        System.out.println("You missed...");
    }

    public static void alreadyAttackedMsg(){
        System.out.println("You already attacked this cell! play again...");
    }

    public static void printWinMsg(Player player1, Player player2){
        if(player1.getScore() > player2.getScore()) {
            System.out.println("Well Played! " + player1.getName() + " wins!");
            System.out.println("Score: " + player1.getScore());
        }
        else{
            System.out.println("Well Played! " + player2.getName() + " wins!");
            System.out.println("Score: " + player2.getScore());
        }
    }

    public static void showStatisticsMsg(int i_numOfTurns, double i_totalTime, int i_score, int i_missed, long i_avgTimeForMove){
        System.out.println("------Statistics------");
        System.out.println("Amount of turns: " + i_numOfTurns);
        System.out.println("Total time: " + i_totalTime);
        System.out.println("Total score : " + i_score);
        System.out.println("Total missed shots: " + i_missed);
        System.out.println("Avarage time for move: " + i_avgTimeForMove);
        System.out.println("------Statistics------");
    }

    public static void printResultsAndStatistics(Player player1, Player player2, int i_numOfTurns, double i_totalTime){
        player1.getMyBoard().printMyBoard(player1);
        player2.getMyBoard().printMyBoard(player2);
        System.out.println(player1.getName() + " Retired from the game");
        System.out.println("THE WINNER IS :" + player2.getName() + "!!!");
        System.out.println("Amount of turns: " + i_numOfTurns);
        System.out.println("Total time: " + i_totalTime);
        System.out.println("------Results " + player1.getName() + " ------");
        System.out.println("Total score : " + player1.getScore());
        System.out.println("Total missed shots: " + player1.getMissed());
        System.out.println("Avarage time for move: " + player1.getAvgTimeForMove() / (double)i_numOfTurns);
        System.out.println("-------------------------");
        System.out.println("------Results " + player2.getName() + " ------");
        System.out.println("Total score : " + player2.getScore());
        System.out.println("Total missed shots: " + player2.getMissed());
        System.out.println("Avarage time for move: " + player2.getAvgTimeForMove());
        System.out.println("---------------------");
    }

    public static void noMoreMinesMsg() {
        System.out.println("No mines left!");
    }

    public static void mineMsg(String name) {
        System.out.println(name + " you hit a mine! self attack...");
    }

    public static void mineLocatedNotValidMsg() {
        System.out.println("Your mine located is unvalid. try again...");
    }

    public static String getFullPathMsg() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a file full path ");
        String fullPath = reader.next();

        return fullPath;
    }
}
