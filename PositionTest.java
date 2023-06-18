import org.junit.Assert;
import org.junit.Test;


/**
 * Classe usada para definição de estrutura de posições e movimentos do jogo
 */
public class PositionTest {
    @Test
    public void testCreatePosition() {
        Position position = new Position(1, 2);

        Assert.assertEquals(1, position.getRow());
        Assert.assertEquals(2, position.getCol());
    }
}
