import java.io.InputStream;
import java.util.List;

/**
 * Created by rgopal on 4/1/17.
 */
public abstract class AbstractPlayer implements Player{
    public static enum Type {COMPUTER, HUMAN}
    public static enum Symbol {
            X ('X'),
            O('O');

        public char getSymbol() {
            return symbol;
        }

        private char symbol;

        Symbol(char symbol) {
            this.symbol = symbol;
        }
    }

    protected Type playerType;
    protected Symbol playerSymbol;
    protected Integer currentPlayPosition;

    @Override
    public Integer getCurrentPlayPosition() {
        return currentPlayPosition;
    }

    private InputStream inputStream = System.in;

    @Override
    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    @Override
    public Type getPlayerType() {
        return playerType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean validateInput(int position)
    {
        return true;
    }

    public abstract int play(List<Integer> remainingPositions);

    @Override
    public void playTurn(Character[][] board, List<Integer> remainingPositions) throws IllegalArgumentException{
        int playPosition = play(remainingPositions);

        // remove object from remaining available positions
        boolean removed = remainingPositions.remove(Integer.valueOf(playPosition));
        if (!removed || playPosition == 0) {
            throw new IllegalArgumentException("Not a valid input. Please try again");
        }

        // translate input to board coordinates
        // update board
        int boardsize = board[0].length;
        int x = (playPosition -1 )/ boardsize;
        int y = (playPosition - 1) % boardsize;
        board[x][y] = getPlayerSymbol().getSymbol();
    }
}
