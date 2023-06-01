/**
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class Player {
    private final String name;
    private final Color pieceColor;
    private final Card[] cards;

    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param cards Cartas na mão do jogador
     */
    public Player(String name, Color pieceColor, Card[] cards) {
        this.name = name;
        this.pieceColor = pieceColor;
        this.cards = cards;
    }

    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param card1 A primeira carta na mão do jogador
     * @param card2 A segunda carta na mão do jogador
     */
    public Player(String name, Color pieceColor, Card card1, Card card2) {
        Card[] cards = {card1, card2};
        this.name = name;
        this.pieceColor = pieceColor;
        this.cards = cards;
    }

    /**
     * Método que devolve o nome do jogador(a)
     * @return String com o nome do jogador(a)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método que devolve a cor das peças do jogador
     * @return Enum Color com a cor das peças do jogador
     */
    public Color getPieceColor() {
        return this.pieceColor;
    }

    /**
     * Método que devolve as cartas da mão do jogador
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Método que troca uma carta da mão por outra carta (idealmente da mesa)
     * @param oldCard A carta que será substituída
     * @param newCard A carta que irá substituir
     * @exception InvalidCardException Caso a carta não esteja na mão do jogador e/ou na mesa
     */
    protected void swapCard(Card oldCard, Card newCard) throws InvalidCardException {
        boolean oldCardInHand = false;
        boolean newCardInHand = false;
        
        for (Card card : this.cards) {
            if (card == oldCard) oldCardInHand = true;
            if (card == newCard) newCardInHand = true;
        }

        if (!oldCardInHand || newCardInHand) 
            throw new InvalidCardException("A carta não está na mão do jogador ou a carta já está na mão do jogador");

        Card temp = oldCard;
        oldCard = newCard;
        newCard = temp;
    }


    /**
     * Método para imprimir as cartas da mão do jogador
     * @return void - não retorna nada
     */
    public void printHand() {
        for(int i = 0; i < this.cards.length; i++){
            this.cards[i].printCard(i + 1);
        }
    }
}
