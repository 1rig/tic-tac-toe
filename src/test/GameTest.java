import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by rgopal on 3/31/17.
 */
public class GameTest {

    @Test
    public void initBoard() {
        Game game = new Game();
        assertEquals(game.getPlayers().length, 2);
        assertArrayEquals(new int[] {1,2,3,4,5,6,7,8,9}, game.getRemainingBoardPositions());

    }
}