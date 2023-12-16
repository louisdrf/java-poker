package org.example;

import java.util.*;

public class Hand {

    String owner;
    public List<Card> cards;
    private Map<Integer, Integer> mapValues;
    private Map<Integer, Integer> mapColours;
    public int current_combinaison = Combinaison.HIGHCARD.ordinal();
    public int high_card_value = 2;
    public int combinaison_high_card_value;
    public int combinaison_second_high_card_value; // in case of double pair or full

    public Hand(List<Card> deck, String playername) {
            this.owner = playername;
            this.cards = new ArrayList();
            this.mapValues = new HashMap();
            this.mapColours = new HashMap();
            this.combinaison_second_high_card_value = -1;

            for (int i = 0; i < 5; i++)
            {
                Card card = deck.get(new Random().nextInt(deck.size()));
                deck.remove(card);
                cards.add(card);
                this.mapValues.merge(card.value, 1, Integer::sum);
                this.mapColours.merge(card.colour, 1, Integer::sum);

                if(card.value > this.high_card_value) {
                    this.high_card_value = card.value;
                }
            }
            this.sortCards();

            this.checkCombinaison();
            this.checkColour();
    }

    public void sortCards() {
        this.cards.sort(Comparator.comparingInt(Card::getValue));
    }

    public void checkColour() {
        for (Map.Entry<Integer, Integer> entry : this.mapColours.entrySet()) {
            if(entry.getValue() == 5) {
                if(this.current_combinaison == Combinaison.STRAIGHT.ordinal()) {
                    this.current_combinaison = Combinaison.STRAIGHT_FLUSH.ordinal();
                }
                else if(this.current_combinaison < Combinaison.FLUSH.ordinal()) {
                    this.current_combinaison = Combinaison.FLUSH.ordinal();
                }
            }
        }
    }

    public void checkCombinaison() {

        for (Map.Entry<Integer, Integer> card : this.mapValues.entrySet()) {

            int cardValue = card.getKey();              // la carte en question (as, 5, valet...)
            int cardOccurences = card.getValue();       // nombre d'occurences de la carte dans la main

            switch(cardOccurences) {
                case 2: // paire et double paire
                    this.pairCase(cardValue);
                    break;

                case 3: // full et brelan
                    this.threeOfCase(cardValue);
                    break;

                case 4: // carré
                    this.current_combinaison = Combinaison.FOUR_OF.ordinal();
                    this.combinaison_high_card_value = cardValue;
                    break;
            }
        }

        if(this.mapValues.size() == 5) {
            if(this.checkStraight() && this.current_combinaison < Combinaison.STRAIGHT.ordinal()) {
                this.current_combinaison = Combinaison.STRAIGHT.ordinal();
            }
        }
    }

    public void pairCase(int cardValue) {

        if(this.current_combinaison == Combinaison.PAIR.ordinal())
        {
            this.current_combinaison = Combinaison.DOUBLE_PAIR.ordinal();
            this.combinaison_second_high_card_value = cardValue;
        }
        else if (this.current_combinaison == Combinaison.THREE_OF.ordinal())
        {
            this.current_combinaison = Combinaison.FULL.ordinal();
            this.combinaison_second_high_card_value = cardValue;
        }
        else {
            this.current_combinaison = Combinaison.PAIR.ordinal();
            this.combinaison_high_card_value = cardValue;
        }
    }

    public void threeOfCase(int cardValue) {

        if(this.current_combinaison == Combinaison.PAIR.ordinal())
        {
                this.current_combinaison = Combinaison.FULL.ordinal();
                this.combinaison_second_high_card_value = cardValue;
        }
        else {
            this.current_combinaison = Combinaison.THREE_OF.ordinal();
            this.combinaison_high_card_value = cardValue;
        }
    }

    public boolean checkStraight() {
        for(int i = 0; i < this.cards.size()-1; i++) {
            Card current = this.cards.get(i);
            Card next = this.cards.get(i + 1);

            if(next.value != current.value + 1) {
                return false;
            }
        }
        return true;
    }

    public void displayHand() {
        for (Card card : this.cards) {
            card.display();
        }
    }

    public void displayCombinaison() {

        switch (this.current_combinaison) {
            case 0 :
                System.out.print("carte haute : ");
                this.printValue(this.high_card_value);
                break;

            case 1 :
                System.out.print("paire de ");
                this.printValue(this.combinaison_high_card_value);
                break;
            case 2 :
                System.out.print("double paire : ");
                this.printValue(this.combinaison_high_card_value);
                System.out.print(" et ");
                this.printValue(this.combinaison_second_high_card_value);
                break;

            case 3 :
                System.out.print("brelan de ");
                this.printValue(this.combinaison_high_card_value);
                break;

            case 4 : System.out.print("suite");

            case 5 : System.out.print("couleur");
            case 6 :
                System.out.print("full au ");
                this.printValue(this.combinaison_high_card_value);
                System.out.print(" et ");
                this.printValue(this.combinaison_second_high_card_value);
                break;

            case 7 :
                System.out.print("carré de ");
                this.printValue(this.combinaison_high_card_value);
                break;

            case 8 : System.out.print("quinte flush");
            break;
        }

        System.out.println(" ");
    }

    public void printValue(int value) {
        switch(value) {
            case 11:
                System.out.print("Valet"); break;
            case 12:
                System.out.print("Dame"); break;
            case 13:
                System.out.print("Roi"); break;
            case 14:
                System.out.print("As"); break;
            default:
                System.out.print(value); break;
        }
    }

}


enum Combinaison {
    HIGHCARD,
    PAIR,
    DOUBLE_PAIR,
    THREE_OF,
    STRAIGHT,
    FLUSH,
    FULL,
    FOUR_OF,
    STRAIGHT_FLUSH
}