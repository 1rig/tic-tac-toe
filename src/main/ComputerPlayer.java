import java.util.List;

/**
 * Created by rgopal on 3/31/17.
 */
public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer() {
        super();
        playerType = type.COMPUTER;
    }

    @Override
    public int play(List<Integer> remainingPositions) {

        double randomPosition = Math.random() * remainingPositions.size();

        return remainingPositions.get((int) randomPosition);
    }

}
