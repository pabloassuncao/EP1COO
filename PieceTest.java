import org.junit.Assert;
import org.junit.Test;



/**
 * Classe que contém informações das peças de jogo
 */
public class PieceTest {
    // Gere testes para a classe Piece

    @Test
    public void testCreatePiece() {
        Piece pieceMaster = new Piece(Color.BLUE, true);
        Piece pieceNotMaster = new Piece(Color.RED, false);

        Assert.assertEquals(Color.BLUE, pieceMaster.getColor());
        Assert.assertEquals(true, pieceMaster.isMaster());
        Assert.assertEquals(false, pieceMaster.isDead());

        Assert.assertEquals(Color.RED, pieceNotMaster.getColor());
        Assert.assertEquals(false, pieceNotMaster.isMaster());
        Assert.assertEquals(false, pieceNotMaster.isDead());
    }

    @Test
    public void testKillPiece() {
        Piece piece = new Piece(Color.BLUE, true);

        Assert.assertEquals(true, piece.kill());
        Assert.assertEquals(true, piece.isDead());
        Assert.assertEquals(false, piece.kill());
    }
}
