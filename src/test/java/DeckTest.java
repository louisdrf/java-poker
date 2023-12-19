import static org.junit.Assert.*;
import org.junit.Test;
import org.example.Deck;
import org.example.Card;

import java.util.HashSet;
import java.util.Set;

public class DeckTest {

    @Test
    public void deckInitializationTest() {
        Deck deck = new Deck();
        assertNotNull("Le paquet ne doit pas être null", deck);
        assertEquals("Le paquet doit contenir 52 cartes", 52, deck.cards.size());

        Set<String> uniqueCards = new HashSet<>();
        for (Card card : deck.cards) {
            String cardSignature = card.colour + "-" + card.value;
            uniqueCards.add(cardSignature);
        }

        assertEquals("Toutes les cartes doivent être uniques", 52, uniqueCards.size());
    }
}
