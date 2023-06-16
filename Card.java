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
    private Position[] positions;

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
        this.sortMoves();
    }

    private void sortMoves() {
        List<Position> moves = new ArrayList<Position>();

        List<Position> posList = new ArrayList<Position>();

        for (Position pos : this.positions) {
            posList.add(pos);
        }

        int moveCount = 0;
        Position temp;
        int center = BOARD_SIZE / 2;

        // for(int j = 0; j < BOARD_SIZE; j++) {
        //     for(int k = 0; k < BOARD_SIZE; k++) {
        //         temp = new Position(j-BOARD_SIZE/2, k-BOARD_SIZE/2);
        //         if (posList.contains(temp)) {
        //             moves.add(temp);
        //             moveCount++;
        //         }
        //     }
        // }

        for (int j = 0; j < BOARD_SIZE; j++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                temp = new Position(j - center, k - center);
                boolean isMove = false;

                for (Position pos : positions) {
                    if (pos.getRow() == temp.getRow() && pos.getCol() == temp.getCol()) {
                        isMove = true;
                        break;
                    }
                }

                if (isMove) {
                    moves.add(temp);
                    moveCount++;
                }
            }
        }

        if (moveCount != positions.length)
            throw new RuntimeException("Erro ao criar cartas: número de movimentos inválido");

        this.positions = moves.toArray(new Position[moves.size()]);
    }

    /**
     * Método que devolve o movimento do index passado como parâmetro
     * @param index Index do movimento
     * @return String que contém o nome da carta
     */
    public Position getMove(int baseIndex, Color color) {
        int index = baseIndex;
        int multi = 1;

        // para selecionar a jogada correta para cada cor
        if (color == Color.BLUE) {
            index = this.positions.length - 1 - baseIndex;
            multi = -1;
        }

        return new Position(this.positions[index].getRow() * multi, this.positions[index].getCol() * multi);
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
    public boolean hasMove(Position cardMove, Color color) {
        // para inverter a visão da carta para o jogador azul
        int multi = color == Color.BLUE ? -1 : 1;

        for (Position p : this.positions) {
            if (p.getRow()*multi == cardMove.getRow() && p.getCol()*multi == cardMove.getCol())
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
    public void printCard(Color color) {
        // para inverter a visão da carta para o jogador azul
        int posConstMulti = color == Color.BLUE ? -1 : 1;
        // TODO: Melhorar o fix
        List <Position> posList = new ArrayList<Position>();
        for (Position posArray : this.positions) {
            posList.add(new Position(posArray.getRow()*posConstMulti, posArray.getCol()*posConstMulti));
        }

        Position temp;

        int moveIndex = 1;
        int center = BOARD_SIZE/2;

        // for(int j = 0; j < BOARD_SIZE; j++) {
        //     for(int k = 0; k < BOARD_SIZE; k++) {
        //         temp = new Position(j-center, k-center);
        //         if (posList.contains(temp)) {
        //             System.out.print("[0"+ moveIndex +"]");
        //             moveIndex++;
        //         } else if(k == center && j == center) {
        //             System.out.print("[XX]");
        //         } else {
        //             System.out.print("[  ]");
        //         }
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        for (int j = 0; j < BOARD_SIZE; j++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                temp = new Position(j - center, k - center);
                boolean isMove = false;

                for (Position pos : posList) {
                    if (pos.getRow() == temp.getRow() && pos.getCol() == temp.getCol()) {
                        isMove = true;
                        break;
                    }
                }

                if (isMove) {
                    System.out.print("[0" + moveIndex + "]");
                    moveIndex++;
                } else if (k == center && j == center) {
                    System.out.print("[XX]");
                } else {
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
