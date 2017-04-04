import java.util.List;

/**
 * Created by rgopal on 3/31/17.
 */
public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer() {
        super();
        playerType = Type.COMPUTER;
        playerSymbol = Symbol.O;
    }

    @Override
    public int play(List<Integer> remainingPositions) {

        double randomPosition = Math.random() * remainingPositions.size();
        currentPlayPosition = remainingPositions.get((int) randomPosition);

        System.out.print("I will put an " + getPlayerSymbol() + " in the " + Game.getPrintablePosition(currentPlayPosition) + ".");

        return currentPlayPosition;
    }

}
