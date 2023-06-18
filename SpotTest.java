import org.junit.Assert;
import org.junit.Test;


/**
 * Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
 */
public class SpotTest {
    // Gere testes para a classe Spot

    @Test
    public void testCreateBoard(){
        int size = 5;
        int center = size/2;

        Spot[][] board = Spot.createBoard(size);


        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if(i == 0){
                    if(j == center){
                        Assert.assertEquals(Color.BLUE, board[i][j].getColor());
                        Assert.assertNotEquals(null, board[i][j].getPiece());
                        Assert.assertEquals(true, board[i][j].getPiece().isMaster());
                        Assert.assertEquals(false, board[i][j].getPiece().isDead());
                    }else{
                        Assert.assertEquals(Color.NONE, board[i][j].getColor());
                        Assert.assertNotEquals(null, board[i][j].getPiece());
                        Assert.assertEquals(false, board[i][j].getPiece().isMaster());
                        Assert.assertEquals(false, board[i][j].getPiece().isDead());
                    } 
                } else if(i == size-1){
                    if(j == center){
                        Assert.assertEquals(Color.RED, board[i][j].getColor());
                        Assert.assertNotEquals(null, board[i][j].getPiece());
                        Assert.assertEquals(true, board[i][j].getPiece().isMaster());
                        Assert.assertEquals(false, board[i][j].getPiece().isDead());
                    }else{
                        Assert.assertEquals(Color.NONE, board[i][j].getColor());
                        Assert.assertNotEquals(null, board[i][j].getPiece());
                        Assert.assertEquals(false, board[i][j].getPiece().isMaster());
                        Assert.assertEquals(false, board[i][j].getPiece().isDead());
                    }
                } else {
                    Assert.assertEquals(Color.NONE, board[i][j].getColor());
                    Assert.assertEquals(null, board[i][j].getPiece());
                }
            }
        }
    }

    @Test
    public void testCreateSpot() {
        Piece pieceMB = new Piece(Color.BLUE, true);
        Piece pieceRNM = new Piece(Color.RED, false);

        Position positionMB = new Position(0, 2);
        Position positionRNM = new Position(4, 3);
        Position center = new Position(2, 2);

        Spot spotPPC = new Spot(pieceMB, positionMB, Color.BLUE);
        Spot spotPP = new Spot(pieceRNM, positionRNM);
        Spot spotEmpty = new Spot(center);

        Assert.assertEquals(spotPPC.getPiece(), pieceMB);
        Assert.assertEquals(spotPPC.getColor(), Color.BLUE);
        Assert.assertEquals(spotPPC.getPosition(), positionMB);

        Assert.assertEquals(spotPP.getPiece(), pieceRNM);
        Assert.assertEquals(spotPP.getColor(), Color.NONE);
        Assert.assertEquals(spotPP.getPosition(), positionRNM);

        Assert.assertEquals(spotEmpty.getPiece(), null);
        Assert.assertEquals(spotEmpty.getColor(), Color.NONE);
        Assert.assertEquals(spotEmpty.getPosition(), center);
    }

    @Test
    public void testSetPiece() {
        Piece pieceMB = new Piece(Color.BLUE, true);
        Piece pieceBNM = new Piece(Color.BLUE, false);
        Piece pieceRNM = new Piece(Color.RED, false);
        Piece pieceRM = new Piece(Color.RED, true);

        Position positionMB = new Position(0, 2);
        Position positionRNM = new Position(4, 3);
        Position center = new Position(2, 2);

        Spot spotBP = new Spot(positionMB);
        Spot spotRP = new Spot(positionRNM);

        spotBP.setPiece(pieceMB);
        spotRP.setPiece(pieceRNM);

        Assert.assertEquals(spotBP.getPiece(), pieceMB);
        Assert.assertEquals(spotRP.getPiece(), pieceRNM);

        Assert.assertThrows(IllegalMovementException.class, () -> {
            spotBP.setPiece(pieceBNM);
        });
        Assert.assertThrows(IllegalMovementException.class, () -> {
            spotRP.setPiece(pieceRM);
        });

        spotBP.setPiece(pieceRM);
        spotRP.setPiece(pieceBNM);

        Assert.assertEquals(spotBP.getPiece(), pieceRM);
        Assert.assertEquals(spotRP.getPiece(), pieceBNM);

        Assert.assertEquals(true, pieceMB.isDead());
        Assert.assertEquals(true, pieceRNM.isDead());

        spotBP.releaseSpot();
        spotRP.releaseSpot();

        Assert.assertEquals(null, spotBP.getPiece());
        Assert.assertEquals(null, spotRP.getPiece());
    }
}
