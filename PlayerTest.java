import org.junit.Assert;
import org.junit.Test;

/**
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class PlayerTest {
    // Gere testes para a classe Player

    @Test
    public void testCreatePlayer() {
        Card card1 = new Card("Tiger", Color.BLUE, new Position[]{new Position(1, 0), new Position(-2, 0)});
        Card card2 = new Card("Crab", Color.BLUE, new Position[]{new Position(0, -2), new Position(-1, 0), new Position(0, 2)});
        Card[] cards = new Card[]{card1, card2};

        Player playerCardArray = new Player("Player 1", Color.BLUE, cards);
        Player playerCardUnit = new Player("Player 2", Color.RED, card1, card2);

        Assert.assertEquals("Player 1", playerCardArray.getName());
        Assert.assertEquals(Color.BLUE, playerCardArray.getPieceColor());
        Assert.assertArrayEquals(cards, playerCardArray.getCards());

        Assert.assertEquals("Player 2", playerCardUnit.getName());
        Assert.assertEquals(Color.RED, playerCardUnit.getPieceColor());
        Assert.assertArrayEquals(cards, playerCardUnit.getCards());
    }

    @Test
    public void testSwapCards() {
        Card card1 = new Card("Tiger", Color.BLUE, new Position[]{new Position(1, 0), new Position(-2, 0)});
        Card card2 = new Card("Crab", Color.BLUE, new Position[]{new Position(0, -2), new Position(-1, 0), new Position(0, 2)});
        Card[] cards = new Card[]{card1, card2};

        Card cardSwap = new Card("Dragon", Color.RED, new Position[]{new Position(1, -1), new Position(1, 1), new Position(-1, -2), new Position(-1, 2)});
        Card otherCardSwap = new Card("Elephant", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)});

        Player playerCardArray = new Player("Player 1", Color.BLUE, cards);
        Player playerCardUnit = new Player("Player 2", Color.RED, card1, card2);

        // Teste de troca de cartas de um jogador, valida se da Throw corretamete
        Assert.assertThrows(InvalidCardException.class, () -> {
            playerCardArray.swapCard(card1, card2);
        });
        Assert.assertThrows(InvalidCardException.class, () -> {
            playerCardArray.swapCard(cardSwap, otherCardSwap);
        });

        Assert.assertThrows(InvalidCardException.class, () -> {
            playerCardUnit.swapCard(card1, card2);
        });
        Assert.assertThrows(InvalidCardException.class, () -> {
            playerCardUnit.swapCard(cardSwap, otherCardSwap);
        });

        try {
            playerCardArray.swapCard(card1, cardSwap);
            playerCardArray.swapCard(card2, otherCardSwap);
            playerCardUnit.swapCard(card1, cardSwap);
            playerCardUnit.swapCard(card2, otherCardSwap);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("A função não deve lançar nenhuma exceção");
        }
    }
}
