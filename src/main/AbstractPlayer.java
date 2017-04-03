import java.io.InputStream;
import java.util.List;

/**
 * Created by rgopal on 4/1/17.
 */
public abstract class AbstractPlayer implements Player{
    public static enum type {COMPUTER, HUMAN}

    protected type playerType;
    private InputStream inputStream = System.in;

    public type getPlayerType() {
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

        // translate input to board coordinates
        // update board

        // remove object from remaining available positions
        boolean removed = remainingPositions.remove(Integer.valueOf(playPosition));
        if (!removed || playPosition == 0) {
            throw new IllegalArgumentException("Not a valid input. Please try again");
        }
    }
}
