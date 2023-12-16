package org.example;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> cards;

    public Deck() {
        this.cards = new ArrayList();

        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                this.cards.add(new Card(i, j));
            }
        }
    }

    public void displayDeck() {
        for (Card card : this.cards) {
            System.out.print(card.colour + " ");
            System.out.print(card.value + "\n");
        }
    }
}