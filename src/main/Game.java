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
        currentPlayer = players[1];

//        remainingBoardPositions = new int[]{1,2,3,4,5,6,7,8,9};
        Integer[] positions = new Integer[BOARD_SIZE * BOARD_SIZE];
        Arrays.setAll(positions, i -> i + 1);
        remainingBoardPositions = new ArrayList<>();
        remainingBoardPositions.addAll(Arrays.asList(positions));
    }

    public void startGame() {
        printBoard();

        Character winnerSymbol;
        while ((winnerSymbol = isGameOver()) != null || remainingBoardPositions.size() > 0) {
            nextTurn().playTurn(board, remainingBoardPositions);
        }
    }

    /**
     * @return: Winning symbol if game is over
     *          Null if game is
     */
    protected Character isGameOver() {

        // check row winning
        for (int i= 0;  i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // check column winning
        for (int i= 0;  i < BOARD_SIZE; i++) {
            if (board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // check diagonal winning
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }

        // check reverse diagonal winning
        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }


        return null;
    }

    public Player nextTurn() {
        System.out.println(remainingBoardPositions);
        currentPlayer = players[remainingBoardPositions.size() % 2];
        return currentPlayer;
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
