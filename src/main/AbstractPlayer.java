/**
 * Created by rgopal on 4/1/17.
 */
public class AbstractPlayer implements Player{
    public boolean validateInput(int position)
    {
        return false;
    }

    public int play(Character[][] board) {
        return 0;
    }

    @Override
    public void playTurn(Character[][] board) {
        int playPosition = play(board);
        boolean valid = validateInput(playPosition);

        // translate input to board coordinates
        // update board


    }
}
