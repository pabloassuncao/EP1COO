import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que contém informações das cartas
 */
public class Card {
    protected static final int BOARD_SIZE = 5;
    private final String name;
    private final Color color;
    private final Position[] positions;

    /**
     * Construtor que define os principais atributos de uma cara
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    public Card(String name, Color color, Position[] positions) {
        this.name = name;
        this.color = color;
        this.positions = positions;
    }

    /**
     * Método que devolve o movimento do index passado como parâmetro
     * @param index Index do movimento
     * @return String que contém o nome da carta
     */
    public Position getMove(int index) {
        return new Position(this.positions[index].getRow()+BOARD_SIZE/2, this.positions[index].getCol()+BOARD_SIZE/2);
    }

    /**
     * Método que devolve o nome da carta
     * @return String que contém o nome da carta
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método que devolve a cor da carta
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento.
     * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao ponto de origem.
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao ponto de origem
     */
    public Position[] getPositions() {
        return this.positions;
    }

    /**
     * Método que verifica se existe determinada posição de movimento em relação ao ponto de origem
     * @param cardMove Posição realtiva de movimento para validação
     * @return
     */
    public boolean hasMove(Position cardMove) {
        for (Position p : this.positions) {
            if (p.getRow() == cardMove.getRow() && p.getCol() == cardMove.getCol())
                return true;
        }
        return false;
    }

    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na partida.
     * @return Vetor de cartas com todas as cartas do jogo
     */
    public static Card[] createCards() {
        List<Card> allDeck = new ArrayList<Card>();

        allDeck.add(new Card("Tiger", Color.BLUE, new Position[]{new Position(1, 0), new Position(-2, 0)}));
        allDeck.add(new Card("Crab", Color.BLUE, new Position[]{new Position(0, -2), new Position(-1, 0), new Position(0, 2)}));
        allDeck.add(new Card("Goose", Color.BLUE, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(0, 1), new Position(1, 1)}));
        allDeck.add(new Card("Rabbit", Color.BLUE, new Position[]{new Position(1, -1), new Position(-1, 1), new Position(0, 2)}));
        allDeck.add(new Card("Elephant", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)}));
        allDeck.add(new Card("Frog", Color.RED, new Position[]{new Position(-1, -1), new Position(0, -2), new Position(1, 1)}));
        allDeck.add(new Card("Rooster", Color.RED, new Position[]{new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(-1, 1)}));
        allDeck.add(new Card("Dragon", Color.RED, new Position[]{new Position(1, -1), new Position(1, 1), new Position(-1, -2), new Position(-1, 2)}));

		Collections.shuffle(allDeck);

        Card[] playDeck = allDeck.subList(0, 5).toArray(new Card[5]);
        
        return playDeck;
    }
    
    /**
     * Método para printar uma carta e seus possíveis movimentos
     */
    public void printCard(int i) {
        System.out.println("A carta" + i + " é " + this.name + " e possui as seguintes posições de movimento:");

        List <Position> positions = new ArrayList<Position>();
        for (Position position : this.positions) {
            positions.add(position);
        }

        Position temp;

        for(int j = 0; j < BOARD_SIZE; j++) {
            for(int k = 0; k < BOARD_SIZE; k++) {
                temp = new Position(j-BOARD_SIZE/2, k-BOARD_SIZE/2);
                if (positions.contains(temp)) {
                    for( int z = 0; z < this.positions.length; z++) {
                        System.out.println("[0"+ z +"]");
                    }
                } else {
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
    }
}
