import org.example.Card;
import org.example.Combinaison;
import org.example.Deck;
import org.example.Hand;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HandTest {

    @Test
    public void handInitializationTest() {
        Deck deck = new Deck();
        Hand hand = new Hand(deck.cards, "player");
        assertEquals("La main doit contenir 5 cartes", 5, hand.cards.size());
    }

    //Tester des Combinaisons Spécifiques
    @Test
    public void testHighCard() {
        Hand hand = new Hand("player");
        hand.setCards(Arrays.asList(
                new Card(0, 2),
                new Card(1, 5),
                new Card(2, 7),
                new Card(3, 9),
                new Card(0, 11) // Valet de pique comme carte haute
        ));
        assertEquals("Doit être une carte haute", Combinaison.HIGHCARD.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testPair() {
        Hand hand = new Hand("player");

        hand.setCards(Arrays.asList(
                new Card(0, 10), // 10 de pique
                new Card(1, 10), // 10 de cœur
                new Card(2, 3),  // 3 de trèfle
                new Card(3, 6),  // 6 de carreau
                new Card(0, 9)   // 9 de pique
        ));

        assertEquals("La main devrait être une paire", Combinaison.PAIR.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testTwoPairs() {
        Hand hand = new Hand("player");
        hand.setCards(Arrays.asList(
                new Card(0, 3),
                new Card(1, 3),
                new Card(2, 6),
                new Card(3, 6),
                new Card(0, 9)
        ));
        assertEquals("Doit être une double paire", Combinaison.DOUBLE_PAIR.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testBrelan() {
        Hand hand = new Hand("player");

        hand.setCards(Arrays.asList(
                new Card(0, 7), // 7 de pique
                new Card(1, 7), // 7 de cœur
                new Card(2, 7), // 7 de trèfle
                new Card(3, 4), // 4 de carreau
                new Card(0, 9)  // 9 de pique
        ));

        assertEquals("La main devrait être un brelan", Combinaison.THREE_OF.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testStraight() {
        Hand hand = new Hand("player");

        hand.setCards(Arrays.asList(
                new Card(0, 4), // 4 de pique
                new Card(1, 5), // 5 de cœur
                new Card(2, 6), // 6 de trèfle
                new Card(3, 7), // 7 de carreau
                new Card(0, 8)  // 8 de pique
        ));

        assertEquals("La main devrait être une suite", Combinaison.STRAIGHT.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testFlush() {
        Hand hand = new Hand("player");

        hand.setCards(Arrays.asList(
                new Card(0, 2), // 2 de pique
                new Card(0, 5), // 5 de pique
                new Card(0, 7), // 7 de pique
                new Card(0, 9), // 9 de pique
                new Card(0, 11) // Valet de pique
        ));

        assertEquals("La main devrait être un flush", Combinaison.FLUSH.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testFull() {
        Hand hand = new Hand("player");

        hand.setCards(Arrays.asList(
                new Card(0, 7), // 7 de pique
                new Card(1, 7), // 7 de cœur
                new Card(2, 7), // 7 de trèfle
                new Card(3, 4), // 4 de carreau
                new Card(0, 4)  // 4 de pique
        ));

        assertEquals("La main devrait être un full house", Combinaison.FULL.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testFourOf() {
        Hand hand = new Hand("player");
        hand.setCards(Arrays.asList(
                new Card(0, 8),
                new Card(1, 8),
                new Card(2, 8),
                new Card(3, 8),
                new Card(0, 4)
        ));
        assertEquals("Doit être un carré", Combinaison.FOUR_OF.ordinal(), hand.current_combinaison);
    }

    @Test
    public void testStraightFlush() {
        Hand hand = new Hand("player");
        hand.setCards(Arrays.asList(
                new Card(0, 5),
                new Card(0, 6),
                new Card(0, 7),
                new Card(0, 8),
                new Card(0, 9)
        ));
        assertEquals("Doit être une quinte flush", Combinaison.STRAIGHT_FLUSH.ordinal(), hand.current_combinaison);
    }

}
