import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rgopal on 3/31/17.
 */
public class GameTest {

    @Test
    public void testInitBoard() {
        Game game = new Game();
        assertEquals(game.getPlayers().length, 2);
        assertEquals(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), game.getRemainingBoardPositions());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotPickSamePosition() {
        String input1 = "1",
                input2 = "1";
        Game game = new Game();
        Player player = new HumanPlayer();
        player.setInputStream(new ByteArrayInputStream(input1.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        player.setInputStream(new ByteArrayInputStream(input2.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoInputByPlayer() {
        String input = "";
        Game game = new Game();
        Player player = new HumanPlayer();
        player.setInputStream(new ByteArrayInputStream(input.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputByPlayer() {
        String input = "a";
        Game game = new Game();
        Player player = new HumanPlayer();
        player.setInputStream(new ByteArrayInputStream(input.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());
    }

    @Test
    public void testCorrectCellPopulatedFromInput() {

    }

    @Test
    public void testRowWinner() {

    }

    @Test
    public void testColumnWinner() {

    }

    @Test
    public void testDiagonalWinner() {

    }

    @Test
    public void testAlternateTurns() {
        String input = "1";
        Game game = new Game();
        Player player = game.nextTurn();
        assertEquals(AbstractPlayer.type.HUMAN, player.getPlayerType());

        player.setInputStream(new ByteArrayInputStream(input.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        Player nextPlayer = game.nextTurn();
        assertEquals(AbstractPlayer.type.COMPUTER, nextPlayer.getPlayerType());
    }

    @Test
    public void testRandomPicks() {
        ComputerPlayer player = new ComputerPlayer();

        List<Integer> positions = new ArrayList<>(Arrays.asList(new Integer[]{10,20,30,40,50,60,70,80,90,100}));
        assertTrue(positions.contains(player.play(positions)));
        assertTrue(positions.contains(player.play(positions)));
        assertTrue(positions.contains(player.play(positions)));
        assertTrue(positions.contains(player.play(positions)));
        assertTrue(positions.contains(player.play(positions)));

    }
    @Test
    public void testGameDraw() {

    }
}