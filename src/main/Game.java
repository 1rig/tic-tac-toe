import java.util.Arrays;

/**
 * Created by rgopal on 3/31/17.
 */
public class Game {

    private final int BOARD_SIZE = 3;

    private Player[] players;
    private Character[][] board;

    private Player currentPlayer;
    private int[] remainingBoardPositions ;

    public Player[] getPlayers() {
        return players;
    }

    public Character[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getRemainingBoardPositions() {
        return remainingBoardPositions;
    }


    /**
     * Initialize game with players and board size
     */
    public Game() {
        players = new Player[2];
        players[0] = new HumanPlayer();
        players[1] = new ComputerPlayer();

        board = new Character[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = players[0];
//        remainingBoardPositions = new int[]{1,2,3,4,5,6,7,8,9};
        remainingBoardPositions = new int[BOARD_SIZE * BOARD_SIZE];
        Arrays.setAll(remainingBoardPositions, i -> i+1);
    }

    public void startGame() {

    }

    private Player checkForWinner() {
        return null;
    }

    public Player nextTurn() {
        return null;
    }

    public void printBoard() {
        System.out.format("Welcome to Tic-Tac-Toe.  Please make your move selection by entering a number\n" +
                "1-9 corresponding to the movement option on the right.");

        System.out.printf("Board:                                                           Movement Options: ");
        System.out.printf("  |  |                                                            1 | 2 | 3 ");;
        System.out.printf("  |  |                                                            4 | 5 | 6 ");;
        System.out.printf("  |  |                                                            7 | 8 | 9 ");;

    }

}
