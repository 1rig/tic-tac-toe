import java.io.InputStream;
import java.util.List;

/**
 * Created by rgopal on 3/31/17.
 */
public interface Player {

    public void playTurn(Character[][] board, List<Integer> remainingPositions);
    public AbstractPlayer.type getPlayerType();

    public void setInputStream(InputStream inputStream);

}
