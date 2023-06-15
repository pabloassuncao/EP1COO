public class Main {
    public static void main(String[] args) {
        Card[] cards = Card.createCards();

        System.out.println("As cartas do jogo:");

        for (Card card : cards) {
          	card.printCard();
        }

        GameImpl game = new GameImpl("João", "Maria", cards);

        System.out.println("O jogo vai começar!");

        game.play();

        System.out.println("O jogo acabou!");
    }
}