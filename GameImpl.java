import java.util.*;

//TODO: corrigir o checkVictory
//TODO: Investigar pq só inicia pelo blue

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
    private Piece redMasterPiece;
    private Piece blueMasterPiece;
    private Card tableCard;
    private Card[] deck;

    /**
     * Construtor que inicia o jogo com as informações básicas
     */
    public GameImpl(){
        this.board = Spot.createBoard(this.BOARD_SIZE);
        this.deck = Card.createCards();

        redMasterPiece = board[0][2].getPiece();
        blueMasterPiece = board[4][2].getPiece();

        this.redPlayer = new Player("Red", Color.RED, this.deck[0], this.deck[1]);
        this.bluePlayer = new Player("Blue", Color.BLUE, this.deck[2], this.deck[3]);

        this.tableCard = this.deck[4];
        this.turn = this.tableCard.getColor() == Color.RED ? this.redPlayer : this.bluePlayer;
    }

    public GameImpl(String redName, String blueName){
        this.board = Spot.createBoard(this.BOARD_SIZE);
        this.deck = Card.createCards();

        redMasterPiece = board[0][2].getPiece();
        blueMasterPiece = board[4][2].getPiece();

        this.redPlayer = new Player(redName, Color.RED, this.deck[0], this.deck[1]);
        this.bluePlayer = new Player(blueName, Color.BLUE, this.deck[2], this.deck[3]);

        this.tableCard = this.deck[4];
        this.turn = this.tableCard.getColor() == Color.RED ? this.redPlayer : this.bluePlayer;
    }

    public GameImpl(String redName, String blueName, Card[] cards){
        this.board = Spot.createBoard(this.BOARD_SIZE);

        redMasterPiece = board[0][2].getPiece();
        blueMasterPiece = board[4][2].getPiece();

        ArrayList<Card> cardsList = new ArrayList<Card>(Arrays.asList(cards));

        if (cardsList.contains(null)) {
            throw new IllegalArgumentException("Cards cannot be null");
        }

        Collections.shuffle(cardsList);

        this.deck = cardsList.subList(0, 5).toArray(new Card[5]);

        this.redPlayer = new Player(redName, Color.RED, this.deck[0], this.deck[1]);
        this.bluePlayer = new Player(blueName, Color.BLUE, this.deck[2], this.deck[3]);

        this.tableCard = this.deck[4];

        this.turn = this.tableCard.getColor() == Color.RED ? this.redPlayer : this.bluePlayer;
    }

    /**
     * Limpa a tela do terminal entre os turnos
     * https://stackoverflow.com/questions/2979383/java-clear-the-console
     */
    private static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

    /**
     * Método para jogar o jogo
     */
    public void play(){
        while(this.winner == null){
            this.playTurn();
        }
        System.out.println("O vencedor é o jogador " + this.winner.getName());
    }

    /**
     * Método que executa um turno de jogo
     */
    public void playTurn(){
        Scanner scanner = new Scanner(System.in);
        Player opponent = this.turn == this.redPlayer ? this.bluePlayer : this.redPlayer;

        System.out.println("É a vez do jogador " + this.turn.getName() + " de cor " + this.turn.getPieceColor() + " jogar!\n");

        System.out.println("A mão do jogador " + this.turn.getName() + " é:\n");
        this.turn.printHand();
        System.out.println();

        System.out.println("A carta da mesa é " + tableCard.getName() + " e possui as seguintes posições de movimento:");
        this.tableCard.printCard();
        System.out.println();

        System.out.println("O tabuleiro é:");
        this.printBoard();
        System.out.println();

        System.out.println("As cartas do seu oponente são:");
        opponent.printHand();
        System.out.println();

        System.out.println("Escolha uma peça para mover no formato 'linha coluna':");
        int pieceRow = scanner.nextInt();
        int pieceCol = scanner.nextInt();

        System.out.println("Escolha uma carta pelo número (1 ou 2) para jogar:");
        int cardIndex = scanner.nextInt() - 1;

        System.out.println("Escolha uma dos movimentos da carta digitando seu número:");
        int cardMoveIndex = scanner.nextInt() - 1;

        Card card = this.turn.getCards()[cardIndex];

        Position cardMove = card.getPositions()[cardMoveIndex];

        Position currentPos = new Position(pieceRow, pieceCol);

        System.out.println("Você escolheu mover a peça " + "["+ pieceRow + "," + pieceCol +"]" + " para a posição " + "["+ (cardMove.getRow()+pieceRow) + "," + (cardMove.getCol()+pieceCol) +"]" + " com a carta " + card.getName() + "\n");

        scanner.close();

        try{
            this.makeMove(card, cardMove, currentPos);
        } catch (IncorrectTurnOrderException e){
            System.out.println(e.getMessage());
            return;
        } catch (IllegalMovementException e){
            System.out.println(e.getMessage());
            return;
        } catch (InvalidCardException e){
            System.out.println(e.getMessage());
            return;
        } catch (InvalidPieceException e){
            System.out.println(e.getMessage());
            return;
        }

        this.turn.swapCard(card, this.tableCard);

        this.tableCard = card;

        if(checkVictory(this.turn.getPieceColor())){
            winner = this.turn;
            return;
        }

        this.turn = opponent;
        
        System.out.println("\n\n=================================================================================================================================================\n\n");

        return;
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

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */
    public boolean checkVictory(Color color){
        Spot templeToCheck = color == Color.RED ? board[0][2] : board[4][2];

        Piece masterToCheck = color == Color.RED ? blueMasterPiece : redMasterPiece;

        if(masterToCheck.isDead()){
            System.out.println("O mestre do jogador " + color + " está morto!");
            return true;
        }

        if(templeToCheck.getPiece() != null 
            && templeToCheck.getPiece().getColor() == color 
            && templeToCheck.getPiece().isMaster()
        ) return true;

        return false;
    };


    /**
     * Método que move uma peça
     * @param card A carta de movimento que será usada
     * @param currentPos A posição de origem da peça
     * @param cardMove A posição da carta para onde a peça irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException{
        int curRow = currentPos.getRow();
        int curCol = currentPos.getCol();

        Spot curSpot = board[curRow][curCol];

        int cardRow = cardMove.getRow();
        int cardCol = cardMove.getCol();

        int endRow = curRow + cardRow;
        int endCol = curCol + cardCol;

        Piece curPiece = curSpot.getPiece();

        // Verifica se existe uma peça na posição atual
        if(curPiece == null)
            throw new InvalidPieceException("A peça não está no tabuleiro.");

        // Verifica se é a vez do jogador
        if(curPiece.getColor() != turn.getPieceColor())
            throw new IncorrectTurnOrderException("Não é a sua vez.");


        if(!card.hasMove(cardMove))
            throw new IllegalMovementException("Movimento não permitido pela carta.");

        if(endRow < 0 || endRow  >= BOARD_SIZE || endCol < 0 || endCol >= BOARD_SIZE)
            throw new IllegalMovementException("Movimento ilegal, posição final fora do tabuleiro");

        // Verifica se a carta usada está na mão do jogador
        boolean cardInHand = false;

        for (Card c : turn.getCards()) {
            if (c.getName().equals(card.getName())) {
                cardInHand = true;
                break;
            }
        }

        if(!cardInHand) 
            throw new InvalidCardException("A carta não está na mão do jogador.");

        // Realiza o movimento da peça
        
        if (endRow < 0 || endRow  >= BOARD_SIZE || endCol < 0 || endCol >= BOARD_SIZE)
            throw new IllegalMovementException("Movimento ilegal, posição final fora do tabuleiro");

        Spot endSpot = board[endRow][endCol];

        endSpot.movePiece(curSpot.getPiece());
        
        curSpot.removePiece();

        return;
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
                else if(spot.getPiece().isMaster() == false){
                    if(spot.getPiece().getColor() == Color.BLUE) {
                        System.out.print("[BS]");
                    } else System.out.print("[RS]");
                }
                else if(spot.getPiece().isMaster() == true){
                    if(spot.getPiece().getColor() == Color.BLUE) {
                        System.out.print("[BM]");
                    } else System.out.print("[RM]");
                }
            }
            System.out.println();
        }
        System.out.println();

        return;
    };

    /**
     * Método que imprime o estado atual do jogo
     */
    public void printGame(){
        System.out.println("O tabuleiro é:");
        this.printBoard();

        System.out.println("A carta na mesa é:");
        this.tableCard.printCard();

        System.out.println("A mão do jogador " + this.redPlayer.getName() + " é:");
        this.redPlayer.printHand();

        System.out.println("A mão do jogador " + this.bluePlayer.getName() + " é:");
        this.bluePlayer.printHand();

        return;
    }
}
