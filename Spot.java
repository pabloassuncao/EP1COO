/**
 * Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
 */
public class Spot {
    private final Position position;
    private Piece piece;
    private Color color;

    /**
     * Construtor para espaços com peça e com cor
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos Posição do espaço no tabuleiro
     * @param color Cor do espaço no tabuleiro (Templo)
     */

    
    public Spot(Piece piece, Position pos, Color color) {
        this(piece, pos);
        this.color = color;
    }

    /**
     * Construtor para espaços com peça e sem cor
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos Posição do espaço no tabuleiro
     */
    public Spot(Piece piece, Position pos) {
        this.piece = piece;
        this.position = pos;
        this.color = Color.NONE;
    }

    /**
     * Construtor para espaços sem peça e sem cor
     * @param pos Posição do espaço no tabuleiro
     */
    public Spot(Position pos) {
        this.position = pos;
        this.color = Color.NONE;
        this.piece = null;
    }

    /**
     * Método que cria um Tabuleiro com os espaços e peças iniciais
     * @param size Tamanho do tabuleiro
     */
    public static Spot[][] createBoard(int size){
        Spot[][] board = new Spot[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if(i == 0){
                    if(j == size/2)
                        board[i][j] = new Spot(new Piece(Color.BLUE, true), new Position(i, j), Color.BLUE);
                    else
                        board[i][j] = new Spot(new Piece(Color.BLUE, false), new Position(i, j), Color.BLUE);
                }
                if(i == size-1){
                    if(j == size/2) 
                        board[i][j] = new Spot(new Piece(Color.RED, true), new Position(i, j), Color.RED);
                    else
                        board[i][j] = new Spot(new Piece(Color.RED, false), new Position(i, j), Color.RED);
                } 
                else board[i][j] = new Spot(new Position(i, j));
            }
        }
        return board;
    }

    /**
     * Método que devolve a posição (coordenadas) do espaço
     * @return Objeto Position contendo a posição (coordenadas) do espaço
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Método que devolve a peça contida neste espaço
     * @return Objeto Piece caso tenha uma peça ou null caso o espaço esteja vazio
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Método que devolve a cor do espaço
     * @return Enum Color com a cor do espaço. Caso o espaço não tenha cor, o valor do enum será NONE
     */
    Color getColor() {
        return this.color;
    }

    /**
     * Método que verifica se a posição é válida no tabuleiro
     * @return Um booleano que indica se o objeto representa uma posição válida no tabuleiro
     */
    public boolean isValid() {
        return false;
        //TODO: Implementar, incompreensível
    }

    /**
     * Método que ocupa o espaço atual com a peça passada
     * @param piece A peça para ocupar este espaço
     * @exception IllegalMovementException Caso o espaço já esteja ocupado por uma peça da mesma cor
     */
    protected void occupySpot(Piece piece) throws IllegalMovementException {
        //TODO: Implementar
    }

    /**
     * Método que "libera" o espaço atual, ou seja, deixa-o vazio
     */
    protected void releaseSpot() {
        this.piece = null;
        this.color = Color.NONE;
    }
}
