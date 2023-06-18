import static org.junit.Assert.*;
import org.junit.Test;

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


        GameImpl gameNoArg = new GameImpl();

        assertNotEquals(null, gameNoArg.redPlayer);
        assertNotEquals(null, gameNoArg.bluePlayer);

        assertEquals(5, gameNoArg.deck.length);

        assertNotEquals(null, gameNoArg.redMasterPiece);
        assertNotEquals(null, gameNoArg.blueMasterPiece);

        assertEquals(Color.BLUE, gameNoArg.blueMasterPiece.getColor());
        assertEquals(Color.RED, gameNoArg.redMasterPiece.getColor());

        assertEquals(true, gameNoArg.blueMasterPiece.isMaster());
        assertEquals(true, gameNoArg.redMasterPiece.isMaster());

        assertEquals(false, gameNoArg.blueMasterPiece.isDead());       
        assertEquals(false, gameNoArg.redMasterPiece.isDead());       

        GameImpl gameWNames = new GameImpl("RedPlayer", "BluePlayer");

        assertNotEquals(null, gameWNames.redPlayer);
        assertNotEquals(null, gameWNames.bluePlayer);

        assertEquals("RedPlayer", gameWNames.redPlayer.getName());
        assertEquals("BluePlayer", gameWNames.bluePlayer.getName());

        assertEquals(5, gameWNames.deck.length);

        assertNotEquals(null, gameWNames.redMasterPiece);
        assertNotEquals(null, gameWNames.blueMasterPiece);

        assertEquals(Color.BLUE, gameWNames.blueMasterPiece.getColor());
        assertEquals(Color.RED, gameWNames.redMasterPiece.getColor());

        assertEquals(true, gameWNames.blueMasterPiece.isMaster());
        assertEquals(true, gameWNames.redMasterPiece.isMaster());

        assertEquals(false, gameWNames.blueMasterPiece.isDead());       
        assertEquals(false, gameWNames.redMasterPiece.isDead());

        GameImpl gameWNamesWCards = new GameImpl("RedPlayer", "BluePlayer", allDeck);
        
        assertNotEquals(null, gameWNamesWCards.redPlayer);
        assertNotEquals(null, gameWNamesWCards.bluePlayer);

        assertEquals("RedPlayer", gameWNamesWCards.redPlayer.getName());
        assertEquals("BluePlayer", gameWNamesWCards.bluePlayer.getName());

        assertEquals(5, gameWNamesWCards.deck.length);

        assertNotEquals(null, gameWNamesWCards.redMasterPiece);
        assertNotEquals(null, gameWNamesWCards.blueMasterPiece);

        assertEquals(Color.BLUE, gameWNamesWCards.blueMasterPiece.getColor());
        assertEquals(Color.RED, gameWNamesWCards.redMasterPiece.getColor());

        assertEquals(true, gameWNamesWCards.blueMasterPiece.isMaster());
        assertEquals(true, gameWNamesWCards.redMasterPiece.isMaster());

        assertEquals(false, gameWNamesWCards.blueMasterPiece.isDead());       
        assertEquals(false, gameWNamesWCards.redMasterPiece.isDead());
    }
}
