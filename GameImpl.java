/**
 * Classe que contém métodos que serão chamados para a execução do jogo
 */
public class GameImpl implements Game{
    protected final int BOARD_SIZE = 5;
    protected Player winner = null;
    private Player turn;
    private Spot[][] board;
    private Player redPlayer;
    private Player bluePlayer;
    private Card tableCard;
    private Card[] deck;

    /**
     * Construtor que inicia o jogo com as informações básicas
     */
    public GameImpl(){
        this.board = Spot.createBoard(this.BOARD_SIZE);
        this.deck = Card.createCards();

        this.redPlayer = new Player("Red", Color.RED, this.deck[0], this.deck[1]);
        this.bluePlayer = new Player("Blue", Color.BLUE, this.deck[2], this.deck[3]);

        this.tableCard = this.deck[4];
        this.turn = this.tableCard.getColor() == Color.RED ? this.redPlayer : this.bluePlayer;

        Player winner = this.play();

        System.out.println("O vencedor é: " + winner.getName());
    }

    /**
     * Método jogar que executa o jogo em si
     * @return void - não retorna nada
     */

    public Player play(){
        if(this.winner != null){
            return this.winner;
        }

        while(this.winner == null){
            Player other = this.turn == this.redPlayer ? this.bluePlayer : this.redPlayer;
            System.out.println("É a vez do jogador " + this.turn.getName());
            this.turn.printHand();
            printBoard();
            other.printHand();
            
            String spot = System.console().readLine("Digite o endereço da peça que deseja mover no formato linha,coluna:");

            String[] spotArray = spot.split(",");
            int row = Integer.parseInt(spotArray[0]);
            int col = Integer.parseInt(spotArray[1]);

            Piece piece = this.board[row][col].getPiece();

            String card = System.console().readLine("Digite o número da carta que deseja jogar:");

            int cardNumber = Integer.parseInt(card);
            Card cardPlayed = this.turn.getCards()[cardNumber - 1];

            String direction = System.console().readLine("Digite o número do movimento da carta:");
            int directionNumber = Integer.parseInt(direction);
            
            Position endPosition = cardPlayed.getMove(directionNumber);

            makeMove(piece, cardPlayed, endPosition);
        }
        Player winner = this.winner;
        this.winner = null;

        return winner;
    }

    /**
     * Método que devolve a cor da posição do tabuleiro. Se possui uma cor, significa que é um templo. Caso contrário, é um espaço normal
     * @param position Posição do tabuleiro
     * @return O enum Color que representa a cor da posição
     */
    public Color getSpotColor(Position position){
        return this.board[position.getRow()][position.getCol()].getColor();
    };

    /**
     * Método que devolve a peça que está na posição do tabuleiro
     * @param position Posição do tabuleiro
     * @return Um objeto Piece que representa a peça na posição indicada. Se não tiver peça, devolve null
     */
    public Piece getPiece(Position position){
        return this.board[position.getRow()][position.getCol()].getPiece();
    }

    /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     * @return Um objeto Card que representa a carta na mesa
     */
    public Card getTableCard(){
        return this.tableCard;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     * @return Um objeto Player que representa o jogador vermelho
     */
    public Player getRedPlayer(){
        return this.redPlayer;
    };

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     * @return Um objeto Player que representa o jogador azul
     */
    public Player getBluePlayer(){
        return this.bluePlayer;
    };

    public boolean hasPiece(Piece piece) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].getPiece() == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public Position getPiecePosition(Piece piece) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col].getPiece() == piece) {
                    return new Position(row, col);
                }
            }
        }
        return null; 
    }

    /**
     * Método que move uma peça
     * @param piece A peça que irá mover
     * @param card A carta de movimento que será usada
     * @param position A posição da carta para onde a peça irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    public void makeMove(Piece piece, Card card, Position position) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException{
        // Verifica se é a vez do jogador atual
        if(piece.getColor() != turn.getPieceColor()) throw new IncorrectTurnOrderException("Não é a sua vez.");

        // Verifica se a peça está no tabuleiro
        if(hasPiece(piece))  throw new InvalidPieceException("A peça não está no tabuleiro.");

        Position piecePosition = getPiecePosition(piece);

        if(!card.isReachable(piecePosition, position))
            throw new IllegalMovementException("Movimento inválido.");

        // Verifica se a carta usada está na mão do jogador
        boolean cardInHand = false;
        for (Card c : turn.getCards()) {
            if (c.getName().equals(card.getName())) {
                cardInHand = true;
                break;
            }
        }
        if(!cardInHand) throw new InvalidCardException("A carta não está na mão do jogador.");

        // // Verifica se o movimento é permitido
        // boolean validMove = false;
        // for (Position validPosition : card.getPositions()) {
        //     Position targetPosition = new Position(getPiecePosition(piece).getRow() + validPosition.getRow(), getPiecePosition(piece).getCol() + validPosition.getCol());
        //     if (targetPosition.equals(position)) {
        //         validMove = true;
        //         break;
        //     }
        // }
        // if(!validMove || !isValidPosition(position) || verifyDestinyPieceSameColor(piece, position)){
        //     throw new IllegalMovementException("Movimento ilegal");
        // }

        // Realiza o movimento da peça
        
        if (position.getRow() >= 0 && position.getRow() < BOARD_SIZE && position.getCol() >= 0 && position.getCol() < BOARD_SIZE) {
            board[position.getRow()][position.getCol()].setPiece(piece);
        }

        board[position.getRow()][position.getCol()].setPiece(piece);

        board[piecePosition.getRow()][piecePosition.getCol()].removePiece();

        // Verifica se há condição de vitória após o movimento
        if (checkVictory(piece.getColor())) {
            winner = piece.getColor() == Color.RED ? redPlayer : bluePlayer;
        }

        // Atualiza o turno do jogador
        turn = turn == redPlayer ? bluePlayer : redPlayer;

        return;
    };

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */
    public boolean checkVictory(Color color){

        return false;
    };

    /**
     * Método que imprime o tabuleiro no seu estado atual
     * OBS: Esse método é opcional não será utilizado na correção, mas serve para acompanhar os resultados parciais do jogo
     */
    public void printBoard(){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Spot spot = board[i][j];
                if(spot.getPiece() == null){
                    System.out.print("[  ]");
                }
                else if(spot.getPiece() != null && spot.getPiece().isMaster() == false){
                    if(spot.getPiece().getColor() == Color.BLUE) System.out.print("[BS]");
                    else System.out.print("[RS]");
                }
                else if(spot.getPiece() != null && spot.getPiece().isMaster() == true){
                    if(spot.getPiece().getColor() == Color.BLUE) System.out.print("[BM]");
                    else System.out.print("[RM]");
                }
                System.out.println();
            }
        }

        return;
    };
}
