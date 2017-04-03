import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by rgopal on 3/31/17.
 */
public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer() {
        super();
        playerType = type.HUMAN;
    }

    @Override
    public int play(List<Integer> remainingPositions) throws IllegalArgumentException{

        Scanner scanner = new Scanner(getInputStream());

        int position = 0;
        System.out.println("Where to?");

        try {
            if (scanner.hasNext()) {
                position = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Invalid input. Please try again");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Invalid input. Please try again");
        }

        System.out.println("You played on position " + position);
        return position;
    }

}
