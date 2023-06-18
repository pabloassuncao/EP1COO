import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Classe que contém informações das cartas
 */
public class CardTest {
    // Gere testes para a classe Card

    @Test
    public void testCreateCards() {
        Card[] cards = Card.createCards();
        Card[] cards2 = Card.createCards();
        
        assertEquals(5, cards.length);
        assertEquals(5, cards2.length);
        assertNotEquals(cards, cards2);
    }

    @Test
    public void testCreateOneCard() {
        Position[] cardPositions = new Position[]{new Position(1, 0), new Position(-2, 0)};
        
        cardPositions = Card.sortMoves(cardPositions);

        Card card = new Card("Tiger", Color.BLUE, cardPositions);

        assertEquals("Tiger", card.getName());
        assertEquals(Color.BLUE, card.getColor());

        Position position1Blue = new Position(-1, 0);
        Position position2Blue = new Position(2, 0);

        Position position1Red = new Position(-2, 0);
        Position position2Red = new Position(1, 0);

        Position cardMoveBlue1 = card.getMove(0, Color.BLUE);
        Position cardMoveBlue2 = card.getMove(1, Color.BLUE);

        Position cardMoveRed1 = card.getMove(0, Color.RED);
        Position cardMoveRed2 = card.getMove(1, Color.RED);

        assertTrue(position1Blue.getRow() == cardMoveBlue1.getRow());
        assertTrue(position1Blue.getCol() == cardMoveBlue1.getCol());

        assertTrue(position2Blue.getRow() == cardMoveBlue2.getRow());
        assertTrue(position2Blue.getCol() == cardMoveBlue2.getCol());

        assertTrue(position1Red.getRow() == cardMoveRed1.getRow());
        assertTrue(position1Red.getCol() == cardMoveRed1.getCol());

        assertTrue(position2Red.getRow() == cardMoveRed2.getRow());
        assertTrue(position2Red.getCol() == cardMoveRed2.getCol());

        assertEquals(false, card.hasMove(position1Red, Color.BLUE));
        assertEquals(false, card.hasMove(position2Red, Color.BLUE));

        assertEquals(false, card.hasMove(position1Blue, Color.RED));
        assertEquals(false, card.hasMove(position2Blue, Color.RED));

        assertEquals(true, card.hasMove(position1Blue, Color.BLUE));
        assertEquals(true, card.hasMove(position2Blue, Color.BLUE));

        assertEquals(true, card.hasMove(position1Red, Color.RED));
        assertEquals(true, card.hasMove(position2Red, Color.RED));

        assertEquals(false, card.hasMove(position1Blue, Color.RED));
        assertEquals(false, card.hasMove(position2Blue, Color.RED));
    }
}
