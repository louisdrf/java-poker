import org.example.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    @Test
    public void gameWinnerTest() {
        Deck deck = new Deck();
        Hand player1Hand = new Hand(deck.cards, "player1");
        Hand player2Hand = new Hand(deck.cards, "player2");

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertNotNull("Un gagnant doit être déterminé", game.winner);
        // Plus de tests pour des scénarios spécifiques...
    }

    @Test
    public void testWinnerWithDifferentCombinations() {
        Deck deck = new Deck();
        Hand player1Hand = new Hand(deck.cards, "player1");
        Hand player2Hand = new Hand(deck.cards, "player2");

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertNotNull("Un gagnant devrait être déterminé", game.winner);
    }

    //Carte Haute : la carte la plus haute de la main.
    @Test
    public void testHighCard() {
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 2), new Card(1, 3), new Card(2, 5), new Card(3, 9), new Card(0, 7)));

        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 2), new Card(1, 3), new Card(2, 4), new Card(3, 8), new Card(0, 6)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertEquals("Le joueur 1 devrait gagner avec la carte haute", player1Hand, game.winner);
    }

    //Pair
    @Test
    public void testPair() {
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 6), new Card(3, 8), new Card(0, 10)));

        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 2), new Card(1, 2), new Card(2, 5), new Card(3, 7), new Card(0, 9)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertEquals("Le joueur 1 devrait gagner avec une paire la plus élevée", player1Hand, game.winner);
    }

    //Double Paire : celle qui a la plus haute
    @Test
    public void testTwoPairs() {
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 5), new Card(1, 5), new Card(2, 8), new Card(3, 8), new Card(0, 11)));

        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 7), new Card(3, 7), new Card(0, 10)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertEquals("Le joueur 1 devrait gagner avec les deux paires les plus élevées", player1Hand, game.winner);
    }


    //Brelan
    @Test
    public void testThreeOfAKind() {
        // Création de la main du joueur 1 avec un brelan de 6
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 6), new Card(1, 6), new Card(2, 6), new Card(3, 2), new Card(0, 9)));

        // Création de la main du joueur 2 avec un brelan de 4
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 4), new Card(3, 3), new Card(0, 8)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        assertEquals("Le joueur 1 devrait gagner avec un brelan de 6", player1Hand, game.winner);
    }

    //Suite
    @Test
    public void testStraight() {
        // Création de la main du joueur 1 avec une suite de 5 à 9
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 5), new Card(1, 6), new Card(2, 7), new Card(3, 8), new Card(0, 9)));

        // Création de la main du joueur 2 avec une suite de 10 à As (14)
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 10), new Card(1, 11), new Card(2, 12), new Card(3, 13), new Card(0, 14)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 2 gagne avec une suite plus élevée (10 à As)
        assertEquals("Le joueur 2 devrait gagner avec une suite plus élevée", player2Hand, game.winner);
    }

    //Couleur (Flush)
    @Test
    public void testFlush() {
        // Création de la main du joueur 1 avec une couleur de cœurs, haute carte 9
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(2, 2), new Card(2, 4), new Card(2, 6), new Card(2, 8), new Card(2, 9)));

        // Création de la main du joueur 2 avec une couleur de carreaux, haute carte Reine (12)
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(1, 7), new Card(1, 9), new Card(1, 10), new Card(1, 11), new Card(1, 12)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 2 gagne avec une couleur plus élevée (haute carte Reine)
        assertEquals("Le joueur 2 devrait gagner avec une couleur plus élevée", player2Hand, game.winner);
    }

    //Full House
    @Test
    public void testFullHouse() {
        // Création de la main du joueur 1 avec un Full House : brelan de 3 et paire de 2
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 3), new Card(1, 3), new Card(2, 3), new Card(3, 2), new Card(0, 2)));

        // Création de la main du joueur 2 avec un Full House : brelan de 6 et paire de 4
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 6), new Card(1, 6), new Card(2, 6), new Card(3, 4), new Card(0, 4)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 2 gagne avec un Full House supérieur (brelan de 6)
        assertEquals("Le joueur 2 devrait gagner avec un Full House supérieur", player2Hand, game.winner);
    }

    //Carre
    @Test
    public void testFourOf() {
        // Création de la main du joueur 1 avec un Carré de 4
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 4), new Card(3, 4), new Card(0, 9)));

        // Création de la main du joueur 2 avec un Carré de 7
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 7), new Card(1, 7), new Card(2, 7), new Card(3, 7), new Card(0, 5)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 2 gagne avec un Carré de 7, qui est supérieur au Carré de 4 du joueur 1
        assertEquals("Le joueur 2 devrait gagner avec un Carré de 7", player2Hand, game.winner);
    }

    //Quinte Flush
    @Test
    public void testStraightFlush() {
        // Création de la main du joueur 1 avec une Quinte Flush de 5 à 9 de cœurs
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(2, 5), new Card(2, 6), new Card(2, 7), new Card(2, 8), new Card(2, 9)));

        // Création de la main du joueur 2 avec une Quinte Flush de 9 à Roi de piques
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(3, 9), new Card(3, 10), new Card(3, 11), new Card(3, 12), new Card(3, 13)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 2 gagne avec une Quinte Flush plus élevée (9 à Roi)
        assertEquals("Le joueur 2 devrait gagner avec une Quinte Flush plus élevée", player2Hand, game.winner);
    }

    //Combinaisons identiques
    @Test
    public void testKickerWithSameCombination() {
        // Main du joueur 1 avec une carte plus élevé (As)
        Hand player1Hand = new Hand("player1");
        player1Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 8), new Card(3, 8), new Card(0, 14)));

        // Main du joueur 2 avec une carte inférieur (Roi)
        Hand player2Hand = new Hand("player2");
        player2Hand.setCards(Arrays.asList(
                new Card(0, 4), new Card(1, 4), new Card(2, 8), new Card(3, 8), new Card(0, 13)));

        player1Hand.manageCard();
        player2Hand.manageCard();

        Game game = new Game(player1Hand, player2Hand);
        game.checkWinner();

        // Vérification que le joueur 1 gagne avec une carte plus élevé (As)
        assertEquals("Le joueur 1 devrait gagner avec une carte plus élevé", player1Hand, game.winner);
    }

}
