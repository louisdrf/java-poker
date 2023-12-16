package org.example;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Game game = new Game(new Hand(deck.cards, "player1"), new Hand(deck.cards, "player2"));

        System.out.print("player 1: \n\n");
        game.player1.displayHand();
        System.out.print("\n\nplayer 2: \n\n");
        game.player2.displayHand();
        System.out.print("\n\n");

        game.checkWinner();
        game.displayWinner();
    }
}