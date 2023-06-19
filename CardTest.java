import org.junit.Assert;
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
        
        Assert.assertEquals(5, cards.length);
        Assert.assertEquals(5, cards2.length);
        Assert.assertNotEquals(cards, cards2);
    }

    @Test
    public void testCreateOneCard() {
        Position[] cardPositions = new Position[]{new Position(1, 0), new Position(-2, 0)};
        
        cardPositions = Card.sortMoves(cardPositions);

        Card card = new Card("Tiger", Color.BLUE, cardPositions);

        Assert.assertEquals("Tiger", card.getName());
        Assert.assertEquals(Color.BLUE, card.getColor());

        Position position1Blue = new Position(-1, 0);
        Position position2Blue = new Position(2, 0);

        Position position1Red = new Position(-2, 0);
        Position position2Red = new Position(1, 0);

        Position cardMoveBlue1 = card.getMove(0, Color.BLUE);
        Position cardMoveBlue2 = card.getMove(1, Color.BLUE);

        Position cardMoveRed1 = card.getMove(0, Color.RED);
        Position cardMoveRed2 = card.getMove(1, Color.RED);

        Assert.assertTrue(position1Blue.getRow() == cardMoveBlue1.getRow());
        Assert.assertTrue(position1Blue.getCol() == cardMoveBlue1.getCol());

        Assert.assertTrue(position2Blue.getRow() == cardMoveBlue2.getRow());
        Assert.assertTrue(position2Blue.getCol() == cardMoveBlue2.getCol());

        Assert.assertTrue(position1Red.getRow() == cardMoveRed1.getRow());
        Assert.assertTrue(position1Red.getCol() == cardMoveRed1.getCol());

        Assert.assertTrue(position2Red.getRow() == cardMoveRed2.getRow());
        Assert.assertTrue(position2Red.getCol() == cardMoveRed2.getCol());

        Assert.assertEquals(false, card.hasMove(position1Red, Color.BLUE));
        Assert.assertEquals(false, card.hasMove(position2Red, Color.BLUE));

        Assert.assertEquals(false, card.hasMove(position1Blue, Color.RED));
        Assert.assertEquals(false, card.hasMove(position2Blue, Color.RED));

        Assert.assertEquals(true, card.hasMove(position1Blue, Color.BLUE));
        Assert.assertEquals(true, card.hasMove(position2Blue, Color.BLUE));

        Assert.assertEquals(true, card.hasMove(position1Red, Color.RED));
        Assert.assertEquals(true, card.hasMove(position2Red, Color.RED));

        Assert.assertEquals(false, card.hasMove(position1Blue, Color.RED));
        Assert.assertEquals(false, card.hasMove(position2Blue, Color.RED));
    }
}
