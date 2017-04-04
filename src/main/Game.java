import java.util.*;

/**
 * Created by rgopal on 3/31/17.
 */
public class Game {

    private static final int BOARD_SIZE = 3;

    public static enum GAME_STATE {PLAYING, DRAW, WIN}

    private Player[] players;
    private Character[][] board;
    private GAME_STATE gameState;
    private Character winner;

    private Player currentPlayer;

    private List<Integer> remainingBoardPositions;          // choices for play

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

    protected void clearRemainingBoardPositions() {
        remainingBoardPositions.clear();
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

        gameState = GAME_STATE.PLAYING;
    }

    public void startGame() {
        System.out.format("Welcome to Tic-Tac-Toe.  Please make your move selection by entering a number\n" +
                "1-9 corresponding to the movement option on the right.");
        System.out.println();

        while ((gameState = checkGameState()) == GAME_STATE.PLAYING) {
            currentPlayer = nextTurn();

            if (currentPlayer.getPlayerType() == AbstractPlayer.Type.HUMAN) {
                printBoard();
                System.out.println("Choices are : " + remainingBoardPositions);

            }

            try {
                currentPlayer.playTurn(board, remainingBoardPositions);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        if (gameState == GAME_STATE.WIN) {
            if (currentPlayer.getPlayerType() == AbstractPlayer.Type.HUMAN)
                System.out.println(" You have beaten my AI!");
            else
                System.out.println(" I win !");

        } else {
            System.out.println(" It's a draw.");
        }

        printBoard();
    }

    protected GAME_STATE checkGameState() {

        // check row winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != null &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                winner = board[i][0];
                return GAME_STATE.WIN;
            }
        }

        // check column winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] != null &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                winner = board[0][i];
                return GAME_STATE.WIN;
            }
        }

        // check diagonal winning
        if (board[0][0] != null &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            winner = board[0][0];
            return GAME_STATE.WIN;
        }

        // check reverse diagonal winning
        if (board[0][2] != null &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            winner = board[0][2];
            return GAME_STATE.WIN;
        }

        // no more choices left
        if (remainingBoardPositions.size() == 0) {
            return GAME_STATE.DRAW;
        }

        return GAME_STATE.PLAYING;
    }

    public Player nextTurn() {
        currentPlayer = players[remainingBoardPositions.size() % 2];
        return currentPlayer;
    }

    public void printBoard() {
        System.out.println();
        System.out.println("Board:                                                           Movement Options: ");
        System.out.printf(" " + getPrintableBoardCellValue(0, 0) + " | " + getPrintableBoardCellValue(0, 1) + " | " + getPrintableBoardCellValue(0, 2) + "                                                            1 | 2 | 3 ");
        System.out.println();
        System.out.printf(" " + getPrintableBoardCellValue(1, 0) + " | " + getPrintableBoardCellValue(1, 1) + " | " + getPrintableBoardCellValue(1, 2) + "                                                            4 | 5 | 6 ");
        System.out.println();
        System.out.printf(" " + getPrintableBoardCellValue(2, 0) + " | " + getPrintableBoardCellValue(2, 1) + " | " + getPrintableBoardCellValue(2, 2) + "                                                            7 | 8 | 9 ");
        System.out.println();

    }

    private Character getPrintableBoardCellValue(int x, int y) {
        return (board[x][y] != null) ? board[x][y] : ' ';
    }

    public static String getPrintablePosition(int playPosition) {
        int x = getPositionX(playPosition);
        int y = getPositionY(playPosition);

        String position = "";

        position = (x == 0) ? "upper " : (x == 1) ? "center " : "lower ";
        if (x == 1 && x == y )
            position = "center";
        else
            position += (y == 0) ? "left" : (y == 1) ? "middle" : "right";

        return position;

    }

    public static int getPositionX(int playPosition) {
        return (playPosition -1 )/ BOARD_SIZE;
    }

    public static int getPositionY(int playPosition) {
        return (playPosition - 1) % BOARD_SIZE;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

    }

}
