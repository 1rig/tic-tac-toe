import java.util.*;

/**
 * Created by rgopal on 3/31/17.
 */
public class Game {

    private final int BOARD_SIZE = 3;

    private Player[] players;
    private Character[][] board;

    private Player currentPlayer;

    private List<Integer> remainingBoardPositions;

    public Player[] getPlayers() {
        return players;
    }

    public Character[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Integer> getRemainingBoardPositions() {
        return remainingBoardPositions;
    }


    /**
     * Initialize game with players and board size
     */
    public Game() {
        players = new Player[2];
        players[0] = new ComputerPlayer();
        players[1] = new HumanPlayer();

        board = new Character[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = players[0];

//        remainingBoardPositions = new int[]{1,2,3,4,5,6,7,8,9};
        Integer[] positions = new Integer[BOARD_SIZE * BOARD_SIZE];
        Arrays.setAll(positions, i -> i + 1);
        remainingBoardPositions = new ArrayList<>();
        remainingBoardPositions.addAll(Arrays.asList(positions));
    }

    public void startGame() {
        printBoard();
        while (!gameOver()) {
            nextTurn().playTurn(board, remainingBoardPositions);
        }
    }

    private boolean gameOver() {
        return false;
    }

    public Player nextTurn() {
        System.out.println(remainingBoardPositions);

        return players[remainingBoardPositions.size() % 2];
    }

    public void printBoard() {
        System.out.format("Welcome to Tic-Tac-Toe.  Please make your move selection by entering a number\n" +
                "1-9 corresponding to the movement option on the right.");

        System.out.printf("Board:                                                           Movement Options: ");
        System.out.printf("  |  |                                                            1 | 2 | 3 ");
        System.out.printf("  |  |                                                            4 | 5 | 6 ");
        System.out.printf("  |  |                                                            7 | 8 | 9 ");

    }

}
