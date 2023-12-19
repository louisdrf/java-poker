import static org.junit.Assert.*;

import org.example.Card;
import org.junit.Test;

public class CardTest {

    @Test
    public void cardCreationTest() {
        Card card = new Card(0, 5);
        assertNotNull("La carte ne doit pas être null", card);
        assertEquals("La couleur doit être 0 (pic)", 0, card.colour);
        assertEquals("La valeur doit être 5", 5, card.getValue());
    }

    @Test
    public void getValueTest() {
        Card card = new Card(0, 7);
        assertEquals("La méthode getValue doit retourner la valeur de la carte", 7, card.getValue());
    }
}