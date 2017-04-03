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
        String input = "1";
        String input1 = "2";
        String input2 = "4";
        String input3 = "7";
        String input4 = "9";

        Game game = new Game();
        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream(input.getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
//        assertEquals(AbstractPlayer.Symbol.X.getSymbol(), game.getBoard()[0][0].charValue());
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, null}, game.getBoard()[0]);
        assertArrayEquals(new Character[]{null, null, null}, game.getBoard()[1]);
        assertArrayEquals(new Character[]{null, null, null}, game.getBoard()[2]);

        humanPlayer.setInputStream(new ByteArrayInputStream(input1.getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), AbstractPlayer.Symbol.X.getSymbol(), null}, game.getBoard()[0]);
        assertArrayEquals(new Character[]{null, null, null}, game.getBoard()[1]);
        assertArrayEquals(new Character[]{null, null, null}, game.getBoard()[2]);

        humanPlayer.setInputStream(new ByteArrayInputStream(input2.getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), AbstractPlayer.Symbol.X.getSymbol(), null}, game.getBoard()[0]);
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, null}, game.getBoard()[1]);
        assertArrayEquals(new Character[]{null, null, null}, game.getBoard()[2]);

        humanPlayer.setInputStream(new ByteArrayInputStream(input3.getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), AbstractPlayer.Symbol.X.getSymbol(), null}, game.getBoard()[0]);
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, null}, game.getBoard()[1]);
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, null}, game.getBoard()[2]);

        humanPlayer.setInputStream(new ByteArrayInputStream(input4.getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), AbstractPlayer.Symbol.X.getSymbol(), null}, game.getBoard()[0]);
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, null}, game.getBoard()[1]);
        assertArrayEquals(new Character[]{AbstractPlayer.Symbol.X.getSymbol(), null, AbstractPlayer.Symbol.X.getSymbol()}, game.getBoard()[2]);

    }

    @Test
    public void testRowWinner() {
        Game game = new Game();

        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream("1".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("2".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("3".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        assertEquals(AbstractPlayer.Symbol.X.getSymbol(), game.isGameOver().charValue());
    }

    @Test
    public void testInvalidRowWinner() {
        Game game = new Game();

        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream("1".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("3".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("4".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        assertNull(game.isGameOver());
    }

    @Test
    public void testColumnWinner() {
        Game game = new Game();

        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream("1".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("4".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("7".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        assertEquals(AbstractPlayer.Symbol.X.getSymbol(), game.isGameOver().charValue());

    }

    @Test
    public void testDiagonalWinner() {

        Game game = new Game();

        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream("1".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("5".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("9".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        assertEquals(AbstractPlayer.Symbol.X.getSymbol(), game.isGameOver().charValue());

    }

    @Test
    public void testReverseDiagonalWinner() {

        Game game = new Game();

        Player humanPlayer = new HumanPlayer();

        humanPlayer.setInputStream(new ByteArrayInputStream("3".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("5".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());
        humanPlayer.setInputStream(new ByteArrayInputStream("7".getBytes()));
        humanPlayer.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        assertEquals(AbstractPlayer.Symbol.X.getSymbol(), game.isGameOver().charValue());

    }

    @Test
    public void testAlternateTurns() {
        String input = "1";
        Game game = new Game();
        Player player = game.nextTurn();
        assertEquals(AbstractPlayer.Type.HUMAN, player.getPlayerType());

        player.setInputStream(new ByteArrayInputStream(input.getBytes()));
        player.playTurn(game.getBoard(), game.getRemainingBoardPositions());

        Player nextPlayer = game.nextTurn();
        assertEquals(AbstractPlayer.Type.COMPUTER, nextPlayer.getPlayerType());
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