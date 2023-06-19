import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém métodos que serão chamados para a execução do jogo
 */
public class GameImplTest {
    @Test
    public void testCreateGameImpl() {
        Card[] allDeck = new Card[8];

        allDeck[0] = new Card("Tiger", Color.BLUE, new Position[]{new Position(1, 0), new Position(-2, 0)});
        allDeck[1] = new Card("Crab", Color.BLUE, new Position[]{new Position(0, -2), new Position(-1, 0), new Position(0, 2)});
        allDeck[2] = new Card("Goose", Color.BLUE, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(0, 1), new Position(1, 1)});
        allDeck[3] = new Card("Rabbit", Color.BLUE, new Position[]{new Position(1, -1), new Position(-1, 1), new Position(0, 2)});
        allDeck[4] = new Card("Elephant", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)});
        allDeck[5] = new Card("Frog", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -2), new Position(1, 1)});
        allDeck[6] = new Card("Rooster", Color.RED, new Position[]{new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(-1, 1)});
        allDeck[7] = new Card("Dragon", Color.RED, new Position[]{new Position(1, -1), new Position(1, 1), new Position(-1, -2), new Position(-1, 2)});

        List<Card> allDeckList = new ArrayList<Card>();

        allDeckList.add(new Card("Tiger", Color.BLUE, new Position[]{new Position(1, 0), new Position(-2, 0)}));
        allDeckList.add(new Card("Crab", Color.BLUE, new Position[]{new Position(0, -2), new Position(-1, 0), new Position(0, 2)}));
        allDeckList.add(new Card("Goose", Color.BLUE, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(0, 1), new Position(1, 1)}));
        allDeckList.add(new Card("Rabbit", Color.BLUE, new Position[]{new Position(1, -1), new Position(-1, 1), new Position(0, 2)}));
        allDeckList.add(new Card("Elephant", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)}));
        allDeckList.add(new Card("Frog", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -2), new Position(1, 1)}));
        allDeckList.add(new Card("Rooster", Color.RED, new Position[]{new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(-1, 1)}));
        allDeckList.add(new Card("Dragon", Color.RED, new Position[]{new Position(1, -1), new Position(1, 1), new Position(-1, -2), new Position(-1, 2)}));


        GameImpl gameNoArg = new GameImpl();

        Assert.assertNotEquals(null, gameNoArg.redPlayer);
        Assert.assertNotEquals(null, gameNoArg.bluePlayer);

        Assert.assertEquals(5, gameNoArg.deck.length);

        Assert.assertNotEquals(null, gameNoArg.redMasterPiece);
        Assert.assertNotEquals(null, gameNoArg.blueMasterPiece);

        Assert.assertEquals(Color.BLUE, gameNoArg.blueMasterPiece.getColor());
        Assert.assertEquals(Color.RED, gameNoArg.redMasterPiece.getColor());

        Assert.assertEquals(true, gameNoArg.blueMasterPiece.isMaster());
        Assert.assertEquals(true, gameNoArg.redMasterPiece.isMaster());

        Assert.assertEquals(false, gameNoArg.blueMasterPiece.isDead());       
        Assert.assertEquals(false, gameNoArg.redMasterPiece.isDead());       

        GameImpl gameWNames = new GameImpl("RedPlayer", "BluePlayer");

        Assert.assertNotEquals(null, gameWNames.redPlayer);
        Assert.assertNotEquals(null, gameWNames.bluePlayer);

        Assert.assertEquals("RedPlayer", gameWNames.redPlayer.getName());
        Assert.assertEquals("BluePlayer", gameWNames.bluePlayer.getName());

        Assert.assertEquals(5, gameWNames.deck.length);

        Assert.assertNotEquals(null, gameWNames.redMasterPiece);
        Assert.assertNotEquals(null, gameWNames.blueMasterPiece);

        Assert.assertEquals(Color.BLUE, gameWNames.blueMasterPiece.getColor());
        Assert.assertEquals(Color.RED, gameWNames.redMasterPiece.getColor());

        Assert.assertEquals(true, gameWNames.blueMasterPiece.isMaster());
        Assert.assertEquals(true, gameWNames.redMasterPiece.isMaster());

        Assert.assertEquals(false, gameWNames.blueMasterPiece.isDead());       
        Assert.assertEquals(false, gameWNames.redMasterPiece.isDead());

        GameImpl gameWNamesWCards = new GameImpl("RedPlayer", "BluePlayer", allDeck);
        
        Assert.assertNotEquals(null, gameWNamesWCards.redPlayer);
        Assert.assertNotEquals(null, gameWNamesWCards.bluePlayer);

        Assert.assertEquals("RedPlayer", gameWNamesWCards.redPlayer.getName());
        Assert.assertEquals("BluePlayer", gameWNamesWCards.bluePlayer.getName());

        Assert.assertEquals(5, gameWNamesWCards.deck.length);

        Assert.assertNotEquals(null, gameWNamesWCards.redMasterPiece);
        Assert.assertNotEquals(null, gameWNamesWCards.blueMasterPiece);

        Assert.assertEquals(Color.BLUE, gameWNamesWCards.blueMasterPiece.getColor());
        Assert.assertEquals(Color.RED, gameWNamesWCards.redMasterPiece.getColor());

        Assert.assertEquals(true, gameWNamesWCards.blueMasterPiece.isMaster());
        Assert.assertEquals(true, gameWNamesWCards.redMasterPiece.isMaster());

        Assert.assertEquals(false, gameWNamesWCards.blueMasterPiece.isDead());       
        Assert.assertEquals(false, gameWNamesWCards.redMasterPiece.isDead());
        
        
        Card[] missingCardsDeck = allDeckList.subList(0, 4).toArray(new Card[4]);

        Assert.assertThrows(RuntimeException.class, () -> {
            new GameImpl("RedPlayer", "BluePlayer", missingCardsDeck);
        });
    }
}
